package com.bbllguru.wiki.page.reader;

import com.bbllguru.wiki.page.exception.AccessFileException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReaderWrapper {

	protected String path;

	protected BufferedReader br;

	protected static final Logger logger = LoggerFactory.getLogger(FileReaderWrapper.class);

	public FileReaderWrapper(String path) {
		this.path = path;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (IOException e) {
			logger.error("Failed to create BufferedReader ", e);
			close();
			throw new AccessFileException("Faile to create BufferedReader for file: " + path);
		}
	}

	public String getNextLine() {
		try {
			String line = br.readLine();
			if (line != null) {
				return line.trim();
			} else {
				return null;
			}
		} catch (IOException e) {
			logger.error("Failed to read content from file: " + path, e);
			throw new AccessFileException("Faile to read content from file: " + path);
		}
	}

	public void close() {
		try {
			if (br != null) {
				br.close();
			}
		} catch (IOException e) {
			logger.warn("Failed to close BufferedReader ", e);
		}
	}
}

