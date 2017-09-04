package Phantom.PostGIS.Synchronization.Dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;

import Phantom.PostGIS.Synchronization.Entity.AdministrativeDivision;

public interface AdministrativeDivisionsDao extends BaseRepository<AdministrativeDivision,String> {

	
	@Query(value="SELECT adcode.adcode FROM adcode",nativeQuery=true)
	public ArrayList<String> getAllAdCode();

}
