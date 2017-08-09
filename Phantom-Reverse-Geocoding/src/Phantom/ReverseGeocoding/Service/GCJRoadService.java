package Phantom.ReverseGeocoding.Service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Phantom.ReverseGeocoding.Dao.GCJRoadDao;
import Phantom.ReverseGeocoding.Entity.GCJRoad;


@Service
public class GCJRoadService {
	
	@Resource 
	private GCJRoadDao roadDao;
	
	@Autowired
	private BaseJpaSearch jpaSearch;
	
	public void save(GCJRoad entity){
		roadDao.save(entity);
	}
	
	public void save(ArrayList<GCJRoad> entities){
		roadDao.save(entities);
	}
	
	public ArrayList<GCJRoad> getUnNamedGCJRoad(int limit,int offset){
		return roadDao.getUnNamedGCJRoadByPage(limit, offset);
	}
	
	public ArrayList<GCJRoad> getGaoSuGCJRoad(){
		return roadDao.getGaoSuGCJRoadByPage();
	}

}
