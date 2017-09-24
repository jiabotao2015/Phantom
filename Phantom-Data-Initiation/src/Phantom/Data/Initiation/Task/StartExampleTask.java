package Phantom.Data.Initiation.Task;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Phantom.Data.Initiation.Service.OSMRoadService;
import Phantom.Data.Initiation.Service.SpringInjectService;

//@Service
public class StartExampleTask implements InitializingBean{

	@Autowired
	private OSMRoadService service;
	
	private static final int pagesize = 1000;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		long count = service.count();
		
	}

}
