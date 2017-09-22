package Phantom.Data.Initiation.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Phantom.Data.Initiation.Dao.OSMRoadDao;

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
	 */
	public void page(int limit,int offset){
		dao.getOSMRoadByPage(limit, offset);
	}

}
