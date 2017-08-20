package Phantom.Web.Gis.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Phantom.Web.Gis.Entity.GaodeMapTile;


public interface GaodeMapTileDao extends BaseRepository<GaodeMapTile,String>{
	
	@Query(value="select * from tb_gaode_map_tile where x = :x  and y = :y and z = :z",nativeQuery=true)
	public GaodeMapTile getTileWithXYZ(@Param("x") int x,@Param("y")int y,@Param("z")int z);
	
	@Query(value="select * from tb_gaode_map_tile where tile_code = :tileCode",nativeQuery=true)
	public GaodeMapTile getTileWithTileCode(@Param("tileCode") String tileCode);

}
