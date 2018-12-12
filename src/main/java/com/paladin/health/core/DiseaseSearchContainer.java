package com.paladin.health.core;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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

import com.paladin.framework.core.VersionContainer;
import com.paladin.framework.utils.StringParser;
import com.paladin.health.model.origin.OriginDiseaseName;
import com.paladin.health.model.origin.OriginDiseaseTag;
import com.paladin.health.service.origin.OriginDiseaseNameService;
import com.paladin.health.service.origin.OriginDiseaseTagService;

/**
 * 疾病搜索容器
 */
@Component
public class DiseaseSearchContainer implements VersionContainer {

	private static Logger logger = LoggerFactory.getLogger(DiseaseSearchContainer.class);

	@Autowired
	private OriginDiseaseTagService diseaseTagService;
	@Autowired
	private OriginDiseaseNameService diseaseNameService;

	private static final String FIELD_SEARCH = "attr";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_KEY = "key";

	private static final int MAX_SEARCH_RESULT_COUNT = 1000;

	private IndexReader reader = null;
	private IndexSearcher searcher = null;

	public boolean initialize() {
		logger.info("-------------开始初始化疾病搜索服务功能-------------");
				
		try {
			String path = ResourceUtils.getFile("classpath:lucene/disease").getPath();
			Directory dir = FSDirectory.open(Paths.get(path));
			reader = DirectoryReader.open(dir);
			searcher = new IndexSearcher(reader);
		} catch (IOException e) {
			logger.error("初始化失败", e);
		}
		
		logger.info("-------------结束初始化疾病搜索服务功能-------------");
		return true;
	}

	public Directory writeDirectory(String path) {
		Directory dir = null;
		IndexWriter writer = null;
		try {
			dir = FSDirectory.open(Paths.get(path));
			writer = new IndexWriter(dir, new IndexWriterConfig());
			List<OriginDiseaseName> names = diseaseNameService.findAllDiseaseName();

			logger.info("开始读取疾病数据生成索引");
			long t1 = System.currentTimeMillis();

			for (OriginDiseaseName name : names) {
				String diseaseKey = name.getNameKey();
				String diseaseName = name.getName();

				Document doc = new Document();
				doc.add(new StringField(FIELD_KEY, diseaseKey, Store.YES));
				doc.add(new StringField(FIELD_NAME, diseaseName, Store.YES));
				doc.add(new StringField(FIELD_SEARCH, diseaseName, Store.NO));

				List<OriginDiseaseTag> tags = diseaseTagService.findAllDiseaseTag(diseaseKey);
				if (tags != null && tags.size() > 0) {
					for (OriginDiseaseTag tag : tags) {
						doc.add(new StringField(FIELD_SEARCH, tag.getName(), Store.NO));
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
	 * 搜索疾病
	 * @param args
	 * @return
	 */
	public DiseaseSearchResult search(String... args) {

		if (args == null || args.length == 0) {
			return null;
		}

		Query query = null;

		if (args.length == 1) {
			query = new TermQuery(new Term(FIELD_SEARCH, args[0]));
		} else {
			BooleanQuery.Builder builder = new BooleanQuery.Builder();
			for (String arg : args) {
				builder.add(new TermQuery(new Term(FIELD_SEARCH, arg)), Occur.MUST);
			}
			query = builder.build();
		}

		try {
			TopDocs topDocs = searcher.search(query, MAX_SEARCH_RESULT_COUNT);

			// 提取TopDocs对象中的文档ID，如何找出对应的文档
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			String[][] result = new String[scoreDocs.length][2];

			for (int i = 0; i < scoreDocs.length; i++) {
				ScoreDoc scoreDoc = scoreDocs[i];
				int docId = scoreDoc.doc;
				Document doc = searcher.doc(docId);
				result[i] = new String[] { doc.get(FIELD_KEY), doc.get(FIELD_NAME) };
			}

			return new DiseaseSearchResult(result, topDocs.totalHits);
		} catch (IOException e1) {
			logger.error("搜索疾病异常:" + StringParser.toString(args), e1);
		}

		return null;
	}

	public static class DiseaseSearchResult {

		String[][] diseases;
		long totalNum;

		DiseaseSearchResult(String[][] diseases, long totalNum) {
			this.diseases = diseases;
			this.totalNum = totalNum;
		}

		public String[][] getDiseases() {
			return diseases;
		}

		public void setDiseases(String[][] diseases) {
			this.diseases = diseases;
		}

		public long getTotalNum() {
			return totalNum;
		}

		public void setTotalNum(long totalNum) {
			this.totalNum = totalNum;
		}

	}

	@Override
	public String getId() {
		return "disease_search_container";
	}

	@Override
	public boolean versionChangedHandle(long version) {
		return initialize();
	}
}
