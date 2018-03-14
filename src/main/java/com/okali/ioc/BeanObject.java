package com.okali.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * bean标签对应的id和class
 * @author OKali
 *
 */
public class BeanObject {
	
	private String beanId;
	
	private String className;

	private List<AttributeObject> attributeObjects = new ArrayList<AttributeObject>();
	
	public BeanObject(String beanId, String className) {
		this.beanId = beanId;
		this.className = className;
	}
	
	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<AttributeObject> getAttributeObjects() {
		return attributeObjects;
	}

	public void setAttributeObjects(List<AttributeObject> attributeObjects) {
		this.attributeObjects = attributeObjects;
	}
	
	

}
