package com.bbllguru.wiki.page.writer;

import com.bbllguru.wiki.page.component.Component;
import com.bbllguru.wiki.page.exception.BaseException;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONStringer;
import org.json.JSONWriter;
import org.json.JSONException;

public class PageJSONWriter {

	protected LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

	protected String title;

	// Default section is "Main section"
	protected String curSection = "Main section";

	protected JSONWriter jsonWriter = new JSONStringer();
	
	public void addComponent(Component component) {
		if (component.getName().equals("sectionheader")) {
			curSection = component.getElement().html();
		}
		if (!map.containsKey(curSection)) {
			map.put(curSection, component.getElement().toString());
		} else {
			String data = map.get(curSection);
			map.put(curSection, data + "\n" + component.getElement().toString());
		}
	}

	public void addHTML(String html) {
		if (!map.containsKey(curSection)) {
			map.put(curSection, html);
		} else {
			String data = map.get(curSection);
			map.put(curSection, data + "\n" + html);
		}
	}

	public void addTitle(String title) {
		this.title = title;
	}

	public String write() {
		try {
			JSONWriter writer = jsonWriter.object();
			if (title != null) {
				writer.key("title").value(title);
			}
			writer = writeSections(writer.key("sections").object());
			writer.endObject();
			return writer.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			throw new BaseException("Unable to write content to JSON");
		}
	}
	
	protected JSONWriter writeSections(JSONWriter w) throws JSONException {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			w.key(key).value(value);
		}
		w.endObject();
		return w;
	}
}
