package Phantom.ReverseGeocoding.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Phantom.ReverseGeocoding.Entity.GCJRoad;

public interface GCJRoadDao extends BaseRepository<GCJRoad,Integer>{
	
	@Query(value="SELECT * FROM tb_gcj_road WHERE geom_name IS NULL AND ref IS NULL ORDER BY gid asc limit :limit offset :offset",nativeQuery=true)
	public List<GCJRoad> getUnamedRoad(@Param("limit")int limit,@Param("offset")int offset);
	
}

