/**
 * @Project: Phantom
 * @Title: 公用模块|dbcp连接池
 * @Description: dbcp连接池
 * @Author: jiabotao
 * @Date: 2017年3月21日 下午6:31:15
 * @Company: 
 * @Copyright:
 * @Version V1.0
 */
package Phantom.Common.Util.DataUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: DataSourceFactory.java
 * @Description: 管理数据库连接池
 * @Author: jiabotao
 * @Date: 2016年12月20日
 */
public class DataSourceFactory {

	private static DataSourceFactory poolManager = null;// 管理单例
	private DataSource dataSource; // 数据源
	private static String configFilePath;
	private static String driver = "oracle.jdbc.OracleDriver";// 驱动
	private static String url = "jdbc:oracle:thin:@//192.168.1.102:1521:c5testdb";// URL
	private static String Name = "c5web";// 用户名
	private static String Password = "c5web";// 密码

	boolean logAbandoned = true;// 是否在自动回收超时连接的时候打印连接的超时错误
	boolean removeAbandoned = true;// 是否自动回收超时连接

	private int initialSize = 10;// 初始化连接
	private int maxIdle = 20;// 最大空闲连接
	private int minIdle = 5;// 最小空闲连接
	private int maxActive = 25;// 最大连接数量
	private int removeAbandonedTimeout = 60;// 超时时间(以秒数为单位)
	private int maxWait = 1000;// 超时等待时间以毫秒为单位 6000毫秒/1000等于60秒

	// 实例化log4j
	private static Log log = LogFactory.getLog(DataSourceFactory.class);

	/**
	 * @Description 根据配置获取管理
	 * @param tconfigFilePath
	 *            配置路径
	 */
	synchronized public static DataSourceFactory getPoolManager(String fileName) {

		if (poolManager == null) {
			poolManager = new DataSourceFactory();
			// 初始化一下，再看看是否失败
			if (!poolManager.init(fileName)) {
				return null;
			}
		}
		// 返回连接池管理
		return poolManager;

	}

	/**
	 * @Title: 初始化连接池
	 * @Description: 初始化数据连接池
	 * @param:
	 * @return true-加载成功
	 * @Throws Exception
	 */
	public boolean init(String fileName) {
		// 读取资源文件
		if (!loadProperties(fileName)) {
			return false;
		}
		// 加载驱动
		try {
			Class.forName(driver);
			// 连接池初始化
			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(driver);
			basicDataSource.setUrl(url);
			basicDataSource.setUsername(Name);
			basicDataSource.setPassword(Password);
			basicDataSource.setMaxTotal(maxActive);// 设置最大并发数
			basicDataSource.setInitialSize(initialSize);// 数据库初始化时，创建的连接个数
			basicDataSource.setMinIdle(minIdle);// 最小空闲连接数
			basicDataSource.setMaxIdle(maxIdle);// 数据库最大连接数
			basicDataSource.setMaxWaitMillis(maxWait);
			basicDataSource.setMinEvictableIdleTimeMillis(removeAbandonedTimeout);// 空闲连接60秒中后释放
			basicDataSource.setTimeBetweenEvictionRunsMillis(5 * 60 * 1000);// 5分钟检测一次是否有死掉的线程
			basicDataSource.setTestOnBorrow(true);
			basicDataSource.getConnection();
			dataSource = basicDataSource;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @Title: 读取配置
	 * @Description: 加载配置信息
	 * @param:
	 * @return true-加载成功
	 * @Throws Exception
	 */
	private boolean loadProperties(String fileName) {
		try {
			configFilePath = System.getProperty("server.config.path");
			// configFilePath="D:\\code\\796dev2\\01.devlib\\02.src\\01.code源代码\\vms\\config\\reportService_shuttlebus\\";
			// 读取jdbc.properties
			log.info("DB " + configFilePath + fileName);
			// 变成文件流
			java.io.InputStream stream = new FileInputStream(configFilePath + fileName);
			// 创建资源文件对象
			java.util.Properties props = new java.util.Properties();
			props.load(stream);

			// 驱动，数据库地址，用户名，密码
			driver = props.getProperty("jdbc.driverClassName");
			url = props.getProperty("jdbc.url");
			Name = props.getProperty("jdbc.username");
			Password = props.getProperty("jdbc.password");

			log.info(driver + ";" + url + ";" + Name + ";" + Password);
			logAbandoned = (Boolean.valueOf(props.getProperty("logAbandoned", "false"))).booleanValue();

			removeAbandoned = (Boolean.valueOf(props.getProperty("removeAbandoned", "false"))).booleanValue(); // 超时时间(以秒数为单位)

			initialSize = Integer.parseInt(props.getProperty("initialSize", "30"));// 初始化连接
			maxIdle = Integer.parseInt(props.getProperty("maxIdle", "20"));// 最大线程
			minIdle = Integer.parseInt(props.getProperty("minIdle", "5"));// 最小线程
			maxActive = Integer.parseInt(props.getProperty("maxActive", "50"));// 最大连接
			removeAbandonedTimeout = Integer.parseInt(props.getProperty("removeAbandonedTimeout", "10")); // 超时
			maxWait = Integer.parseInt(props.getProperty("maxWait", "2000")); // 超时时间

		} catch (FileNotFoundException e) {
			System.out.println("读取配置文件异常");
			log.debug("读取配置文件异常");
			log.warn("", e);
		} catch (IOException ie) {
			System.out.println("读取配置文件时IO异常");
			log.debug("读取配置文件时IO异常");
		}
		return true;
	}

	/**
	 * @Title: 获取连接
	 * @Description: 获取连接
	 * @param:
	 * @return Connection-数据库连接
	 * @Throws Exception
	 */
	synchronized public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * @Title: 关闭连接
	 * @Description 关闭连接
	 * @param:
	 * @return Connection-数据库连接
	 * @Throws Exception
	 */
	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

}
