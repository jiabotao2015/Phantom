package Phantom.TerminalAccess;

import java.io.FileNotFoundException;

import Phantom.Common.Booter.MainBoot;
import Phantom.Common.Component.AbstractComponent;
import Phantom.Common.Util.XMLUtil.XmlException;
import Phantom.TerminalAccess.Component.TestComponent;

public class TerminalAccessStartup extends MainBoot {
	
	public static void main(String[] args) throws FileNotFoundException, XmlException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		String serverPath = System.getProperty("server.config.path");
		String serverFile = System.getProperty("server.config.file");
		TerminalAccessStartup server = new TerminalAccessStartup();
		String[] params = new String[]{serverPath,serverFile}; 
		boolean is_inited = server.init(params);
		System.out.println(is_inited);
	}

}
