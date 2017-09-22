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
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		long a = service.count();
		System.out.println(a);
		
	}

}
