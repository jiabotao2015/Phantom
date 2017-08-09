package Phantom.AlarmCompute;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceFactory {

	private static DataSourceFactory poolManager = null;
	private DataSource ds; 
	private static String configFilePath;
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@//59.172.105.86:7003:VMS3DEVDB";// URL
	private static String Name = "c5web";
	private static String Password = "c5web";
	
	boolean logAbandoned = true;
	boolean removeAbandoned = true;
	
	private int initialSize = 5;// 
	private  int maxIdle = 20;// 
	private int minIdle = 5;//
	private int maxActive = 25;// 
	private int removeAbandonedTimeout = 60;//
	private int maxWait = 1000;// 
	
	private String MinPoolSize;
	private String MaxPoolSize;
	private String InitialPoolSize;

	//你好
	private static Log log = LogFactory.getLog(DataSourceFactory.class);

	
	synchronized public static DataSourceFactory getPoolManager(String fileName) {
		
		if (poolManager == null) {
			poolManager = new DataSourceFactory();
			if (!poolManager.init(fileName)) {
				return null;
			}
		}
		
		return poolManager;
	}

	
	public boolean init(String fileName) {
		
		if (!loadProperties(fileName)) {
			return false;
		}
		
		try {
			Class.forName(driver);
			//BasicDataSource bs = new BasicDataSource();
			ComboPooledDataSource c3p0ds = new ComboPooledDataSource();
			c3p0ds.setUser(Name);
			c3p0ds.setPassword(Password);
			c3p0ds.setDriverClass(driver);
			c3p0ds.setJdbcUrl(url);
			c3p0ds.setInitialPoolSize(Integer.parseInt(InitialPoolSize));
			c3p0ds.setMinPoolSize(Integer.parseInt(MinPoolSize));
			c3p0ds.setMaxPoolSize(Integer.parseInt(MaxPoolSize));
			c3p0ds.getConnection();
			ds = c3p0ds;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	
	private boolean loadProperties(String fileName) {
		try {
			configFilePath = System.getProperty("server.config.path");
			
			log.info("DB " + configFilePath + "jdbc.properties");
			
			java.io.InputStream stream = new FileInputStream(configFilePath
					+ fileName);
			
			java.util.Properties props = new java.util.Properties();
			props.load(stream);

			
			driver = props.getProperty("jdbc.driverClassName");
			url = props.getProperty("jdbc.url");
			Name = props.getProperty("jdbc.username");
			Password = props.getProperty("jdbc.password");
			MinPoolSize = props.getProperty("MinPoolSize");
			MaxPoolSize = props.getProperty("MaxPoolSize");
			InitialPoolSize = props.getProperty("InitialPoolSize");

			log.info(driver + ";" + url + ";" + Name + ";" + Password);
			logAbandoned = (Boolean.valueOf(props.getProperty("logAbandoned",
					"false"))).booleanValue();

			removeAbandoned = (Boolean.valueOf(props.getProperty(
					"removeAbandoned", "false"))).booleanValue(); // 

			initialSize = Integer.parseInt(props.getProperty("initialSize",
					"30"));// ��ʼ������
			maxIdle = Integer.parseInt(props.getProperty("maxIdle", "20"));
			minIdle = Integer.parseInt(props.getProperty("minIdle", "5"));//
			maxActive = Integer.parseInt(props.getProperty("maxActive", "50"));//
			removeAbandonedTimeout = Integer.parseInt(props.getProperty(
					"removeAbandonedTimeout", "10"));
			maxWait = Integer.parseInt(props.getProperty("maxWait", "2000")); //
			
		} catch (FileNotFoundException e) {
			System.out.println("");
			log.debug("");
			log.warn("", e);
		} catch (IOException ie) {
			System.out.println("");
			log.debug("");
		}
		return true;
	}

	
	synchronized public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	
	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}
	

}
