package Phantom.PostGIS.Synchronization.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

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
	
	public OSMRoad getById(int gid){
		return roadDao.findOne(gid);
	}
	
	public Coordinate[] getCoords(OSMRoad osmroad){
		Geometry geom = osmroad.getGeom();
		Coordinate[] coords = geom.getCoordinates();
		return coords;
	}
	
	public ArrayList<OSMRoad> findByPage(int limit,int offset){
		ArrayList<OSMRoad> osm_road_array = roadDao.getOSMRoadByPage(limit, offset);
		return osm_road_array;		
	}
	
	

}
