package Phantom.ReverseGeocoding.Service.DataSyn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Phantom.ReverseGeocoding.Dao.GCJRoadDao;
import Phantom.ReverseGeocoding.Entity.GCJRoad;

@Service
public class GCJRoadService {
	
	@Autowired
	private GCJRoadDao dao;
	
	public void save(GCJRoad entity){
		dao.save(entity);
	}
	
	public List<GCJRoad> getUnNameRoad(int limit,int offset){
		return dao.getUnamedRoad(limit,offset);
	}

}
