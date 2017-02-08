package Phantom.Common.Util.XMLUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author jiabotao
 *
 */
public class XmlElement implements Serializable{

	private static final long serialVersionUID = -1855105264673257409L;
	
	private String name;  //xml（父）节点名称
	
	private HashMap<String,String> attributes;  //xml(父)节点属性键值对
	
	private ArrayList<XmlElement> content;  //xml父节点包含的所有内容
	
	private HashMap<String,ArrayList<XmlElement>> children; //子节点，同名子节点放入一个List
	
	public XmlElement(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	
	/**
	 * @Description 获取属性值
	 */
	public String getAttributes(String attributeName){
		return this.attributes.get(attributeName);
	}
	
	/**
	 * @Description 删除属性
	 */
	public void removeAttribute(String attributeName) {
		this.attributes.remove(attributeName);
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<XmlElement> getContent() {
		return content;
	}

	public void setContent(ArrayList<XmlElement> content) {
		this.content = content;
	}

	public HashMap<String, ArrayList<XmlElement>> getChildren() {
		return children;
	}

	public void setChildren(HashMap<String, ArrayList<XmlElement>> children) {
		this.children = children;
	}
	
	/**
	 * @Description 按名获取子节点
	 * @param childName
	 * @return
	 */
	public XmlElement getChildElement(String childName){
		XmlElement child = null;
		ArrayList<XmlElement> namedChildren = children.get(childName);
		if(namedChildren != null && namedChildren.size()==1){
			child = namedChildren.iterator().next();
		}
		return child;
	}
	
}
