package com.paladin.health.core;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.paladin.framework.spring.SpringContainer;
import com.paladin.framework.utils.StringParser;
import com.paladin.health.core.factor.PeopleCondition;
import com.paladin.health.core.factor.FactorAnalyzer.FactorResult;
import com.paladin.health.model.prescription.PrescriptionFactor;
import com.paladin.health.model.prescription.PrescriptionFactorItem;
import com.paladin.health.model.prescription.PrescriptionItem;
import com.paladin.health.model.prescription.PrescriptionTerminology;
import com.paladin.health.service.prescription.PrescriptionFactorItemService;
import com.paladin.health.service.prescription.PrescriptionFactorService;
import com.paladin.health.service.prescription.PrescriptionItemService;
import com.paladin.health.service.prescription.PrescriptionTerminologyService;

/**
 * 健康处方搜索容器
 */
@Component
public class HealthPrescriptionContainer implements SpringContainer {

	private static Logger logger = LoggerFactory.getLogger(HealthPrescriptionContainer.class);

	@Autowired
	private PrescriptionFactorItemService prescriptionFactorItemService;
	@Autowired
	private PrescriptionFactorService prescriptionFactorService;
	@Autowired
	private PrescriptionItemService prescriptionItemService;
	@Autowired
	private PrescriptionTerminologyService prescriptionTerminologyService;

	private static final String FIELD_ID = "id";
	private static final String FIELD_CONTENT = "content";
	private static final String FIELD_DETAIL = "detail";
	private static final String FIELD_FACTOR = "factor";
	private static final String FIELD_TYPE = "type";
	private static final String FIELD_MUTEX = "mutex";
	private static final String FIELD_MUTEX_PRIORITY = "mutex_priority";
	private static final String FIELD_DEMAND = "demand";
	private static final String FIELD_TERMINOLOGY = "terminology";

	private static final int MAX_SEARCH_RESULT_COUNT = 1000;

	private IndexReader reader = null;
	private IndexSearcher searcher = null;

	private List<PrescriptionFactor> prescriptionFactor;
	private Map<String, String> factorNameMap;
	private Map<String, Factor> factorCodeMap;

	private Set<Factor> hasParentFactorList;

	private Map<String, PrescriptionTerminology> terminologyMap;

	@Override
	public boolean initialize() {
		logger.info("-------------开始初始化健康处方搜索服务功能-------------");
		
		// 初始化处方专业术语map
		List<PrescriptionTerminology> prescriptionTerminologies = prescriptionTerminologyService.findAll();

		Map<String, PrescriptionTerminology> terminologyMap = new HashMap<>();
		for (PrescriptionTerminology terminology : prescriptionTerminologies) {
			terminologyMap.put(terminology.getName(), terminology);
		}

		this.terminologyMap = terminologyMap;

		prescriptionFactor = prescriptionFactorService.findAll();
		prescriptionFactor = Collections.unmodifiableList(prescriptionFactor);

		factorNameMap = new HashMap<>();
		factorCodeMap = new HashMap<>();

		for (PrescriptionFactor factor : prescriptionFactor) {
			factorNameMap.put(factor.getName(), factor.getCode());
			factorCodeMap.put(factor.getCode(), new Factor(factor));
		}

		hasParentFactorList = new HashSet<>();

		for (Factor factor : factorCodeMap.values()) {
			String parentFactor = factor.source.getParentFactor();
			if (parentFactor != null && parentFactor.length() != 0) {
				String[] parents = parentFactor.split(",");
				for (String parent : parents) {
					Factor f = factorCodeMap.get(parent);
					if (f != null) {
						factor.parents.add(f);
						hasParentFactorList.add(factor);
					}
				}
			}
		}

		for (Factor factor : hasParentFactorList) {
			factor.initChildParam();
		}

		try {
			String path = ResourceUtils.getFile("classpath:lucene/prescription").getPath();
			Directory dir = FSDirectory.open(Paths.get(path));
			reader = DirectoryReader.open(dir);
			searcher = new IndexSearcher(reader);
		} catch (IOException e) {
			logger.error("初始化失败", e);
		}

		logger.info("-------------结束初始化健康处方搜索服务功能-------------");
		return true;
	}

