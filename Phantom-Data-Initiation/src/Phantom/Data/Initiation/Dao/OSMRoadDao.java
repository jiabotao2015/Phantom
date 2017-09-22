package Phantom.Data.Initiation.Dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Phantom.Data.Initiation.Entity.OSMRoad;

public interface OSMRoadDao extends BaseRepository<OSMRoad,Integer>{
	
	@Query(value="select * from tb_osm_road order by gid asc limit :limit offset :offset ",nativeQuery = true)
	public ArrayList<OSMRoad> getOSMRoadByPage(@Param("limit")int limit,@Param("offset")int offset);

}
