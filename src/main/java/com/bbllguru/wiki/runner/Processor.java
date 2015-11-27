package com.bbllguru.wiki.runner;

import com.bbllguru.wiki.page.component.Component;
import com.bbllguru.wiki.page.component.ComponentFactory;
import com.bbllguru.wiki.page.transform.Transformer;
import com.bbllguru.wiki.page.exception.ParseContentException;
import com.bbllguru.wiki.page.util.HashStore;
import com.bbllguru.wiki.page.util.PropertiesFactory;
import com.bbllguru.wiki.page.parser.BlockParser;
import com.bbllguru.wiki.page.parser.TitleParser;
import com.bbllguru.wiki.page.writer.FileWriterWrapper;
import com.bbllguru.wiki.page.writer.PageJSONWriter;

import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.jsoup.nodes.Element;

public class Processor {

	public static void main(String[] args) {
		try {
			if (args.length < 1) {
				System.out.println("Usage: java -jar [jar file] [base directory]");
				System.exit(0);
			}
			String baseDir = args[0];
			RunnerConfig.initEnvironment(baseDir);
			Properties p = PropertiesFactory.getProperties("config");
			String dataDir = baseDir + "/" + p.getProperty("wiki.data.json.old.dir");
			String destDir = baseDir + "/" + p.getProperty("wiki.data.json.new.dir");

			File folder = new File(dataDir);
			File[] files = folder.listFiles();
			
			for (File file : files) {
				String content = new String(Files.readAllBytes(Paths.get(dataDir + "/" + file.getName())));
				TitleParser tp = new TitleParser(content);
				tp.parse();
				System.out.println("Title: " + tp.getTitle() + "\n\n");

				BlockParser sp = new BlockParser(content);
				sp.parse();

				PageJSONWriter jw = new PageJSONWriter();
				jw.addTitle(tp.getTitle());

				for (Element e: sp.getBlocks()) {
					boolean detected = false;
					Component component = ComponentFactory.detectComponent(e);
					if (component != null) {
						component.transform(new Transformer());
						System.out.println("After transform: ");
						System.out.println("Component: " + component.getName());
						System.out.println("Content: " + component.getElement().toString());
						System.out.println("\n");
						detected = true;
						if (!component.isEmpty()) {
							System.out.println("Include in output");
							jw.addComponent(component);
							System.out.println("\n\n");
						}
					}
					if (!detected) {
						jw.addHTML(e.toString());
						System.out.println("No transform: ");
						System.out.println("Element: " + e.toString() + "\n\n");
					}
				}

				FileWriterWrapper w = new FileWriterWrapper(destDir + "/" + HashStore.hash("MD5", tp.getTitle()));
				w.write(jw.write());
				break;
			}
		} catch(JSONException e) {
			e.printStackTrace();
		} catch(ParseContentException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}