	private static class Factor {

		PrescriptionFactor source;

		String code;

		HashSet<Factor> parents = new HashSet<>();

		Factor(PrescriptionFactor prescriptionFactor) {
			source = prescriptionFactor;
			code = prescriptionFactor.getCode();
		}

		public void getFactorAndParent(HashSet<String> codes) {
			codes.add(this.code);
			if (parents.size() > 0) {
				for (Factor f : parents) {
					f.getFactorAndParent(codes);
				}
			}
		}

		List<String> parentCodes;
		boolean initParentCodes;

		private void initChildParam() {
			if (!initParentCodes) {
				parentCodes = new ArrayList<>(parents.size());
				for (Factor f : parents) {
					if (f.parents.size() > 0) {
						f.initChildParam();
						for (String c : f.parentCodes) {
							parentCodes.add(c);
						}
					} else {
						parentCodes.add(f.code);
					}
				}
				initParentCodes = true;
			}
		}

		private boolean isChild(Collection<String> codes) {
			for (String code : parentCodes) {
				if (!codes.contains(code)) {
					return false;
				}
			}
			return true;
		}

		@Override
		public int hashCode() {
			return code.hashCode();
		}

		@Override
		public boolean equals(Object obj) {

			if (obj == null)
				return false;

			if (obj instanceof Factor) {
				Factor f = (Factor) obj;
				if (code == null) {
					return f.code == null;
				}
				return code.equals(f.code);
			}
			return false;
		}

	}

	public static class Prescription {

		String content;
		String detail;
		int type;
		String[] mutexIds;
		int mutexPriority;
		int id;
		String demand;
		String terminology;

		Prescription(Document doc) {
			id = Integer.valueOf(doc.get(FIELD_ID));
			content = doc.get(FIELD_CONTENT);
			detail = doc.get(FIELD_DETAIL);
			type = Integer.valueOf(doc.get(FIELD_TYPE));
			demand = doc.get(FIELD_DEMAND);
			terminology = doc.get(FIELD_TERMINOLOGY);
			String mutex = doc.get(FIELD_MUTEX);
			if (mutex != null && mutex.length() > 0) {
				mutexIds = mutex.split(",");
				String mutexPriorityStr = doc.get(FIELD_MUTEX_PRIORITY);
				if (mutexPriorityStr != null && mutexPriorityStr.length() > 0) {
					mutexPriority = Integer.valueOf(mutexPriorityStr);
				}
			}
		}

		Prescription(String content, int type) {
			this.content = content;
			this.type = type;
		}

		public String getContent() {
			return content;
		}

		public int getType() {
			return type;
		}

		public String getDetail() {
			return detail;
		}

		public int getId() {
			return id;
		}

		public String getTerminology() {
			return terminology;
		}
	}

	public static class PrescriptionResult {

		private List<PrescriptionFactor> factors;
		private List<Prescription> prescriptions;
		private List<PrescriptionTerminology> terminologies;

		public PrescriptionResult(List<Prescription> prescriptions, List<PrescriptionFactor> factors, List<PrescriptionTerminology> terminologies) {
			this.factors = factors;
			this.prescriptions = prescriptions;
			this.terminologies = terminologies;
		}

		public List<PrescriptionFactor> getFactors() {
			return factors;
		}

		public List<Prescription> getPrescriptions() {
			return prescriptions;
		}

		public List<PrescriptionTerminology> getTerminologies() {
			return terminologies;
		}

	}

	/**
	 * 搜索健康处方
	 * 
	 * @param args
	 * @return
	 */
	public PrescriptionResult search(Collection<String> args) {
		if (args == null) {
			return null;
		}
		return search(args.toArray(new String[args.size()]));
	}

	/**
	 * 搜索健康处方
	 * 
	 * @param peopleCondition
	 * @return
	 */
	public PrescriptionResult search(PeopleCondition peopleCondition) {
		PrescriptionResult result = search(peopleCondition.getFactors());
		List<FactorResult> factors = peopleCondition.getSpeculateFactors();
		if (factors != null) {
			for (FactorResult fr : factors) {
				result.prescriptions.add(new Prescription(fr.getIllustration(), 10));
			}
		}
		return result;
	}

