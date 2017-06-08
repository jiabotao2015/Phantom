package Phantom.PostGIS.Synchronization.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.PostGIS.Synchronization.Dao.OSMRoadDao;
import Phantom.PostGIS.Synchronization.Entity.OSMRoad;

@Service
public class OSMRoadService {
	
	@Resource
	private OSMRoadDao roadDao;
	
	public List<OSMRoad> getAll(){
		List<OSMRoad> roads = (List<OSMRoad>) roadDao.findAll();
		return roads;
	}
	
	public int getCount(){
		int count = roadDao.getCount();
		return count;
	}

}
