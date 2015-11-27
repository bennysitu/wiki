package com.bbllguru.wiki.page.component;

import org.jsoup.nodes.Element;

public class SectionHeaderComponent extends Component {

	public SectionHeaderComponent(Element element) {
		super("sectionheader", element);
	}

	public static boolean detect(Element e) {
		if (e.nodeName().equals("h2") && !e.getElementsByClass("mw-headline").isEmpty()) {
			return true;
		}
		return false;
	}

}
