package Phantom.Web.Gis.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Phantom.Web.Gis.Entity.MapTile;

public interface MapTileDao extends BaseRepository<MapTile,Long>{
	
	
	@Query(value="select * from tb_map_tile where x = :x  and y = :y and z = :z",nativeQuery=true)
	public MapTile getTileWithXYZ(@Param("x") String x,@Param("y")String y,@Param("z")String z);

}
