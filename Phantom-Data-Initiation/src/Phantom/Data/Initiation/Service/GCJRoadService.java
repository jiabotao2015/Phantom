package Phantom.Data.Initiation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Phantom.Data.Initiation.Dao.GCJRoadDao;
import Phantom.Data.Initiation.Entity.GCJRoad;

@Service
public class GCJRoadService {

	@Autowired
	private GCJRoadDao dao;
	
	public void save(GCJRoad entity){
		dao.save(entity);
	}
	
	public void save(List<GCJRoad> entities){
		dao.save(entities);
	}
}
