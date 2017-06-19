package Phantom.PostGIS.Synchronization;

import java.sql.SQLException;
/*import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Bean.LonLat;
import Phantom.API.Util.LngLatUtil;
import Phantom.API.Util.SpatialUtil;
import Phantom.PostGIS.Synchronization.Entity.GCJRoad;
import Phantom.PostGIS.Synchronization.Entity.OSMRoad;
import Phantom.PostGIS.Synchronization.Service.GCJRoadService;
import Phantom.PostGIS.Synchronization.Service.OSMRoadService;
*/
public class SynchronizationStartUp {


	public static void main(String[] args) throws SQLException {
		//  Auto-generated method stub
	/*	ApplicationContext context = new FileSystemXmlApplicationContext("src/config/SpringContext.xml");
		OSMRoadService roadservice = (OSMRoadService) context.getBean("RoadService");
		GCJRoadService new_road_service = (GCJRoadService)context.getBean("GCJRoadService");
		List<OSMRoad> roads = roadservice.getAll();
		int length = roads.size();
		System.out.println(length);
		for(int i = 0;i<length;i++){
			OSMRoad tmproad = roads.get(i);
			Geometry tmpgeom = tmproad.getGeom();
			Coordinate[] coords = tmpgeom.getCoordinates();
			int coords_length = coords.length;
			Coordinate[] new_coords = new Coordinate[coords_length];
			for(int j=0;j<coords_length;j++){
				Coordinate tmpcoord = coords[j];
				LonLat coord = new LonLat();
				LngLatUtil.transform(tmpcoord.y, tmpcoord.x, coord);
				Coordinate new_coord = new Coordinate(coord.getLongitude(),coord.getLatitude());
				new_coords[j] = new_coord;
			}
			
			String new_wkt = "LINESTRING(";
			for(int k=0;k<coords_length;k++){
				double a = new_coords[k].x;
				double b = new_coords[k].y;
				String append = Double.toString(a)+" "+Double.toString(b)+",";
				new_wkt = new_wkt + append;
			}
			new_wkt = new_wkt.substring(0,new_wkt.length()-1);
			new_wkt = new_wkt+")";
			//System.out.println(new_wkt);
			Geometry gcjgeom = SpatialUtil.createGeometryByWKT(new_wkt);
			gcjgeom.setSRID(4326);
			
			GCJRoad new_road = new GCJRoad();
			
			new_road.setOsmid(tmproad.getOsmid());
			new_road.setCode(tmproad.getCode());
			new_road.setFclass(tmproad.getFclass());
			
			String name = tmproad.getName();
			if(name!=null){
				new_road.setName(name);
			}
			
			String ref = tmproad.getRef();
			if(ref!=null){
				new_road.setRef(ref);
			}
			
			new_road.setOneway(tmproad.getOneway());
			new_road.setMaxspeed(tmproad.getMaxspeed());
			new_road.setBridge(tmproad.getBridge());
			new_road.setTunnel(tmproad.getTunnel());
			new_road.setGeom(gcjgeom);
			
			new_road_service.save(new_road);
			System.out.println(i);
		}
		

	}*/

}}
