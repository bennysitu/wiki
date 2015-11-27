package com.bbllguru.wiki.page.component;

import org.mockito.Mockito;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;

import com.bbllguru.wiki.page.transform.rule.ConcreteRule;

public class ComponentTest {

	@Test
	public void testAddRule() {
		Element e = Mockito.mock(Element.class);
		ConcreteComponent c = new ConcreteComponent("test", e);
		ConcreteRule r = Mockito.mock(ConcreteRule.class);
		c.addRule(r);
		Assert.assertFalse(c.getRules().isEmpty());
	}

}
