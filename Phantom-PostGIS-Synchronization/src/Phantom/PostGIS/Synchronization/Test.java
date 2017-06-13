package Phantom.PostGIS.Synchronization;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws SQLException {

		String contextPath = "src/config/SpringTestContext.xml";
		ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);
	}

}
