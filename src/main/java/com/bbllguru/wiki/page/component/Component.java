package com.bbllguru.wiki.page.component;

import java.util.ArrayList;

import org.jsoup.nodes.Element;

import com.bbllguru.wiki.page.transform.rule.Rule;
import com.bbllguru.wiki.page.transform.Transformable;
import com.bbllguru.wiki.page.transform.Transformer;

/**
 * Abstract component object
 */
abstract public class Component implements Transformable {

	/**
	 * The component name
	 */
	protected String name;

	/**
	 * The Element object of the component
	 */
	protected Element element;

	/**
	 * List of transform rule
	 */
	protected ArrayList<Rule> rules = new ArrayList<Rule>();

	/**
	 * @param name The component name
	 * @param element The Element object of the component
	 */
	public Component(String name, Element element) {
		this.name = name;
		this.element = element;
	}

	/**
	 * Check if this is an empty element.  For example, "<p></p>" should be considered as empty
	 * @return boolean
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Perform a transform with the rules
	 */
	public void transform(Transformer t) {
		t.setComponent(this);
		t.transform(rules);
	}

	/**
	 * Add rule
	 * @param rule
	 */
	public void addRule(Rule rule) {
		rules.add(rule);
	}

	//Getter & Setter methods
	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Element getElement() {
		return element;
	}
	
	public void setElement(Element element) {
		this.element = element;
	}

}
