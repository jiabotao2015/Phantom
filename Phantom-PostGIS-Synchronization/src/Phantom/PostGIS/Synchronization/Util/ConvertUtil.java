package Phantom.PostGIS.Synchronization.Util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Bean.LonLat;
import Phantom.API.Util.LngLatUtil;
import Phantom.API.Util.SpatialUtil;
import Phantom.PostGIS.Synchronization.Entity.OSMRoad;

public class ConvertUtil {
	
	public static Geometry OSMToDCJ(OSMRoad osmroad){
		Geometry geom = osmroad.getGeom();
		Coordinate[] coords = geom.getCoordinates();
		int coords_length = coords.length;
		for(int i=0;i<coords_length;i++){
			Coordinate tmpcoord = coords[i];
			LonLat coord = new LonLat();
			LngLatUtil.transform(tmpcoord.y, tmpcoord.x, coord);
			Coordinate new_coord = new Coordinate(coord.getLongitude(),coord.getLatitude());
			coords[i]= new_coord;
		}
		Geometry gcjgeom = getLineStringWKT(coords);
		
		return gcjgeom;
	}
	
	public static Geometry getLineStringWKT(Coordinate[] coords){
		int coords_length = coords.length;
		String wkt = "LINESTRING(";
		for(int i=0;i<coords_length;i++){
			double lon = coords[i].x;
			double lat = coords[i].y;
			String append = Double.toString(lon)+" "+Double.toString(lat)+",";
			wkt = wkt + append;
		}
		wkt = wkt.substring(0,wkt.length()-1);
		wkt = wkt+")";
		Geometry gcjgeom = SpatialUtil.createGeometryByWKT(wkt);
		gcjgeom.setSRID(4326);
		return gcjgeom;
	}

}
