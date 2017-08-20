package Phantom.PostGIS.Synchronization.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Phantom.PostGIS.Synchronization.Entity.GaodeMapTile;

public interface GaodeMapTileDao extends BaseRepository<GaodeMapTile,String>{
	
	@Query(value="select * from tb_gaode_map_tile where x = :x  and y = :y and z = :z",nativeQuery=true)
	public GaodeMapTile getTileWithXYZ(@Param("x") String x,@Param("y")String y,@Param("z")String z);

}
