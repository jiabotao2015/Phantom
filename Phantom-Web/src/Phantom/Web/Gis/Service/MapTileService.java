package Phantom.Web.Gis.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.Web.Gis.Dao.MapTileDao;
import Phantom.Web.Gis.Entity.MapTile;

@Service
public class MapTileService {
	
	@Resource
	private MapTileDao dao;
	
	public void save(MapTile entity){
		dao.save(entity);
	}
	
	public MapTile getTileByXYZ(String x,String y,String z){
		MapTile entity= dao.getTileWithXYZ(x, y, z);
		return entity;
	}

}
