package com.paladin.health.data.parser.knowledge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.paladin.health.data.parser.PageParser;

public class ErrorKnowledgePageGetter extends PageParser {

	public void get(String path, String disease, String category) throws IOException {
		String url = "http://jbk.39.net/" + disease + "/" + category;
		Document doc = getDocument(url);
		Elements artElements = doc.getElementsByClass("art-box");

		String html = artElements.outerHtml();

		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>\r\n");
		sb.append("<html>\r\n");
		sb.append("<head>\r\n");
		sb.append("\t<meta charset=\"UTF-8\">\r\n");
		sb.append("\t<title>修复数据</title>\r\n");
		sb.append("</head>\r\n");
		sb.append("<body>\r\n");
		sb.append(html);
		sb.append("</body>\r\n");
		sb.append("</html>\r\n");

		if (!path.endsWith("/")) {
			path += "/";
		}
		path += (disease + "-" + category + ".html");
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(path);
			String content = sb.toString();
			file.write(content.getBytes());
		} finally {
			if (file != null) {
				file.close();
			}
		}
	}

	public static List<String[]> getErrorDisease2(String errorLogPath) {

		ArrayList<String[]> diseases = new ArrayList<>();

		FileReader reader;
		try {
			reader = new FileReader(errorLogPath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("找不到错误日志文件");
		}

		BufferedReader br = new BufferedReader(reader);
		try {
			String str = null;
			while ((str = br.readLine()) != null) {
				int s = str.indexOf("/");
				if (s >= 0) {
					String disease = str.substring(0, s);
					String category = str.substring(s + 1);
					diseases.add(new String[] { disease, category });
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("解析错误日志文件异常");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}

		return diseases;
	}

	public static void main(String[] args) throws IOException {
//		List<String[]> list = getErrorDisease2("d:/error3.txt");
//		ErrorKnowledgePageGetter getter = new ErrorKnowledgePageGetter();
//		String path = "d:/errorPage/";
//		for (String[] l : list) {
//			getter.get(path, l[0], l[1]);
//		}
	}

}
