package com.bbllguru.wiki.page.transform.rule;

import org.jsoup.select.Elements;

import com.bbllguru.wiki.page.exception.TransformException;
import com.bbllguru.wiki.page.component.Component;

public class SectionHeaderRule implements Rule {

	public void apply(Component component) {
		Elements es = component.getElement().select("h2 > span[class=mw-headline]");
		if (es.size() != 1) {
			throw new TransformException("Could not transform element, more than 1 header is detected, Detail:" + component.getElement().toString());
		}
		component.setElement(es.first());
	}

}
