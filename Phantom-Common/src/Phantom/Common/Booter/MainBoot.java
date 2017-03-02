package Phantom.Common.Booter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import Phantom.Common.Util.XMLUtil.XmlElement;
import Phantom.Common.Util.XMLUtil.XmlException;
import Phantom.Common.Util.XMLUtil.XmlParser;

public class MainBoot extends BootTools implements IBoot{

	@Override
	public boolean init(String[] params) throws FileNotFoundException, XmlException {
		// TODO Auto-generated method stub
		FileReader file_reader = new FileReader(params[0] + params[1]);
		xmlConfig = new XmlParser(new FileReader(params[0] + params[1]));
		rootElement = xmlConfig.parse();
		String threadConfig = rootElement.getAttribute("threadConfig");
		String serverName = rootElement.getAttribute("name");
		
		List<XmlElement> connectList = rootElement.getChildElements("connectname");
		for(int i=0;i<connectList.size();i++){
			XmlElement xmlElementTem = connectList.get(i);
			connectNames = xmlElementTem.getAttribute("value").split("\\|");
			System.out.println(connectNames);
			System.out.println(i);
		}
		return false;
	}

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

	

	

}
