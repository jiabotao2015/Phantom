package Phantom.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	
	@RequestMapping(value = "/Login")
	public String Login(){
		return "Login";
	}

}
