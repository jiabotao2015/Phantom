package Phantom.PostGIS.Synchronization.Dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Phantom.PostGIS.Synchronization.Entity.OSMRoad;

public interface OSMRoadDao extends BaseRepository<OSMRoad,Integer>{
	
	@Query(value="select count(*) from tb_osm_roads_line" ,nativeQuery = true)
	public int getCount();
	
	@Query(value="select * from tb_osm_roads_line order by gid asc limit :limit offset :offset ",nativeQuery = true)
	public ArrayList<OSMRoad> getOSMRoadByPage(@Param("limit")int limit,@Param("offset")int offset);

}
