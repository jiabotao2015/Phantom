package Phantom.PostGIS.Synchronization.Service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.PostGIS.Synchronization.Dao.GaodeMapTileDao;
import Phantom.PostGIS.Synchronization.Entity.GaodeMapTile;

@Service
public class GaodeMapTileService {
	
	@Resource
	private GaodeMapTileDao dao;
	
	public void save(GaodeMapTile entity){
		dao.save(entity);
	}
	
	public void save(ArrayList<GaodeMapTile> entities){
		dao.save(entities);
	}
	
	public GaodeMapTile getTileByXYZ(String x,String y,String z){
		GaodeMapTile entity= dao.getTileWithXYZ(x, y, z);
		return entity;
	}
	
	public GaodeMapTile getTileByCode(String tileCode){
		GaodeMapTile entity= dao.findOne(tileCode);
		return entity;
	}
	
	public GaodeMapTile getTileByCodeId(String code){
		GaodeMapTile entity= dao.findOne(code);
		return entity;
	}

}
