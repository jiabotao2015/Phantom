package Phantom.PostGIS.Synchronization.Service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Phantom.PostGIS.Synchronization.Dao.AdministrativeDivisionsDao;
import Phantom.PostGIS.Synchronization.Entity.AdministrativeDivision;

@Service
public class AdministrativeDivisionService {
	
	@Resource
	private AdministrativeDivisionsDao adDao;
	
	public void save(AdministrativeDivision entity){
		adDao.save(entity);
	}
	
	public void save (ArrayList<AdministrativeDivision> entities){
		adDao.save(entities);
	}

	public ArrayList<String>  getAllAdCode() {
		// TODO Auto-generated method stub
		ArrayList<String> adcodes = adDao.getAllAdCode();
		return adcodes;
	}
	
	public ArrayList<String>  getadcodenotin() {
		// TODO Auto-generated method stub
		ArrayList<String> adcodes = adDao.getadcodenotin();
		return adcodes;
	}

}
