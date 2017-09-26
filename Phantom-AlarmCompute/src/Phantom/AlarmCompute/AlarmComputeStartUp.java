package Phantom.AlarmCompute;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AlarmComputeStartup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String contextPath = "src/config/ApplicationContext.xml";
		ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);

	}

}
