package Phantom.Web.Gis.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.Web.Gis.Dao.CityDao;
import Phantom.Web.Gis.Entity.City;

@Service
public class CityService {
	
	@Resource
	private CityDao dao;
	
	public void save(City entity){
		dao.save(entity);
	}

}
