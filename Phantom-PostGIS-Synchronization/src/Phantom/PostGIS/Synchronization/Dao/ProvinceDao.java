package Phantom.PostGIS.Synchronization.Dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;

import Phantom.PostGIS.Synchronization.Entity.Province;

public interface ProvinceDao extends BaseRepository<Province,Integer>{
	
	@Query(value="SELECT adcode.adcode FROM adcode WHERE adcode.adcode NOT in (SELECT tb_gcj_province.adcode FROM tb_gcj_province UNION SELECT tb_gcj_city.adcode FROM tb_gcj_city)",nativeQuery=true)
	public ArrayList<String> getadcodes(); 

}
