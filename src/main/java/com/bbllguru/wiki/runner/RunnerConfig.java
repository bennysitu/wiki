package com.bbllguru.wiki.runner;

import com.bbllguru.wiki.page.util.PropertiesFactory;

import java.io.File;
import java.util.Properties;

public class RunnerConfig {

	public static Properties p = PropertiesFactory.getProperties("config");

	/**
	 * Initialize the environment for all the job runners
	 * @param baseDir The base directory for all data source data and processed data
	 * @return void
	 */
	public static void initEnvironment(String baseDir) {
		String jsonDir = baseDir + "/" + p.getProperty("wiki.data.json.old.dir");
		String htmlDir = baseDir + "/" + p.getProperty("wiki.data.json.new.dir");
		
		File jsonDirFile = new File(jsonDir), htmlDirFile = new File(htmlDir);
		if (!jsonDirFile.exists()) {
			if (!jsonDirFile.mkdirs()) {
				System.out.println("Error creating data directory: " + jsonDir);
				System.exit(0);
			}
		}
		if (!htmlDirFile.exists()) {
			if (!htmlDirFile.mkdirs()) {
				System.out.println("Error creating data directory: " + htmlDir);
				System.exit(0);
			}
		}
	}

	
}
