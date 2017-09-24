package Phantom.Data.Initiation.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Bean.LonLat;
import Phantom.API.Util.LngLatUtil;
import Phantom.API.Util.SpatialUtil;
import Phantom.Data.Initiation.Entity.GCJRoad;
import Phantom.Data.Initiation.Entity.OSMRoad;
import Phantom.Data.Initiation.Service.GCJRoadService;
import Phantom.Data.Initiation.Service.OSMRoadService;

public class OSMRoadToGCJRoadTask implements InitializingBean{
	

	@Autowired
	private OSMRoadService osmRoadService;
	
	@Autowired
	private GCJRoadService gcjRoadService;
	
	private static final int pagesize = 1000;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		long count  = osmRoadService.count();
		long page  = (count/pagesize)+1;
		for(int i=0;i<page;i++){
			List<OSMRoad> osmroads = osmRoadService.page(pagesize, i*pagesize);
			List<GCJRoad> gcjroads = new ArrayList<GCJRoad>();
			
			for(int j=0;j<osmroads.size();j++){
				OSMRoad osmroad = osmroads.get(j);
				Geometry osmgeom = osmroad.getGeom();
				Coordinate[] osmcoords = osmgeom.getCoordinates();
				String wkt = "LineString(";
				for(int k=0;k<osmcoords.length;k++){
					Coordinate temposmcoord = osmcoords[k];
					double osmlng = temposmcoord.x;
					double osmlat = temposmcoord.y;
					LonLat gcjcoord = new LonLat();
					LngLatUtil.transform(osmlat, osmlng, gcjcoord);
					double gcjlng = gcjcoord.getLongitude();

					gcjlng = new BigDecimal(gcjlng).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
					double gcjlat = gcjcoord.getLatitude();
					
					gcjlat = new BigDecimal(gcjlat).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
					
					wkt = wkt+gcjlng +" "+gcjlat+",";
				}
				wkt = wkt.substring(0,wkt.length()-1)+")";
				Geometry gcjgeom = SpatialUtil.createGeometryByWKT(wkt);
				gcjgeom.setSRID(4326);
				
				GCJRoad gcjroad = new GCJRoad();
				gcjroad.setBridge(osmroad.getBridge());
				gcjroad.setTunnel(osmroad.getTunnel());
				gcjroad.setCode(osmroad.getCode());
				gcjroad.setFclass(osmroad.getFclass());
				gcjroad.setLayer(osmroad.getLayer());
				gcjroad.setMaxspeed(osmroad.getMaxspeed());
				gcjroad.setOsmid(osmroad.getOsmid());
				gcjroad.setOneway(osmroad.getOneway());
				gcjroad.setGeom(gcjgeom);
				gcjroads.add(gcjroad);
				
			}
			gcjRoadService.save(gcjroads);
			System.out.println(i);
		}
		
	}

}
