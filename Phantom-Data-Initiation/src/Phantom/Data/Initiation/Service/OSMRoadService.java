package Phantom.Data.Initiation.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Phantom.Data.Initiation.Dao.OSMRoadDao;
import Phantom.Data.Initiation.Entity.OSMRoad;

@Service
public class OSMRoadService {
	
	@Autowired
	private OSMRoadDao dao;
	
	public long count(){
		long count = dao.count();
		return count;
	}
	
	/**
	 * 
	 * @param limit 一页大小
	 * @param offset 第i页*limit
	 * @return 
	 */
	public List<OSMRoad> page(int limit,int offset){
		return dao.getOSMRoadByPage(limit, offset);
	}

}
