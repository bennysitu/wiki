package com.bbllguru.wiki.page.parser;

import org.json.JSONException;

public class TitleParser extends AbstractParser {

	protected String title;

	public TitleParser(String json) throws JSONException {
		super(json);
	}

	public void parse() throws JSONException {
		title = jsonObj.getJSONObject("parse").getString("title");
	}

	public String getTitle() {
		return title;
	}
}
