package com.bbllguru.wiki.page.parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Parser for extracting wiki components which are used for transform
 */
abstract public class AbstractParser {

	protected JSONObject jsonObj;

	public AbstractParser(String json) throws JSONException {
		this.jsonObj = new JSONObject(json);
	}

	abstract public void parse() throws JSONException;

}
