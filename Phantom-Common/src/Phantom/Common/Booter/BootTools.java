package Phantom.Common.Booter;

import Phantom.Common.Util.XMLUtil.XmlElement;
import Phantom.Common.Util.XMLUtil.XmlParser;

public abstract class BootTools {
	
	public  Integer testid;
	
	protected XmlElement rootElement;
	
	protected XmlParser xmlConfig;// xml解析对象
	
	protected String[] connectNames;

}
