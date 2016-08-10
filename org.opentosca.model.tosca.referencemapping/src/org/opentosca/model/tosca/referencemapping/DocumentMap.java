package org.opentosca.model.tosca.referencemapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.opentosca.core.model.csar.id.CSARID;
import org.w3c.dom.Document;

/**
 * Copyright 2012 IAAS University of Stuttgart <br>
 * <br>
 * 
 * @author "Matthias Fetzer - fetzerms@studi.informatik.uni-stuttgart.de"
 * 
 */
public class DocumentMap implements Map<CSARID, Map<QName, Document>> {

	private Map<CSARID, Map<QName, Document>> documentMapMap = new HashMap<CSARID, Map<QName, Document>>();

	@Override
	public void clear() {

		this.documentMapMap.clear();
	}

	@Override
	public boolean containsKey(Object key) {

		return this.documentMapMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {

		return this.documentMapMap.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<CSARID, Map<QName, Document>>> entrySet() {

		return this.documentMapMap.entrySet();
	}

	@Override
	public Map<QName, Document> get(Object key) {

		return this.documentMapMap.get(key);
	}

	@Override
	public boolean isEmpty() {

		return this.documentMapMap.isEmpty();
	}

	@Override
	public Set<CSARID> keySet() {

		return this.documentMapMap.keySet();
	}

	@Override
	public Map<QName, Document> put(CSARID key, Map<QName, Document> value) {

		Map<QName, Document> result = this.documentMapMap.put(key, value);
		return result;
	}

	@Override
	public void putAll(Map<? extends CSARID, ? extends Map<QName, Document>> m) {

		this.documentMapMap.putAll(m);
	}

	@Override
	public Map<QName, Document> remove(Object key) {

		Map<QName, Document> result = this.documentMapMap.remove(key);
		return result;
	}

	@Override
	public int size() {

		return this.documentMapMap.size();
	}

	@Override
	public Collection<Map<QName, Document>> values() {

		return this.documentMapMap.values();
	}

	public Map<CSARID, Map<QName, Document>> getDocumentMap() {

		return this.documentMapMap;
	}

	public void setDocumentMap(Map<CSARID, Map<QName, Document>> documentMap) {

		this.documentMapMap = documentMap;
	}
}