	/**
	 * 搜索健康处方
	 * 
	 * @param args
	 * @return
	 */
	public PrescriptionResult search(String... args) {

		if (args == null || args.length == 0) {
			return null;
		}

		HashSet<String> codes = new HashSet<>();
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			String code = factorNameMap.get(arg);
			if (code == null) {
				code = arg;
			}
			Factor factor = factorCodeMap.get(code);
			if(factor != null) {
				factor.getFactorAndParent(codes);
			}
		}

		for (Factor factor : hasParentFactorList) {
			String code = factor.code;
			if (!codes.contains(code)) {
				if (factor.isChild(codes)) {
					codes.add(code);
				}
			}
		}

		if (codes.size() > 0) {
			List<PrescriptionFactor> factors = new ArrayList<>();
			BooleanQuery.Builder builder = new BooleanQuery.Builder();
			for (String code : codes) {
				builder.add(new TermQuery(new Term(FIELD_FACTOR, code)), Occur.SHOULD);
				factors.add(factorCodeMap.get(code).source);
			}
			Query query = builder.build();

			try {
				TopDocs topDocs = searcher.search(query, MAX_SEARCH_RESULT_COUNT);

				// 提取TopDocs对象中的文档ID，如何找出对应的文档
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;
				Prescription[] result = new Prescription[scoreDocs.length];

				for (int i = 0; i < scoreDocs.length; i++) {
					ScoreDoc scoreDoc = scoreDocs[i];
					int docId = scoreDoc.doc;
					Document doc = searcher.doc(docId);
					result[i] = new Prescription(doc);
				}

				// 提取处方术语说明
				List<Prescription> prescriptions = filterPrescription(result, codes);
				HashSet<String> terminologySet = new HashSet<>();

				for (Prescription p : prescriptions) {
					if (p.terminology.length() > 0) {
						String[] ts = p.terminology.split(",");
						for (String t : ts) {
							terminologySet.add(t);
						}
					}
				}

				List<PrescriptionTerminology> terminologies = new ArrayList<>(terminologySet.size());
				for (String terminologyName : terminologySet) {
					PrescriptionTerminology pt = terminologyMap.get(terminologyName);
					if (pt != null) {
						terminologies.add(pt);
					}
				}

				return new PrescriptionResult(prescriptions, factors, terminologies);
			} catch (IOException e1) {
				logger.error("搜索健康处方异常:" + StringParser.toString(args), e1);
			}
		}

