package com.bbllguru.wiki.page.reader;

import com.bbllguru.wiki.page.exception.AccessContentException;
import com.bbllguru.wiki.page.util.PropertiesFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiWikiPageReader extends ApiReader {

	protected String charset;

	protected String remoteUrlTpl;

	protected static final Logger logger = LoggerFactory.getLogger(ApiWikiPageReader.class);

	public ApiWikiPageReader(String wiki) {
		Properties p = PropertiesFactory.getProperties("config");
		charset = p.getProperty("wiki.data.page.title.charset");
		remoteUrlTpl = p.getProperty("wiki.data.page.title.url").replace("{wiki}", wiki);
	}

	public String fetchContent(String title) {
		try {
			remoteUrl = remoteUrlTpl + URLEncoder.encode(title, charset);
			return makeApiCall();
		} catch (UnsupportedEncodingException e) {
			logger.error("Could not fetch content for title: " + title, e);
			throw new AccessContentException("Could not read remote content from: " + remoteUrl);
		}
	}
}
