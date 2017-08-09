package Phantom.AlarmCompute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogisticsCacheIniter extends TimerTask  {
	
	private static Log log = LogFactory.getLog(LogisticsCacheIniter.class);
	Connection con;
	PreparedStatement pre;
	ResultSet result;
	private static DataSourceFactory pool = DataSourceFactory.getPoolManager("jdbc.properties");;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			con = pool.getConnection();
			String sql = "select * from B_BUSINESS_POINT";
			PreparedStatement point_PreparedStatement = con.prepareStatement(sql);
			ResultSet point_result = point_PreparedStatement.executeQuery();
			while (point_result.next()) {
				log.info("vid:  " + point_result.getString(1));
			}
			point_result.close();
			point_PreparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
