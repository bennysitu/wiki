package com.bbllguru.wiki.page.reader;

import com.bbllguru.wiki.page.exception.AccessContentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiReader {

	protected String remoteUrl;

	protected static final Logger logger = LoggerFactory.getLogger(ApiReader.class);
	
	protected String makeApiCall() {
		try {
			URL url = new URL(remoteUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();

			String line = rd.readLine();
			while (line != null) {
				sb.append(line);
				line = rd.readLine();
			}
			rd.close();
			conn.disconnect();
			return sb.toString();
		} catch (IOException e) {
			logger.error("Could not read remote content from: " + remoteUrl, e);
			throw new AccessContentException("Could not read remote content from: " + remoteUrl);
		}
	}
}
