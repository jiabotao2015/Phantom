package Phantom.PostGIS.Synchronization;

import java.sql.SQLException;
import java.util.ArrayList;

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

public class Test {
	
	private static ArrayList<Integer> all = new ArrayList<Integer>();

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws SQLException {

		String contextPath = "src/config/SpringTestContext.xml";
		ApplicationContext context = new FileSystemXmlApplicationContext(contextPath);
		OSMRoadService osmroadservice = (OSMRoadService) context.getBean("OSMRoadService");
		GCJRoadService gcjroadservice = (GCJRoadService) context.getBean("GCJRoadService");
		int count = osmroadservice.getCount();
		//count = 55;
		int pagelimit = (count/500)+1;
		//ArrayList<OSMRoad> osm_road_array = osmroadservice.findByPage(20, 0);
		//System.out.println(osm_road_array.size());
		ArrayList<OSMRoad> osm_road_array = new ArrayList<OSMRoad>();
		System.out.println(pagelimit);
		for(int i=0;i<pagelimit;i++){
			int offset = 0+i*500;
			osm_road_array = osmroadservice.findByPage(500, offset);
			ArrayList<GCJRoad> gcj_road_array = showroad(osm_road_array);
			gcjroadservice.save(gcj_road_array);
			System.out.println(offset);
		}
		//System.out.println(all.size());
	}

	private static ArrayList<GCJRoad> showroad(ArrayList<OSMRoad> osm_road_array) {
		// TODO Auto-generated method stub
		ArrayList<GCJRoad> gcj_road_array = new ArrayList<GCJRoad>();
		for(int i=0;i<osm_road_array.size();i++){
			OSMRoad osmroad = osm_road_array.get(i);
			Geometry osmroadgeom = osmroad.getGeom();
			Coordinate[] coords = osmroadgeom.getCoordinates();
			int coords_length = coords.length;
			Coordinate[] gcj_coords = new Coordinate[coords_length];
			for(int j=0;j<coords_length;j++){
				Coordinate tmpcoord = coords[j];
				LonLat coord = new LonLat();
				LngLatUtil.transform(tmpcoord.y, tmpcoord.x, coord);
				Coordinate new_coord = new Coordinate(coord.getLongitude(),coord.getLatitude());
				gcj_coords[j] = new_coord;
			}
			String new_wkt = "LINESTRING(";
			for(int k=0;k<coords_length;k++){
				double a = gcj_coords[k].x;
				double b = gcj_coords[k].y;
				String append = Double.toString(a)+" "+Double.toString(b)+",";
				new_wkt = new_wkt + append;
			}
			new_wkt = new_wkt.substring(0,new_wkt.length()-1);
			new_wkt = new_wkt+")";
			
			Geometry gcjgeom = SpatialUtil.createGeometryByWKT(new_wkt);
			gcjgeom.setSRID(4326);
			
			GCJRoad gcj_road = new GCJRoad();
			
			gcj_road.setOsmid(osmroad.getOsmid());
			gcj_road.setCode(osmroad.getCode());
			gcj_road.setFclass(osmroad.getFclass());
			
			String name = osmroad.getName();
			if(name!=null){
				gcj_road.setName(name);
			}
			
			String ref = osmroad.getRef();
			if(ref!=null){
				gcj_road.setRef(ref);
			}
			
			gcj_road.setOneway(osmroad.getOneway());
			gcj_road.setMaxspeed(osmroad.getMaxspeed());
			gcj_road.setBridge(osmroad.getBridge());
			gcj_road.setTunnel(osmroad.getTunnel());
			gcj_road.setGeom(gcjgeom);
			gcj_road_array.add(gcj_road);
		}
		//GCJRoadService
		return gcj_road_array;
	}

}
