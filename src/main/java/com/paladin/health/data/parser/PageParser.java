package com.paladin.health.data.parser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.util.HtmlUtils;

import com.paladin.framework.utils.StringUtil;

public class PageParser {

	// private String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64)
	// AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

	protected Document getDocument(String url, String charset) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) ((new URL(url)).openConnection());
		// 默认就是Get，可以采用post，大小写都行，因为源码里都toUpperCase了。
		connection.setRequestMethod("GET");
		// 是否允许缓存，默认true。
		connection.setUseCaches(Boolean.FALSE);
		// 设置请求头信息
		connection.addRequestProperty("Connection", "close");
		// 设置连接主机超时（单位：毫秒）
		connection.setConnectTimeout(60000);
		// 设置从主机读取数据超时（单位：毫秒）
		connection.setReadTimeout(60000);
		// 开始请求
		return Jsoup.parse(connection.getInputStream(), charset, url);
	}

	protected Document getDocument(String url) throws IOException {
		String charset = url.contains("http://jbk.39.net") ? "GBK" : "UTF8";
		return getDocument(url, charset);
	}

	/**
	 * 修复html文本
	 * 
	 * @param text
	 * @return
	 */
	public String repairHtmlText(String text) {
		if (text != null && text.length() > 0) {
			text = HtmlUtils.htmlUnescape(text);
			text = StringUtil.strongTrim(text);
		}

		return text;
	}

}