		return null;
	}

	private Comparator<Prescription> prescriptionComparator = new Comparator<Prescription>() {

		@Override
		public int compare(Prescription o1, Prescription o2) {
			return o1.id - o2.id;
		}

	};

	/**
	 * 过滤，去除重复无效处方
	 * 
	 * @param prescriptions
	 * @return
	 */
	private List<Prescription> filterPrescription(Prescription[] prescriptions, HashSet<String> factorCodes) {

		// 排序，更友好的用户体验
		Arrays.sort(prescriptions, prescriptionComparator);

		HashMap<String, Prescription> mutexPriorityMap = new HashMap<>();
		List<Prescription> result = new ArrayList<>(prescriptions.length);

		// LinkedHashSet<String> shouldEat = new LinkedHashSet<>();
		// LinkedHashSet<String> notShouldEat = new LinkedHashSet<>();

		for (Prescription p : prescriptions) {
			if (p.mutexIds != null) {
				if (isSuitable(p, factorCodes)) {
					for (String id : p.mutexIds) {
						Prescription pp = mutexPriorityMap.get(id);
						if (pp == null || p.mutexPriority > pp.mutexPriority) {
							mutexPriorityMap.put(id, p);
						}
					}
				}
			} else {

				// if (p.type == 3) {
				// shouldEat.add(p.content);
				// } else if (p.type == 4) {
				// notShouldEat.add(p.content);
				// } else {
				// result.add(p);
				// }

				result.add(p);
			}
		}

		for (Prescription p : mutexPriorityMap.values()) {
			result.add(p);
		}

		// 不在做适宜与忌吃的处理
		// if (notShouldEat.size() > 0) {
		// StringBuilder notEatContent = new StringBuilder("忌吃");
		// for (String notEat : notShouldEat) {
		// notEatContent.append(notEat).append("、");
		// }
		// notEatContent.deleteCharAt(notEatContent.length() - 1);
		// result.add(new Prescription(notEatContent.toString(), 4));
		// }
		//
		// if (shouldEat.size() > 0) {
		// StringBuilder eatContent = new StringBuilder("宜吃");
		// for (String eat : shouldEat) {
		// if (!notShouldEat.contains(eat)) {
		// eatContent.append(eat).append("、");
		// }
		// }
		//
		// if (eatContent.length() > 2) {
		// eatContent.deleteCharAt(eatContent.length() - 1);
		// result.add(new Prescription(eatContent.toString(), 3));
		// }
		// }

		Collections.sort(result, prescriptionComparator);
		return result;
	}

	/**
	 * 是否是合适的处方
	 * <p>
	 * 如果健康处方需要满足某些因素，则判断这些因素是否已经存在
	 * </p>
	 * 
	 * @param p
	 * @param factorCodes
	 * @return
	 */
	private boolean isSuitable(Prescription p, HashSet<String> factorCodes) {
		if (p.demand.length() > 0) {
			String[] demands = p.demand.split(",");
			for (String d : demands) {
				if (!factorCodes.contains(d)) {
					return false;
				}
			}
		}
		return true;
	}

	public List<PrescriptionFactor> getPrescriptionFactor() {
		return prescriptionFactor;
	}

	public String getCodeByName(String name) {
		return factorNameMap.get(name);
	}

	public PrescriptionFactor getFactorByCode(String code) {
		Factor factor = factorCodeMap.get(code);
		return factor == null ? null : factor.source;
	}

	/**
	 * 创建索引
	 * 
	 * @param path
	 * @return
	 */
	public Directory writeDirectory(String path) {
		Directory dir = null;
		IndexWriter writer = null;
		try {
			dir = FSDirectory.open(Paths.get(path));
			writer = new IndexWriter(dir, new IndexWriterConfig());

			List<PrescriptionItem> items = prescriptionItemService.findAll();

			logger.info("开始读取健康处方生成索引");
			long t1 = System.currentTimeMillis();

			for (PrescriptionItem item : items) {

				List<PrescriptionFactorItem> factorItems = prescriptionFactorItemService.findFactorByItem(item.getId());

				if (factorItems != null && factorItems.size() > 0) {

					String content = item.getContent();
					String detail = item.getDetail();
					String mutex = item.getMutex();
					Integer mutexPriority = item.getMutexPriority();
					String demand = item.getDemand();
					String terminology = item.getTerminology();

					Document doc = new Document();
					doc.add(new StringField(FIELD_ID, item.getId().toString(), Store.YES));
					doc.add(new StringField(FIELD_DEMAND, demand == null ? "" : demand, Store.YES));
					doc.add(new StringField(FIELD_CONTENT, content, Store.YES));
					doc.add(new StringField(FIELD_DETAIL, detail == null ? "" : detail, Store.YES));
					doc.add(new StringField(FIELD_TYPE, item.getType().toString(), Store.YES));
					doc.add(new StringField(FIELD_MUTEX, mutex == null ? "" : mutex, Store.YES));
					doc.add(new StringField(FIELD_MUTEX_PRIORITY, mutexPriority == null ? "" : mutexPriority.toString(), Store.YES));
					doc.add(new StringField(FIELD_TERMINOLOGY, terminology == null ? "" : terminology, Store.YES));

					for (PrescriptionFactorItem factorItem : factorItems) {
						doc.add(new StringField(FIELD_FACTOR, factorItem.getFactorCode(), Store.NO));
					}
					writer.addDocument(doc);
				}
			}

			writer.commit();
			long t2 = System.currentTimeMillis();
			logger.info("生成索引结束，共花费" + (t2 - t1) + "毫秒");
		} catch (IOException e) {
			logger.error("写入内存搜索异常", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (dir != null) {
				try {
					dir.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dir;
	}

}
