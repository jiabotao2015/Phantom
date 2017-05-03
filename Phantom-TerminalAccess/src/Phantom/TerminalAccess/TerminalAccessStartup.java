package Phantom.TerminalAccess;

import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Phantom.Common.Booter.MainBoot;
import Phantom.Common.Util.XMLUtil.XmlException;

public class TerminalAccessStartup extends MainBoot {
	
	private static Log log = LogFactory.getLog(TerminalAccessStartup.class);
	
	public static void main(String[] args) throws FileNotFoundException, XmlException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		String serverPath = System.getProperty("server.config.path");
		String serverFile = System.getProperty("server.config.file");
		TerminalAccessStartup server = new TerminalAccessStartup();
		String[] params = new String[]{serverPath,serverFile}; 
		boolean is_inited = server.init(params);
		System.out.println(is_inited);
		if(!is_inited){
			log.error("初始化失败");
			System.exit(0);
		}
	}

}
