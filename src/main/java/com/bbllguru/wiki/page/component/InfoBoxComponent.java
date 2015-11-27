package com.bbllguru.wiki.page.component;

import org.jsoup.nodes.Element;

/**
 * InfoBox component
 *
 */
public class InfoBoxComponent extends Component {
	
	public InfoBoxComponent(Element element) {
		super("infobox", element);
	}

	public static boolean detect(Element e) {
		if (e.nodeName().equals("table") && e.hasClass("infobox")) {
			return true;
		}
		return false;
	}

}
