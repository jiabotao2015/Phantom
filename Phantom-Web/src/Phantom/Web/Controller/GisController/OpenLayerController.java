package Phantom.Web.Controller.GisController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpenLayerController {
	
	@RequestMapping(value = "/MainMap")
	public String MainMapUrl(){
		return "GIS/MainMap";
	}
	
	@RequestMapping(value = "/BingMap")
	public String BingMapUrl(){
		return "GIS/BingMap";
	}

}
