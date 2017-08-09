package Phantom.ReverseGeocoding.WebService;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface IReverseEncodingService {
	
	@WebMethod
	public String reverse();

}
