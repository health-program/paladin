package com.paladin.framework.core.configuration.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "paladin.web")
public class MyWebProperties {

	/**
	 * 文件存放地址
	 */
	private String filePath = "file:D:/file/";

	/**
	 * 文件最大M数
	 */
	private int fileMaxSize = 10;
	
	/**
	 * 静态资源路径
	 */
	private String staticPath = "classpath:/static/";
	
	/**
	 * 图标路径
	 */
	private String faviconPath = "classpath:favicon.ico";
	
	/**
	 * root view
	 */
	private String rootView = "redirect:/index";

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFileMaxSize() {
		return fileMaxSize;
	}

	public void setFileMaxSize(int fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

	public String getStaticPath() {
		return staticPath;
	}

	public void setStaticPath(String staticPath) {
		this.staticPath = staticPath;
	}

	public String getFaviconPath() {
		return faviconPath;
	}

	public void setFaviconPath(String faviconPath) {
		this.faviconPath = faviconPath;
	}

	public String getRootView() {
		return rootView;
	}

	public void setRootView(String rootView) {
		this.rootView = rootView;
	}

}
