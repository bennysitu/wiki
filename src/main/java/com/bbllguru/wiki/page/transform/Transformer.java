package com.bbllguru.wiki.page.transform;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import com.bbllguru.wiki.page.component.Component;
import com.bbllguru.wiki.page.transform.rule.Rule;

/**
 * Generic component transformer
 */
public class Transformer {

	protected Component component;

	public Transformer() {}

	public Transformer(Component component) {
		this.component = component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	/**
	 * Perform first pass of transformer
	 */
	public void transform(ArrayList<Rule> rules) {
		// Apply the pre-defined transform rules
		for (Rule rule : rules) {
			rule.apply(component);
		}
		// Process the steps for generic transform
		transformWikiLink();
		removeWikiAttributes(new String[]{"class", "style", "id"});
	}

	/**
	 * Convert /wiki/Foo_bar to {$Foo_bar$}
	 */
	protected void transformWikiLink() {
		NodeTraversor traversor = new NodeTraversor(new NodeVisitor() {
			public void tail(Node node, int depth) {
				if (node instanceof Element) {
					Element e = (Element)node;
					if (e.attr("href") != "") {
						String href = e.attr("href");
						Pattern r = Pattern.compile("^/wiki/(.*)$");
						Matcher m = r.matcher(href);
						if (m.find()) {
							href = "{$" + m.group(1) + "$}";
							e.attr("href", href);
						}
					}
				}
			}

			public void head(Node node, int depth) {
			}
		});

		traversor.traverse(component.getElement());
	}

	/**
	 * Remove the target attributes from element
	 * @param attributes List of attribute names
	 */
	protected void removeWikiAttributes(final String[] attributes) {
		NodeTraversor traversor = new NodeTraversor(new NodeVisitor() {
			public void tail(Node node, int depth) {
				if (node instanceof Element) {
					Element e = (Element)node;
					for (String attribute : attributes) {
						e.removeAttr(attribute);
					}
				}
			}

			public void head(Node node, int depth) {
			}
		});

		traversor.traverse(component.getElement());
	}

}
