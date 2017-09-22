package Phantom.ReverseGeocoding.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping(value = "/MainMap")
	public String MainMap(){
		return "MainMap";
	}

}
