package com.bbllguru.wiki.runner;

import com.bbllguru.wiki.page.exception.AccessFileException;
import com.bbllguru.wiki.page.reader.FileReaderWrapper;
import com.bbllguru.wiki.page.reader.ApiWikiPageReader;
import com.bbllguru.wiki.page.util.HashStore;
import com.bbllguru.wiki.page.util.PropertiesFactory;
import com.bbllguru.wiki.page.writer.FileWriterWrapper;

import java.util.concurrent.TimeUnit;

public class Loader {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage: java -jar [jar file] [path to the file directory] [path to the source file for list of wiki article titles]");
			System.exit(0);
		}
		String baseDir = args[0], dataFile = args[1];

		RunnerConfig.initEnvironment(baseDir);
		String dataDir = baseDir + "/" + PropertiesFactory.getProperties("config").getProperty("wiki.data.json.old.dir");

		// Hardcode "en" wiki for now
		FileReaderWrapper fr = new FileReaderWrapper(dataFile);
		try {
			//String line = pp.getNextPage();
			String line = fr.getNextLine();
			while (line != null) {
				System.out.println("Getting data for page: " + line);
				ApiWikiPageReader wr = new ApiWikiPageReader("en");
				String json = wr.fetchContent(line);
				FileWriterWrapper w = new FileWriterWrapper(dataDir + "/" + HashStore.hash("MD5", line));
				w.write(json);
				line = fr.getNextLine();

				// Wait for 3 seconds so not to get blocked by Wikipedia
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			fr.close();
		} catch (AccessFileException e) {
			e.printStackTrace();
		}
	}

}