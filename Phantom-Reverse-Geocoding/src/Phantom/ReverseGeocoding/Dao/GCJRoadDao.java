package Phantom.ReverseGeocoding.Dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Phantom.ReverseGeocoding.Entity.GCJRoad;

public interface GCJRoadDao extends BaseRepository<GCJRoad,Integer>{
	
	@Query(value="select * from tb_osm_road order by gid asc limit :limit offset :offset ",nativeQuery = true)
	public ArrayList<GCJRoad> getGCJRoadByPage(@Param("limit")int limit,@Param("offset")int offset);
	
	@Query(value="SELECT * FROM tb_gcj_road WHERE geom_name IS NULL ORDER BY gid ASC LIMIT :limit OFFSET :offset ",nativeQuery = true)
	public ArrayList<GCJRoad> getUnNamedGCJRoadByPage(@Param("limit")int limit,@Param("offset")int offset);
	
	
	@Query(value="SELECT * FROM tb_gcj_road WHERE geom_name like '%高速出口'",nativeQuery = true)
	public ArrayList<GCJRoad> getGaoSuGCJRoadByPage();

}
