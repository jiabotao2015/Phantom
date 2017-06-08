package Phantom.PostGIS.Synchronization.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.PostGIS.Synchronization.Dao.GCJRoadDao;
import Phantom.PostGIS.Synchronization.Entity.GCJRoad;

@Service
public class GCJRoadService {
	
	@Resource 
	private GCJRoadDao roadDao;
	
	public void save(GCJRoad entity){
		roadDao.save(entity);
	}
	
	/*public int getCount(){
		roadDao.
	}*/

}
