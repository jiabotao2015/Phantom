package Phantom.Common.Util.XMLUtil;

import java.util.ArrayList;
import java.util.Collection;

public class XmlException extends Exception {

	private static final long serialVersionUID = -3908851703920751955L;
	
	private Collection<String> errors = null;
	
	public XmlException() {
		this.errors = new ArrayList<String>(0);
	}
	
	public XmlException(String msg) {
		super(msg);
		this.errors = new ArrayList<String>(1);
		this.errors.add(msg);
	}
	
	public XmlException(Collection<String> errors) {
		super(errors.toString());
		this.errors = errors;
	}
	
	public Collection<String> getErrors() {
		return this.errors;
	}

}
