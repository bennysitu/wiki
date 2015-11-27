package com.bbllguru.wiki.page.parser;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;

public class BlockParser extends AbstractParser {

	protected ArrayList<Element> blocks;

	public BlockParser(String json) throws JSONException {
		super(json);
		blocks = new ArrayList<Element>();
	}

	public ArrayList<Element> getBlocks() {
		return blocks;
	}

	public void parse() throws JSONException {
		String textContent = jsonObj.getJSONObject("parse").getJSONObject("text").getString("*");
		Document doc = Jsoup.parse("<div id='mw-content-text'>" + textContent + "</div>");

		for (Element e: doc.select("div#mw-content-text > p, div#mw-content-text > h2, div#mw-content-text > div, div#mw-content-text > table, div#mw-content-text > ul, div#mw-content-text > ol")) {
			blocks.add(e);
		}
	}

}
