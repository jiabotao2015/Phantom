package Phantom.ReverseGeocoding.Service.DataSyn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Phantom.ReverseGeocoding.Dao.GCJPoiDao;
import Phantom.ReverseGeocoding.Entity.GCJPoi;

@Service
public class GCJPoiService {
	
	@Autowired
	private GCJPoiDao dao;
	
	public GCJPoi save(GCJPoi entity){
		return dao.save(entity);
	}
	
	public List<GCJPoi> save(List<GCJPoi> entities){
		return  dao.save(entities);
	}
	
	public List<GCJPoi> FindByGaodeId(String gaodeId){
		return dao.FindByGaodeId(gaodeId);
	}
	
	public void delete(GCJPoi entity){
		dao.delete(entity);
	}

}
