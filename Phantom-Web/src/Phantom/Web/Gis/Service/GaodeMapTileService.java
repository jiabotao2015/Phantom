package Phantom.Web.Gis.Service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.Web.Gis.Dao.GaodeMapTileDao;
import Phantom.Web.Gis.Entity.GaodeMapTile;


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
	
	public GaodeMapTile getTileByXYZ(int x,int y,int z){
		GaodeMapTile entity= dao.getTileWithXYZ(x, y, z);
		return entity;
	}

	public GaodeMapTile getTileWithTileCode(String code) {
		GaodeMapTile entity= dao.getTileWithTileCode(code);
		return entity;
	}
	
	public GaodeMapTile getTileByTileCodeId(String code) {
		GaodeMapTile entity= dao.findOne(code);
		return entity;
	}

}
