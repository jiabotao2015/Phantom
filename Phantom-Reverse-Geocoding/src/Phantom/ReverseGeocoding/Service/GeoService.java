package Phantom.ReverseGeocoding.Service;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class GeoService {
		
	private ComboPooledDataSource dataSourcePool;

	public ComboPooledDataSource getDataSourcePool() {
		return dataSourcePool;
	}

	public void setDataSourcePool(ComboPooledDataSource dataSourcePool) {
		this.dataSourcePool = dataSourcePool;
	} 

	
	


}
