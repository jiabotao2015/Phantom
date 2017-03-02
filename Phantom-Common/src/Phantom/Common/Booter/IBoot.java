package Phantom.Common.Booter;

import java.io.FileNotFoundException;

import Phantom.Common.Util.XMLUtil.XmlException;

public interface IBoot{
	
	boolean init(String[] params) throws FileNotFoundException, XmlException;
	
	boolean start();
	
	boolean stop();
}
