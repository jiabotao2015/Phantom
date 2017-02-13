package Phantom.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	
	@RequestMapping(value = "/admin/blogs/add")
	public String Login(){
		return "Login";
	}

}
