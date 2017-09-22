package Phantom.ReverseGeocoding.Service.VehicleMonitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Phantom.ReverseGeocoding.Dao.GCJPointDao;
import Phantom.ReverseGeocoding.Entity.GCJPoint;

@Service
public class VehicleHistoryService {
	
	@Autowired
	private GCJPointDao dao;
	
	public List<GCJPoint> GetAllPointOrderByIDASC(long vehicleid){
		return dao.GetAllPointOrderByIDASC(vehicleid);
	}
	
	public void saveGPS(GCJPoint point){
		dao.save(point);
	}

}
