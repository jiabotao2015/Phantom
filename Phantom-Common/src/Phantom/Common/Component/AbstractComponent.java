package Phantom.Common.Component;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Phantom.Common.Util.XMLUtil.XmlElement;
import Phantom.Common.Util.XMLUtil.XmlParser;

public class AbstractComponent implements IConponent {
	
	private static Log log = LogFactory.getLog(AbstractComponent.class);
	
	protected XmlParser xmlConfig;
	
	protected XmlElement rootElement;
	
	//线程名字
	protected String threadConfig;

	@Override
	public boolean start() {
		return false;
	}

	@Override
	public boolean stop() {
		return false;
	}

	@Override
	public boolean init(String[] params) {
		
		/*try {
			xmlConfig = new XmlParser(new FileReader(params[0]));
		} catch (FileNotFoundException e) {
			log.error("解析XML失败");
			e.printStackTrace();
			return false;
		}
		try {
			rootElement = xmlConfig.parse();
		} catch (XmlException e) {
			log.error("解析XML失败");
			e.printStackTrace();
			return false;
		}
		threadConfig = rootElement.getAttribute("threadConfig");
		String[] threadInfo = threadConfig.split("\\|");*/
		log.info(params[0]);
		System.out.println(params[0]);
		return true;
	}

}
