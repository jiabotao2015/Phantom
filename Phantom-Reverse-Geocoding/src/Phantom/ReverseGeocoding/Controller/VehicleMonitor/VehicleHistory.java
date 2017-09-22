package Phantom.ReverseGeocoding.Controller.VehicleMonitor;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Phantom.ReverseGeocoding.Entity.GCJPoint;
import Phantom.ReverseGeocoding.Service.VehicleMonitor.VehicleHistoryService;

@Controller
public class VehicleHistory {
	
	@Autowired
	private VehicleHistoryService service;
	
	@ResponseBody
	@RequestMapping(value="/VehicleMonitor/VehicleHistory")
	public List<GCJPoint> GetAllPointOrderById(String vehicleid){
		List<GCJPoint> list = service.GetAllPointOrderByIDASC(Long.valueOf(vehicleid));
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/VehicleMonitor/CreateGCJPointByWKT")
	public String CreateGCJPointByWKT(String lng,String lat,String vehicleId,String plate){
		GCJPoint point = new GCJPoint();
		point.setGcjlng(Double.valueOf(lng));
		point.setGcjlat(Double.valueOf(lat));
		point.setPlate(plate);
		point.setVehicleId(Long.valueOf(vehicleId));
		Random random = new Random(System.currentTimeMillis()); 
		int iRandom = random.nextInt(100)+1;
		
		int direction = random.nextInt(360)+1;
		point.setSpeed(iRandom);
		point.setDirection(direction);
		service.saveGPS(point);
		return "OK";
	}

}
