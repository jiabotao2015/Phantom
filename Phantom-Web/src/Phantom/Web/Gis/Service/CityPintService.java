package Phantom.Web.Gis.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.Web.Gis.Dao.CityPointDao;
import Phantom.Web.Gis.Entity.CityPoint;

@Service
public class CityPintService {
	
	@Resource
	private CityPointDao dao;
	
	public void addcity(CityPoint city){
		dao.save(city);
	}
	
	public CityPoint getCity(int cityid){
		CityPoint cp = dao.findOne(cityid);
		return cp;
	}
	
	

}
