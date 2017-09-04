package Phantom.PostGIS.Synchronization.Service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.PostGIS.Synchronization.Dao.ProvinceDao;
import Phantom.PostGIS.Synchronization.Entity.Province;

@Service
public class ProvinceService {
	
	@Resource
	private ProvinceDao provinceDao;
	
	public void save(Province entity){
		provinceDao.save(entity);
	}
	
	public ArrayList<String> getadcode(){
		ArrayList<String> adcodes = provinceDao.getadcodes();
		return adcodes;
	}

}
