package Phantom.Web.Controller.GisController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpenLayerController {
	
	private static Logger logger = Logger.getLogger(OpenLayerController.class);
	
	@RequestMapping(value = "/MainMap")
	public String MainMapUrl(){
		logger.info("MainMap");
		return "GIS/MainMap";
	}
	
	@RequestMapping(value = "/BingMap")
	public String BingMapUrl(){
		return "GIS/BingMap";
	}
	
	@RequestMapping(value = "/ExampleMap")
	public String wfsDemoMapUrl(){
		return "GIS/Example/vector-wfs-getfeature";
	}
	
	@RequestMapping(value = "/GoogleMap")
	public String GoogleMapUrl(){
		return "GIS/OpenLayersWithGoogleMap";
	}
	
	@RequestMapping(value = "/MapBox")
	public String MapBoxUrl(){
		return "GIS/OpenLayersWithMapBox";
	}

}
