package com.bbllguru.wiki.page.transform.rule;

import org.junit.Assert;
import org.junit.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.bbllguru.wiki.page.component.SectionHeaderComponent;
import com.bbllguru.wiki.page.exception.TransformException;

public class SectionHeaderRuleTest {

	@Test
	public void testApply() {
		Element sectionheader = Jsoup.parse("<h2><span class=\"mw-headline\">test section</span></h2>").select("h2").first();
		SectionHeaderComponent c = new SectionHeaderComponent(sectionheader);
		SectionHeaderRule rule = new SectionHeaderRule();
		rule.apply(c);
		Assert.assertTrue(c.getElement().nodeName() == "span");
	}

	@Test(expected=TransformException.class)
	public void testApplyWithException() {
		Element sectionheader = Jsoup.parse("<h2><span class=\"mw-headline\">test section</span><span class=\"mw-headline\">test section 2</span></h2>").select("h2").first();
		SectionHeaderComponent c = new SectionHeaderComponent(sectionheader);
		SectionHeaderRule rule = new SectionHeaderRule();
		rule.apply(c);
		Assert.assertTrue(c.getElement().nodeName() == "span");
	}

}
