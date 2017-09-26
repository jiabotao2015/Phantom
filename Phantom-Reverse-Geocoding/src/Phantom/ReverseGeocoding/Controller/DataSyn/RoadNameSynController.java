package Phantom.ReverseGeocoding.Controller.DataSyn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.ReverseGeocoding.Entity.GCJRoad;
import Phantom.ReverseGeocoding.Service.DataSyn.GCJRoadService;
import Phantom.ReverseGeocoding.Utils.HttpUtils;

@Controller
public class RoadNameSynController {
	
	@Autowired
	private GCJRoadService service;
	
	private static int offset = 0;
	
	@ResponseBody
	@RequestMapping(value = "/data/getroadname.do")
	public String getRoadFromGaode(String gaodekey){
		
		for(int i=0;i<10000;i++){
			List<GCJRoad> roads = service.getUnNameRoad(2000,offset*2000);
			offset = offset +1;
			for(int j=0;j<roads.size();j++){
				GCJRoad road = roads.get(j);
				road.setRef("checked");
				Geometry geom = road.getGeom();
				Coordinate[] coords= geom.getCoordinates();
				
				int midcoordidx = (coords.length/2);
				
				Coordinate coord = coords[midcoordidx];
				
				String lng = coord.x+"";
				String lat = coord.y+"";
				String layer = road.getLayer()+"";
				
				String url = "http://restapi.amap.com/v3/geocode/regeo?key="+
						gaodekey+"&location="+lng+","+lat+"&poitype=&radius=30&extensions=all&batch=false&roadlevel="+layer;
				String result = HttpUtils.sendPost(url, null);
				
				System.out.println(result);
				
				result = result.substring(result.indexOf("roads")+7, result.indexOf("roadinters")-2);
				if(result.contains("name")&&result.contains("direction")){
					result = result.substring(result.indexOf("name"), result.indexOf("direction"));
				}
				if(result.contains(":")){
					result = result.substring(7,result.length()-3);
				}
				if(!result.contains("[")){
					road.setName(result);
					System.out.println(road.getGid()+"路名："+result);
				}
				service.save(road);
			}
		}
		return "OK";
	}
}
