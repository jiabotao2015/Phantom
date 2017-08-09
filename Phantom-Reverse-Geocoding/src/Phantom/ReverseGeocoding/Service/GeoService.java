package Phantom.ReverseGeocoding.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.postgis.LineString;
import org.postgis.PGgeometry;

import Phantom.API.Bean.LonLat;
import Phantom.API.Util.LngLatUtil;


public class GeoService {
	
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public String getWKT() throws SQLException{
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement(); 
		String sql = "select st_astext(geom) from tb_osm_roads_line where gid  = 10";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		String wkt = rs.getString(1);  
		return wkt;
	}
	
	public LineString getLineString(int gid) throws SQLException{
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement(); 
		String sql = "select geom from tb_gcj_road where gid  = "+gid;
		ResultSet rs = stmt.executeQuery(sql);
		PGgeometry geom = new PGgeometry();
		LineString geomline = new LineString();
		while(rs.next()){
			geom = (PGgeometry)rs.getObject(1);
			geomline = (LineString)geom.getGeometry();
		}
		rs.close();
		stmt.close();
		conn.close();
		return geomline;
	}
	
	public void pubRoadName(int gid,String name) throws SQLException{
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement(); 
		String querysql = "select geom_name from tb_gcj_road where gid  = "+gid;
		ResultSet rs = stmt.executeQuery(querysql);
		while(rs.next()){
			String a = rs.getString(1);
			if(a==null){
				Statement updatestmt = conn.createStatement(); 
				String sql = "update tb_gcj_road set geom_name = '"+name+"' where gid  = "+gid;
				updatestmt.executeUpdate(sql);
				updatestmt.close();
			}
		}
		conn.close();
	}
	
	/**
	 * 导入中烟业务点
	 * @throws SQLException
	 */
	public void getzyywd() throws SQLException{
		String sql = "select * FROM tb_test ";
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql);
		String result = "";
		while(rs.next()){
			String pointname = rs.getString(5);
			double lon = rs.getDouble(2);
			double lat  = rs.getDouble(3);
			LonLat coord = new LonLat();
			LngLatUtil.transform(lat,lon, coord);
			double elon = coord.getLongitude();
			double elat = coord.getLatitude();
			result = result + "{POINTNAME:\""+pointname+"\",POINTTYPE:\"10\""+",USERLEVEL:\"0\""+""+",ORGANIZATION:\"中烟测试\""+",ORGANIZATIONID:\"B00002\""+",CREATED:\"jiabotao\""
			+",REMARK:\"批量导入\""+",RANGE:\"500\""+",STOPTIME:\"30\""+",CREATTIME:\"2017-7-26 12:53:0\""+",LON:\""+elon+"\""+",LAT:\""+elat+"\""+",CITY_ID:\"1010088167\""+",ADMINCODE:\"420100\"},";
		}
		System.out.println(result);
	}

}
