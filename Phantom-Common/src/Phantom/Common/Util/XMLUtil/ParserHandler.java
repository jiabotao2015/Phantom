package Phantom.Common.Util.XMLUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @Project CNPC_VMS
 * @Title ParserHandler
 * @Description 
 * @author guanhl
 * @date 2014年8月7日 上午9:20:39
 * @company Beijing Huayou Information andCommunication Technology Co.,Ltd
 * @Copyright Copyright (c) 2014
 * @version V2.0
 */
public class ParserHandler extends DefaultHandler {

	// 错误集合
	private Collection<String> errors = new ArrayList<String>();
	// 元素集合
	private LinkedList<XmlElement> elementStack = new LinkedList<XmlElement>();
	// 根元素
	private XmlElement rootElement = null;
	// 日志
	private static Log log = LogFactory.getLog(ParserHandler.class.getName());

	/**
	 * @Description 开始元素
	 * @param namespaceURI 命名空间
	 * @param localName 本地名
	 * @param qName 标签名
	 * @param atts 元素
	 */
	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
		// 创建元素
		XmlElement newElement = new XmlElement(qName);
		// 属性map
		Map<String, String> attributeMap = new HashMap<String, String>();
		// 属性map
		for (int i = 0; i < atts.getLength(); i++) {
			attributeMap.put(atts.getQName(i), atts.getValue(i));
		}

		// 设置属性
		newElement.setAttributes(attributeMap);

		// 判断是否追加到上一个结点
		int elementStackSize = elementStack.size();
		if (elementStackSize > 0) {
			XmlElement containingElement = (XmlElement) elementStack.getLast();
			containingElement.addChild(newElement);
		} else {
			rootElement = newElement;
		}

		elementStack.add(newElement);
	}

	/**
	 * @Description 移出子节点
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName) {
		elementStack.removeLast();
	}

	/**
	 * @Description 结点内容
	 */
	/*@Override
	public void characters(char[] ch, int start, int length) {
		String text = new String(ch, start, length).trim();
		if (!"".equals(text)) {
			XmlElement element = (XmlElement) elementStack.getLast();
			element.addText(text);
		}
	}*/

	/**
	 * @Description 获取结点
	 */
	public XmlElement getRootElement() {
		return rootElement;
	}

	/**
	 * @Description dtd验证
	 */
	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
		InputSource dtdInputSource = null;

		log.debug("resolving entity : " + publicId);

		try {
			if ("jbpm/processdefinition_1_0".equals(publicId)) {
				// jbpm/processdefinition_1_0 dtd
				dtdInputSource = new InputSource(ParserHandler.class.getResourceAsStream("/processdefinition.dtd"));
			} else if ("jbpm/webinterface_1_0".equals(publicId)) {
				// jbpm/webinterface_1_0 dtd
				dtdInputSource = new InputSource(ParserHandler.class.getResourceAsStream("/webinterface.dtd"));
			}
		} catch (RuntimeException e) {
			log.error("cannot resolve entity ", e);
		}

		return dtdInputSource;
	}

	/**
	 * @Description 加入错误
	 */
	@Override
	public void error(SAXParseException e) {
		errors.add(e.getMessage());
	}

	/**
	 * @Description 加入错误
	 */
	@Override
	public void fatalError(SAXParseException e) {
		errors.add(e.getMessage());
	}

	/**
	 * @Description 是否有错误
	 */
	public boolean hasErrors() {
		return (errors.size() > 0);
	}

	/**
	 * @Description 获取错误
	 */
	public Collection<String> getErrors() {
		return errors;
	}

}
