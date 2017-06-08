package Phantom.PostGIS.Synchronization.Dao;

import org.springframework.data.jpa.repository.Query;

import Phantom.PostGIS.Synchronization.Entity.OSMRoad;

public interface OSMRoadDao extends BaseRepository<OSMRoad,Integer>{
	
	@Query(value="select count(*) from tb_osm_roads_line" ,nativeQuery = true)
	public int getCount();

}
