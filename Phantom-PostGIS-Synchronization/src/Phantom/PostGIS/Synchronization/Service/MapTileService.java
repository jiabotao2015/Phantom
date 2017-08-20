package Phantom.PostGIS.Synchronization.Service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.PostGIS.Synchronization.Dao.MapTileDao;
import Phantom.PostGIS.Synchronization.Entity.MapTile;

@Service
public class MapTileService {
	
	@Resource
	private MapTileDao dao;
	
	public void save(MapTile entity){
		dao.save(entity);
	}
	
	public void save(ArrayList<MapTile> entities){
		dao.save(entities);
	}
	
	public MapTile getTileByXYZ(String x,String y,String z){
		MapTile entity= dao.getTileWithXYZ(x, y, z);
		return entity;
	}

}
