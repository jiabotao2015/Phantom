package Phantom.PostGIS.Synchronization.SynTask;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import Phantom.PostGIS.Synchronization.Service.OSMRoadService;

public class RoadSynTask {

	private PGConnectionPoolDataSource pgpds;
	
	private PGConnection pgconn;
	
	@Autowired
	private OSMRoadService osmservice;

	public PGConnectionPoolDataSource getPgpds() {
		return pgpds;
	}

	public void setPgpds(PGConnectionPoolDataSource pgpds) {
		this.pgpds = pgpds;
	}

	public void execute() {
		if (pgpds != null && pgconn==null) {
			System.out.println("Start init dataSource and listener");
			initListener();
		} else if(pgpds != null && pgconn!=null){
			System.out.println("start init");
		} else if(pgpds == null && pgconn==null){
			System.out.println("RoadSynTask start fail");
		}
		
		try {
			PGNotification notifications[] = pgconn.getNotifications();
			if (notifications != null) {
				for (int i=0; i<notifications.length; i++) {
					System.out.println("Got notification: " + notifications[i].getName());
				}
			}
			
			int all = osmservice.getCount();
			System.out.println(all);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initListener() {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = pgpds.getConnection();
			Statement statement = conn.createStatement();
			statement.execute("LISTEN VIRTUAL");
			statement.close();
			this.pgconn = (PGConnection) conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
