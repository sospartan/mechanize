package com.gistlabs.mechanize.json.node.impl;

import java.util.*;

import com.gistlabs.mechanize.json.node.JsonNode;
import com.gistlabs.mechanize.util.css_query.NodeHelper;


public class JsonNodeHelper implements NodeHelper<JsonNode> {
	
	private JsonNode root;

	public JsonNodeHelper(JsonNode root) {
		this.root = root;
	}

    public String getValue(JsonNode element) {
		return element.getValue();
	}
	
	public boolean hasAttribute(JsonNode element, String name) {
		return element.hasAttribute(name);
	}
	
	public String getAttribute(JsonNode element, String name) {
		if (element.hasAttribute(name))
			return element.getAttribute(name);
		else 
			return null;
	}
    
    public Collection<? extends JsonNode> getDescendentNodes(JsonNode node) {
    	Collection<JsonNode> result = new LinkedHashSet<JsonNode>();
    	result.add(node);
    	
    	LinkedList<JsonNode> toProcess = new LinkedList<JsonNode>();
    	toProcess.add(node);
    	while(!toProcess.isEmpty()) {
    		JsonNode first = toProcess.removeFirst();
    		List<? extends JsonNode> children = first.getChildren();
    		result.addAll(children);
    		toProcess.addAll(children);
    	}
    	
    	return result;
	}
    
    public List<? extends JsonNode> getChildNodes(JsonNode node) {
		return node.getChildren();
	}
    
    @Override
    public boolean isEmpty(JsonNode node) {
    	return getChildNodes(node).isEmpty();
    }

    public String getName(JsonNode n) {
		return n.getName();
	}
    
    public JsonNode getNextSibling(JsonNode node) {
    	throw new UnsupportedOperationException("Haven't implemented this yet");
    	//DOMHelper.getNextSiblingElement(node);
		// TODO Auto-generated method stub
	}

    @SuppressWarnings("unchecked")
	public Index getIndexInParent(JsonNode node, boolean byType) {
		String type = byType ? node.getName() : "*";
		
		List<? extends JsonNode> children;
		JsonNode parent = node.getParent();
		if (parent==null)
			children = Collections.EMPTY_LIST;
		else
			children = parent.getChildren(type);

		return new Index(children.indexOf(node), children.size());
	}

	public JsonNode getRoot() {
		return this.root;
	}
	
	@Override
	public boolean nameMatches(JsonNode n, String name) {
		return "*".equals(name) || n.getName().equalsIgnoreCase(name);
	}	
}