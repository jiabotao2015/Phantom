package Phantom.ReverseGeocoding.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Service
public class ReverseGeocodingService {
	
	
	@Autowired
	private GeoService geoService;
	
	
	public String reserveWithPool(String lon,String lat) throws SQLException{
		ComboPooledDataSource dsp = geoService.getDataSourcePool();
		Connection conn = dsp.getConnection();
		Statement state = conn.createStatement();
		String reversesql = "select reverse("+lon+","+lat+")";
		ResultSet rs = state.executeQuery(reversesql);
		String result="解析失败";
		while(rs.next()){
			result = rs.getString(1);
		}
		rs.close();
		state.close();
		conn.close();
		return result;
	}

}
