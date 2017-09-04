package Phantom.Web.Gis.Service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

/*@Service*/
public class GisService {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public byte[] getTile(String tileCode) {
		byte[] tile = null;
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select tile from tb_gaode_map_tile where tile_code = '"+tileCode+"'";
			sql = "select gettilebyxyz('"+tileCode+"')";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				tile = rs.getBytes(1);
			}	
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tile;
	}
	
	public InputStream getTileInputeSteam(String tileCode){
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select tile from tb_gaode_map_tile where tile_code = '"+tileCode+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				InputStream is =rs.getBinaryStream(1);
				rs.close();
				stmt.close();
				conn.close();
				return is;
			}
		}catch (SQLException e){
			
		}
		return null;
	}

}
