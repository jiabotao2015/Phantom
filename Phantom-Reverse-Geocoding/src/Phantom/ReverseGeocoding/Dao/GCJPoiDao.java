package Phantom.ReverseGeocoding.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import Phantom.ReverseGeocoding.Entity.GCJPoi;

public interface GCJPoiDao extends BaseRepository<GCJPoi,Integer>{
	
	@Query(value="SELECT * FROM tb_gcj_poi WHERE gd_id = ?1",nativeQuery=true)
	public List<GCJPoi> FindByGaodeId(String GaodeId);

}
