package com.paladin.health.core;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.paladin.health.model.prescription.PrescriptionFactor;
import com.paladin.health.model.prescription.PrescriptionFactorItem;
import com.paladin.health.model.prescription.PrescriptionItem;
import com.paladin.health.service.prescription.PrescriptionFactorItemService;
import com.paladin.health.service.prescription.PrescriptionFactorService;
import com.paladin.health.service.prescription.PrescriptionItemService;

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

	static final String FIELD_CONTENT = "content";
	static final String FIELD_FACTOR = "factor";
	static final String FIELD_MUTEX = "mutex";
	static final String FIELD_MUTEX_PRIORITY = "mutex_priority";

	private static final int MAX_SEARCH_RESULT_COUNT = 1000;

	private IndexReader reader = null;
	private IndexSearcher searcher = null;

	private List<PrescriptionFactor> prescriptionFactor;
	private Map<String, String> factorNameMap;

	@Override
	public boolean initialize() {
		logger.info("-------------开始初始化健康处方搜索服务功能-------------");

		prescriptionFactor = prescriptionFactorService.findAll();

		factorNameMap = new HashMap<>();
		for (PrescriptionFactor factor : prescriptionFactor) {
			factorNameMap.put(factor.getName(), factor.getCode());
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
					String mutex = item.getMutex();
					Integer mutexPriority = item.getMutexPriority();

					Document doc = new Document();
					doc.add(new StringField(FIELD_CONTENT, content, Store.YES));
					doc.add(new StringField(FIELD_MUTEX, mutex == null ? "" : mutex, Store.YES));
					doc.add(new StringField(FIELD_MUTEX_PRIORITY, mutexPriority == null ? "" : mutexPriority.toString(), Store.YES));

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

	/**
	 * 搜索健康处方
	 * 
	 * @param args
	 * @return
	 */
	public String[] search(String... args) {

		if (args == null || args.length == 0) {
			return null;
		}

		Query query = null;

		if (args.length == 1) {
			String code = factorNameMap.get(args[0]);
			if (code == null) {
				code = args[0];
			}
			query = new TermQuery(new Term(FIELD_FACTOR, code));
		} else {
			BooleanQuery.Builder builder = new BooleanQuery.Builder();
			for (String arg : args) {
				String code = factorNameMap.get(arg);
				if (code == null) {
					code = arg;
				}
				builder.add(new TermQuery(new Term(FIELD_FACTOR, code)), Occur.SHOULD);
			}
			query = builder.build();
		}

		try {
			TopDocs topDocs = searcher.search(query, MAX_SEARCH_RESULT_COUNT);

			// 提取TopDocs对象中的文档ID，如何找出对应的文档
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			String[] result = new String[scoreDocs.length];

			for (int i = 0; i < scoreDocs.length; i++) {
				ScoreDoc scoreDoc = scoreDocs[i];
				int docId = scoreDoc.doc;
				Document doc = searcher.doc(docId);
				result[i] = doc.get(FIELD_CONTENT);
			}

			return result;
		} catch (IOException e1) {
			logger.error("搜索健康处方异常:" + StringParser.toString(args), e1);
		}

		return null;
	}
	
	

	@Override
	public boolean afterInitialize() {
		return true;
	}

	@Override
	public int order() {
		return 0;
	}

	public List<PrescriptionFactor> getPrescriptionFactor() {
		return prescriptionFactor;
	}

	public String getCode(String name) {
		return factorNameMap.get(name);
	}

}
