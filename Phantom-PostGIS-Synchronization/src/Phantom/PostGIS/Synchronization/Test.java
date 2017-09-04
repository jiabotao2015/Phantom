package Phantom.PostGIS.Synchronization;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import Phantom.PostGIS.Synchronization.Service.AdministrativeDivisionService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String contextPath = "src/config/SpringTestContext.xml";
		ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);
		AdministrativeDivisionService adservice = (AdministrativeDivisionService)context.getBean("AdministrativeDivisionService");
		

	}

}
