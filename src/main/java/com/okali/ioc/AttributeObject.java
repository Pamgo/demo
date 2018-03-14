package com.okali.ioc;

/**
 * bean标签内内的<property>属性标签值
 * @author OKali
 *
 */
public class AttributeObject {

	private String name;
	
	private String ref;
	
	public AttributeObject(String name, String ref) {
		this.name = name;
		this.ref = ref;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
}
