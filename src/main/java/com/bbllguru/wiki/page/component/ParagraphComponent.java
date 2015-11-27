package com.bbllguru.wiki.page.component;

import org.jsoup.nodes.Element;

public class ParagraphComponent extends Component {

	public ParagraphComponent(Element element) {
		super("paragrah", element);
	}

	@Override
	public boolean isEmpty() {
		if (element.html().trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean detect(Element e) {
		if (e.nodeName().equals("p")) {
			return true;
		}
		return false;
	}

}
