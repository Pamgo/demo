package com.okali.ioc;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

/**
 * 读取配置文件，然后解析建立bean与属性的集合
 * @author OKali
 *
 */
public class SelfClassPathXMLApplicationContext {

	private List<BeanObject> beanObjects = new ArrayList<BeanObject>();
	
	private Map<String, Object> sigletons = new HashMap<String, Object>();
	
	public SelfClassPathXMLApplicationContext(String name) {
		readXml(name);
		instanceBeans();
		injectObejct();
	}

	/**
	 * 注入bean属性
	 */
	private void injectObejct() {
		for(BeanObject beanObject : beanObjects) {
			Object bean = sigletons.get(beanObject.getBeanId());
			if (bean != null) {
				try {
					// Introspector 是一个专门处理bean的工具类.
					// 用来获取Bean体系里的 propertiesDescriptor,methodDescriptor.
					PropertyDescriptor[] pds = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
					for (AttributeObject property : beanObject.getAttributeObjects()) {
						
						for (PropertyDescriptor pDescriptor : pds) {
							if (property.getName().equals(pDescriptor.getName())) {
								Method setter = pDescriptor.getWriteMethod(); // 获得属性的setter方法
								if (setter != null) {
									Object value = sigletons.get(property.getRef());
									setter.setAccessible(true);
									setter.invoke(bean, value); // 把引用对象注入属性
								}
								break;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 实例化bean对象
	private void instanceBeans() {
		for (BeanObject bean : beanObjects) {
			try {
				String clazz = bean.getClassName().trim();
				if (clazz != null && !"".equals(clazz)) {
					sigletons.put(bean.getBeanId(), Class.forName(clazz).newInstance());
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取xml配置文件
	 * @param name
	 */
	private void readXml(String name) {
		// 创建xml解析对象
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			// 读取xml，从classpath根下读取
			URL xmlPath = this.getClass().getClassLoader().getResource(name);
			document = reader.read(xmlPath);	 // 读取xml，获得document对象
			// 存放命名空间
			Map<String, String> nsMap = new HashMap<String, String>();
			// 加入命名空间
			nsMap.put("ns", "http://www.springframework.org/schema/beans");
			//创建beans/bean查询路径
			XPath xsub = document.createXPath("//ns:beans/ns:bean");
			// 设置命名空间
			xsub.setNamespaceURIs(nsMap);
			// 获取文档下的所有bean节点
			List<Element> beanElements = xsub.selectNodes(document);
			// bean
			for (Element beanElement : beanElements) { 
				// 获取beanId属性
				String beanId = beanElement.attributeValue("id");
				// 获取bean的class
				String className = beanElement.attributeValue("class");
				BeanObject bean = new BeanObject(beanId, className);
				XPath propertySub = beanElement.createXPath("//ns:property");
				// 设置命名空间
				propertySub.setNamespaceURIs(nsMap);
				List<Element> propertysEle = propertySub.selectNodes(document);
				// property
				for (Element property : propertysEle) {
					String propertyName = property.attributeValue("name");
					String propertyRef = property.attributeValue("ref");
					AttributeObject attributeObject = new AttributeObject(propertyName, propertyRef);
					// bean对应的属性集合
					bean.getAttributeObjects().add(attributeObject);
				}
				// bean集合
				beanObjects.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * getBean:(获取bean实例). <br/> 
	 * @author pangyc
	 * @date 2018年3月14日上午11:04:49
	 * @param name
	 * @return
	 */
	public Object getBean(String name) {
		return sigletons.get(name);
	}
}
