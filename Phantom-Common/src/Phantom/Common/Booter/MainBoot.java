package Phantom.Common.Booter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Phantom.Common.Component.AbstractComponent;
import Phantom.Common.Util.XMLUtil.XmlElement;
import Phantom.Common.Util.XMLUtil.XmlException;
import Phantom.Common.Util.XMLUtil.XmlParser;

public class MainBoot extends BootTools implements IBoot{
	
	private static Log log = LogFactory.getLog(MainBoot.class);
	
	protected ArrayList<Map<String,String>> components_list = new ArrayList<Map<String,String>>();
	
	protected Map<String,AbstractComponent> components_map = new HashMap<String,AbstractComponent>();;

	@Override
	public boolean init(String[] params) throws FileNotFoundException, XmlException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		//获取rootElement，其中应该包含线程池策略，组件信息等
		
		xmlConfig = new XmlParser(new FileReader(params[0] + params[1]));
		rootElement = xmlConfig.parse();
		
		//rootElement
		//获取组件并初始化
		getComponentXMLEliment(rootElement);
		boolean component_is_inited = initEachComponent(components_list);
		return component_is_inited;
	}

	

	@Override
	public boolean start() {
		
		return false;
	}

	@Override
	public boolean stop() {
		Set<Entry<String, AbstractComponent>> Component_Set = components_map.entrySet();
		Iterator<Entry<String, AbstractComponent>>  Component_Iter = Component_Set.iterator();
		while(Component_Iter.hasNext()){
			Entry<String, AbstractComponent> entry = Component_Iter.next();
			AbstractComponent currentComponent = entry.getValue();
			currentComponent.stop();
		}
		return true;
	}
	
	//解析component，一个组件对应一个配置文件和一个组件类
	private void getComponentXMLEliment(XmlElement rootElement) {
		List<XmlElement> component_xml_List = rootElement.getChildElements("component");
		for(int i=0;i<component_xml_List.size();i++){
			XmlElement element = component_xml_List.get(i);
			String name = element.getAttribute("name");
			String filename = element.getAttribute("fileName");
			Map<String,String> componentbean = new HashMap<String,String>();
			componentbean.put("Name", name);
			componentbean.put("FileName",filename);
			components_list.add(componentbean);
		}
		
	}

	private boolean initEachComponent(ArrayList<Map<String, String>> components){
		boolean compoent_is_inited = false;
		// 逐个初始化组件，并加入到本地map中做保存
		for(int i=0;i<components.size();i++){
			Map<String, String> component_map = components.get(i);
			String component_classname = component_map.get("Name");
			String filename = component_map.get("FileName");
			AbstractComponent component = null;
			try {
				component = (AbstractComponent) Class.forName(component_classname).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			//init component set 各种值，外加配置文件
			log.info("开始初始化： "+component_classname+" 组件");
			compoent_is_inited = component.init(new String[] {("/component/" + filename)});
			if(compoent_is_inited){
				//存入一个map可以拱关闭使用
				components_map.put(component_classname, component);
				log.info("组件: "+component_classname+" 初始化成功");
			}
		}
		return compoent_is_inited;
		
	}

	

}
