package com.bbllguru.wiki.page.component;

import org.junit.Test;
import org.junit.Assert;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class ComponentFactoryTest {

	protected Element infobox = Jsoup.parse("<table class=\"infobox\"><tr><td>data 1</td><td>data 2</td></tr></table>").select("table").first();
	protected Element paragraph = Jsoup.parse("<p><div>data</div></p>").select("p").first();
	protected Element sectionheader = Jsoup.parse("<h2><span class=\"mw-headline\">test section</span></h2>").select("h2").first();
	protected Element invalidElement = Jsoup.parse("<invalid-element>invalid test</invalid-element>").select("invalid-element").first();

	@Test
	public void testDetectInfoBoxComponent() {
		Component c = ComponentFactory.detectComponent(infobox);
		Assert.assertTrue(c instanceof InfoBoxComponent);
	}

	@Test
	public void testDetectParagraphComponent() {
		Component c = ComponentFactory.detectComponent(paragraph);
		Assert.assertTrue(c instanceof ParagraphComponent);
	}

	@Test
	public void testDetectSectionHeaderComponent() {
		Component c = ComponentFactory.detectComponent(sectionheader);
		Assert.assertTrue(c instanceof SectionHeaderComponent);
	}

	@Test
	public void testInvalidComponent() {
		Component c = ComponentFactory.detectComponent(invalidElement);
		Assert.assertNull(c);
	}

}
