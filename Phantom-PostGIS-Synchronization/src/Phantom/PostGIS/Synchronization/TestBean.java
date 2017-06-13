package Phantom.PostGIS.Synchronization;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class TestBean {
	
	private ContextTest context;

	public ContextTest getContext() {
		return context;
	}

	public void setContext(ContextTest context) {
		this.context = context;
	}
	
	public void init(){
		PGConnectionPoolDataSource pgdsp = this.context.getDataSource();
		if(pgdsp!=null){
			System.out.println("not null");
		}
	}

}
