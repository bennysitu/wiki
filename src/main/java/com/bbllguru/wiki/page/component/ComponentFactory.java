package com.bbllguru.wiki.page.component;

import com.bbllguru.wiki.page.transform.rule.SectionHeaderRule;

import org.jsoup.nodes.Element;

public final class ComponentFactory {

	public static Component detectComponent(Element e) {
		if (InfoBoxComponent.detect(e)) {
			return new InfoBoxComponent(e);
		}
		if (SectionHeaderComponent.detect(e)) {
			SectionHeaderComponent component = new SectionHeaderComponent(e);
			component.addRule(new SectionHeaderRule());
			return component;
		}
		if (ParagraphComponent.detect(e)) {
			return new ParagraphComponent(e);
		}
		return null;
	}

}
