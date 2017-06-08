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
		/*String wkt = "LINESTRING(114.4214069 30.5339769,114.4209179 30.5344712,114.4203133 30.5349179,114.4196142 30.5353576,114.418845 30.5357872,114.4181172 30.5361506,114.4152191 30.5376274,114.4125903 30.5390613,114.4125903 30.5390613,114.4119609 30.5394403,114.411137 30.539884,114.4093045 30.5416619,114.4070736 30.5435907,114.4064052 30.5441267,114.4052947 30.5450174,114.4040362 30.5457002,114.3997434 30.54697,114.3994304 30.5471805,114.3989846 30.5478651,114.3988636 30.5484483,114.3988349 30.5544204,114.3986362 30.5563959,114.3974796 30.5623215,114.3965891 30.5649027,114.3957565 30.5669997,114.3947974 30.5683023,114.3938103 30.5692076,114.3922332 30.5701683,114.3895832 30.5715262,114.388124 30.5725977,114.387795 30.5731071,114.38674 30.5747408,114.3856564 30.5783063,114.3849161 30.580246,114.3841758 30.5813174,114.3832853 30.5822595,114.3817511 30.583137,114.3789855 30.5841879,114.3775362 30.5847368)";
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
		System.out.println(new_wkt);*/
		
		String a = null;
		if(a ==null){
			System.out.println("1");
		}
		if(a.equals(null)){
			System.out.println("0");
		}
		
		

	}

}
