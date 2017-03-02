package Phantom.Common.Util.XMLUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserHandler extends DefaultHandler {

	private static Log logger = LogFactory.getLog(ParserHandler.class.getName());

	private Collection<String> errors = new ArrayList<String>();

	// 根元素
	private XmlElement rootElement = null;

	// 元素集合
	private LinkedList<XmlElement> elementStack = new LinkedList<XmlElement>();

	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {

		XmlElement newElement = new XmlElement(qName);

		HashMap<String, String> attributeMap = new HashMap<String, String>();
		for (int i = 0; i < atts.getLength(); i++) {
			attributeMap.put(atts.getQName(i), atts.getValue(i));
		}

		newElement.setAttributes(attributeMap);

		// 判断是否追加到上一个结点
		int elementStackSize = elementStack.size();
		if (elementStackSize > 0) {
			XmlElement containingElement = (XmlElement) elementStack.getLast();
			containingElement.setChildElement(newElement);
		} else {
			rootElement = newElement;
		}

		elementStack.add(newElement);
	}
	
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
		InputSource dtdInputSource = null;

		logger.debug("resolving entity : " + publicId);

		try {
			if ("jbpm/processdefinition_1_0".equals(publicId)) {
				// jbpm/processdefinition_1_0 dtd
				dtdInputSource = new InputSource(ParserHandler.class.getResourceAsStream("/processdefinition.dtd"));
			} else if ("jbpm/webinterface_1_0".equals(publicId)) {
				// jbpm/webinterface_1_0 dtd
				dtdInputSource = new InputSource(ParserHandler.class.getResourceAsStream("/webinterface.dtd"));
			}
		} catch (RuntimeException e) {
			logger.error("cannot resolve entity ", e);
		}

		return dtdInputSource;
	}
	
	public XmlElement getRootElement() {
		return rootElement;
	}
	
	public void error(SAXParseException e) {
		errors.add(e.getMessage());
	}

	public void fatalError(SAXParseException e) {
		errors.add(e.getMessage());
	}

	/**
	 * @Description 是否有错误
	 * @return boolean has_errors
	 */
	public boolean hasErrors() {
		return (errors.size() > 0);
	}
	
	/**
	 * @Description 获取错误
	 * @return Collection<String> errors
	 */
	public Collection<String> getErrors() {
		return errors;
	}
}
