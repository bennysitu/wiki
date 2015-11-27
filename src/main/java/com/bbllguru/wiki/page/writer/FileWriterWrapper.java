package com.bbllguru.wiki.page.writer;

import com.bbllguru.wiki.page.exception.WriteRawContentException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterWrapper {

	/**
	 * The path to the file
	 */
	protected String path;

	/**
	 * @param The path to the file 
	 */
	public FileWriterWrapper(String path) {
		this.path = path;
	}

	public void write(String data) {
		File file = new File(path);
		try {
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new WriteRawContentException("Error writing transformed wiki content to local file");
		}
		
	}

}
