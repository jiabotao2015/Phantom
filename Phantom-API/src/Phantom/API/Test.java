package Phantom.API;


import com.vividsolutions.jts.geom.Point;

import Phantom.API.Bean.LonLat;
import Phantom.API.Util.LngLatUtil;
import Phantom.API.Util.SpatialUtil;

public class Test {

	public static void main(String[] args) {
		/*LonLat coord = new LonLat();
		double wgLat = 30.50103;
		double	wgLon = 114.42840;
		LngLatUtil.transform(wgLat, wgLon, coord);
		System.out.println(coord.getLatitude());
		System.out.println(coord.getLongitude());*/
		
		SpatialUtil su = new SpatialUtil();
		Point pt = su.createPoint();
		System.out.println(pt.getSRID());
		

	}

}
