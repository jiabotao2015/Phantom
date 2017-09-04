package Phantom.Data.Initiation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class InitationStartUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String contextPath = "src/config/ApplicationContext.xml";
		ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);

	}

}
