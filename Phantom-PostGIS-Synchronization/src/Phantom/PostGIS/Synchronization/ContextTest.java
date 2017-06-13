package Phantom.PostGIS.Synchronization;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class ContextTest {
	
	private PGConnectionPoolDataSource dataSource;
	
	public void init(){
		System.out.println("init");
		if(dataSource!=null){
			System.out.println("not null");
		}
	}

	public PGConnectionPoolDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(PGConnectionPoolDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	

}
