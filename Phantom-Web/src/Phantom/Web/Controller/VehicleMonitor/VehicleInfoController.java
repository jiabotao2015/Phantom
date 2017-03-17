package Phantom.Web.Controller.VehicleMonitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VehicleInfoController {
	
	@RequestMapping(value = "/VehicleMonitor/VehicleInfo")
	public @ResponseBody String VehicleInfo(String Message){
		System.out.println(Message);
		return "VehicleInfo_Action中文测试！@#￥1234!@#$";
	}
	
	@RequestMapping(value = "/VehicleMonitor/Map")
	public String Map(){
		return "VehicleMonitor/Map";
	}

}
