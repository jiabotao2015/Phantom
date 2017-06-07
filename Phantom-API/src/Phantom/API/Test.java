package Phantom.API;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Bean.LonLat;
import Phantom.API.Util.LngLatUtil;
import Phantom.API.Util.SpatialUtil;

public class Test {

	public static void main(String[] args) {
		 
		//LonLat coord = new LonLat();
		//LngLatUtil.transform(30.2528126, 120.1731103, coord);
		//LngLatUtil.Wgs84ToBd09(120.1773595, 120.1773595, coord);
		//double a = coord.getLatitude();
		//double b = coord.getLongitude();
		//System.out.println(a);
		//System.out.println(b);
		
		//String wkt = "LINESTRING(120.1773595 30.2522475,120.1754035 30.2524985,120.175231 30.2525038,120.1736977 30.2527235,120.1731103 30.2528126,120.1714494 30.2529979,120.1707159 30.253081,120.170419 30.2531146,120.1700641 30.2531328,120.1696202 30.2531555,120.1677611 30.2531192)";
		String wkt = "LINESTRING(120.1677611 30.2531192,120.1673989 30.253096,120.1672363 30.2530916,120.1671319 30.2530845,120.1657667 30.2529941,120.1645439 30.2529087,120.1637773 30.2528536,120.1618451 30.2527148,120.1605239 30.2527586,120.1595915 30.2527349)";
		Geometry geom = SpatialUtil.createGeometryByWKT(wkt);
		Coordinate[] coords = geom.getCoordinates();
		int length = coords.length;
		Coordinate[] new_coords = new Coordinate[length];
		for(int i=0;i<length;i++){
			Coordinate tmpcoord = coords[i];
			LonLat coord = new LonLat();
			LngLatUtil.transform(tmpcoord.y, tmpcoord.x, coord);
			Coordinate new_coord = new Coordinate(coord.getLongitude(),coord.getLatitude());
			new_coords[i] = new_coord;
		}
		
		String new_wkt = "LINESTRING(";
		for(int i=0;i<length;i++){
			double a = new_coords[i].x;
			double b = new_coords[i].y;
			String append = Double.toString(a)+" "+Double.toString(b)+",";
			new_wkt = new_wkt + append;
		}
		new_wkt = new_wkt.substring(0,new_wkt.length()-1);
		new_wkt = new_wkt+")";
		System.out.println(wkt);
		System.out.println(new_wkt);
		

	}

}
