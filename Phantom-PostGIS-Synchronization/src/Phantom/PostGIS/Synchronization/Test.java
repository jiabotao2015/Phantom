package Phantom.PostGIS.Synchronization;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import Phantom.PostGIS.Synchronization.Service.OSMRoadService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new FileSystemXmlApplicationContext("src/config/SpringContext.xml");
		OSMRoadService roadservice = (OSMRoadService) context.getBean("RoadService");
		int count = roadservice.getCount();
		System.out.println(count);

	}

}
