package Phantom.ReverseGeocoding.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.ReverseGeocoding.Entity.GCJRoad;
import Phantom.ReverseGeocoding.Service.GCJRoadService;
import Phantom.ReverseGeocoding.Service.GeoService;
import Phantom.ReverseGeocoding.Service.ReverseGeocodingService;
import Phantom.ReverseGeocoding.Utils.HttpUtils;

@Controller
public class GCJRoadController {

	@Autowired
	private ReverseGeocodingService service;

	@Autowired
	private GeoService geoservice;

	@Autowired
	private GCJRoadService gcjroadService;
	
	private static int p = 5;

	@RequestMapping(value = "/updateRoad", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody String getUnNamedGCJRoad() {
		for (int k = 1; k < 400; k++) {
			ArrayList<GCJRoad> temproads = gcjroadService.getUnNamedGCJRoad(2000, 2000 * k);

			for (int i = 0; i < temproads.size(); i++) {
				GCJRoad temp_road = temproads.get(i);
				Geometry geom = temp_road.getGeom();
				Coordinate[] coords = geom.getCoordinates();
				int coordsize = coords.length;
				for (int j = 0; j < coordsize; j++) {
					Coordinate coord = coords[j];
					double lon = coord.x;
					double lat = coord.y;
					String url = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + lon + "&type=100";
					url = "http://restapi.amap.com/v3/geocode/regeo?key=8f514706623f68d8595ccbde6115fd9b&location="
							+ lon + "," + lat;
					// url =
					// "http://apis.map.qq.com/ws/geocoder/v1/?location="+lat+","+lon+"&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&get_poi=0";
					String result = HttpUtils.sendGet(url, null);
					System.out.println(result);
					result = result.substring(result.indexOf("street"), result.indexOf("number") - 2);
					String[] namearr = result.split(":");
					String name = namearr[2];
					if (name.length() > 2) {
						name = name.substring(1, name.length() - 1);
						System.out.println(name);
						temp_road.setName(name);
						System.out.println(temp_road.getGid());
						gcjroadService.save(temp_road);
						System.out.println("K:" + k);
						j = coordsize + 1;
					}
				}
			}
		}
		return "OK";
	}
	
	@RequestMapping(value="/UpdateRoadFromGaode",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String UpdateRoadFromGaode(String key,String page){
		//p=p-1;
		for (int k =p; k < 150; k++) {
		p = p+1;
		System.out.println(k);
			ArrayList<GCJRoad> temproads = gcjroadService.getUnNamedGCJRoad(2000, 2000 * k);
			for (int i = 0; i < temproads.size(); i++) {
				GCJRoad temp_road = temproads.get(i);
				Geometry geom = temp_road.getGeom();
				Coordinate[] coords = geom.getCoordinates();
				//System.out.println(temp_road.getGid());
				int coordsize = coords.length;
				Coordinate coord = new Coordinate();
				/*if(coordsize == 2){
					 coord = coords[1];
				}
				if(coordsize == 3){
					 coord = coords[1];
				}
				if(coordsize == 4){
					coord = coords[2];
				}
				if(coordsize == 5){
					coord = coords[3];
				}
				if(coordsize == 6){
					coord = coords[4];
				}
				if(coordsize == 7){
					coord = coords[5];
				}*/
				if(coordsize > 15){
					coord = coords[14];
					double lon = coord.x;
					double lat = coord.y;
					String url = "http://restapi.amap.com/v3/geocode/regeo?"+"key="+key+"&location="
							+ lon + "," + lat;
					String oresult = HttpUtils.sendGet(url, null);
					System.out.println("gid:"+temp_road.getGid());
					System.out.println("经纬度："+lon+","+lat);
					System.out.println("result"+oresult);
					String result = oresult.substring(oresult.indexOf("formatted_address"), oresult.indexOf("addressComponent") - 2);
					String[] namearr = result.split(":");
					String address = namearr[1];
					if (address.length() > 2) {
						if(address.contains("县道")){
				        	address = address.substring(address.indexOf("县道")-3);
				        	address = address.replaceAll("\"", "");
				        	System.out.println(address);
				        	temp_road.setName(address);
							gcjroadService.save(temp_road);
				        }
				        if(address.contains("省道")){
				        	address = address.substring(address.indexOf("省道")-3);
				        	address = address.replaceAll("\"", "");
				        	System.out.println(address);
				        	temp_road.setName(address);
							gcjroadService.save(temp_road);
				        }
				        if(address.contains("国道")){
				        	address = address.substring(address.indexOf("国道")-3);
				        	address = address.replaceAll("\"", "");
				        	System.out.println(address);
				        	temp_road.setName(address);
							gcjroadService.save(temp_road);
				        }
				        if(address.contains("乡道")){
				        	address = address.substring(address.indexOf("乡道")-3);
				        	address = address.replaceAll("\"", "");
				        	System.out.println(address);
				        	temp_road.setName(address);
							gcjroadService.save(temp_road);
				        }
				        if(address.contains("公路")){
				        	address = address.substring(0,address.indexOf("公路")+2);
				        	if(address.contains("區")){
				        		address = address.substring(address.indexOf("區")+1);
				        	}
				        	if(address.contains("镇")){
				        		address = address.substring(address.indexOf("镇")+1);
				        	}
				        	if(address.contains("乡")){
				        		address = address.substring(address.indexOf("乡")+1);
				        	}
				        	if(address.contains("县")){
				        		address = address.substring(address.indexOf("县")+1);
				        	}
				        	if(address.contains("区")){
				        		address = address.substring(address.indexOf("区")+1);
				        	}
				        	address = address.replaceAll("\"", "");
				        	System.out.println(address);
				        	temp_road.setName(address);
							gcjroadService.save(temp_road);
				        }
				        if(address.contains("高速")&&!address.contains("高速公路")){
				        	address = address.substring(0,address.indexOf("高速")+2);
				        	if(address.contains("區")){
				        		address = address.substring(address.indexOf("區")+1);
				        	}
				        	if(address.contains("镇")){
				        		address = address.substring(address.indexOf("镇")+1);
				        	}
				        	if(address.contains("乡")){
				        		address = address.substring(address.indexOf("乡")+1);
				        	}
				        	if(address.contains("县")){
				        		address = address.substring(address.indexOf("县")+1);
				        	}
				        	if(address.contains("区")){
				        		address = address.substring(address.indexOf("区")+1);
				        	}
				        	if(address.contains("道")){
				        		address = address.substring(address.indexOf("道")+1);
				        	}
				        	address = address.replaceAll("\"", "");
				        	System.out.println(address);
				        	temp_road.setName(address);
							gcjroadService.save(temp_road);
				        }
				        if(address.contains("路")&& !address.contains("公路")){
				        	address = address.substring(0,address.indexOf("路")+1);
				        	if(address.contains("區")){
				        		address = address.substring(address.indexOf("區")+1);
				        	}
				        	if(address.contains("區")){
				        		address = address.substring(address.indexOf("區")+1);
				        	}
				        	if(address.contains("镇")){
				        		address = address.substring(address.indexOf("镇")+1);
				        	}
				        	if(address.contains("乡")){
				        		address = address.substring(address.indexOf("乡")+1);
				        	}
				        	if(address.contains("县")){
				        		address = address.substring(address.indexOf("县")+1);
				        	}
				        	if(address.contains("区")){
				        		address = address.substring(address.indexOf("区")+1);
				        	}
				        	if(address.contains("道")){
				        		address = address.substring(address.indexOf("道")+1);
				        	}
				        	address = address.substring(0,address.indexOf("路")+1);
				        	address = address.replaceAll("\"", "");
				        	System.out.println(address);
				        	temp_road.setName(address);
							gcjroadService.save(temp_road);
				        }
				        if(address.contains("大道")){
				        	address = address.substring(0,address.indexOf("大道")+2);
				        	if(address.contains("區")){
				        		address = address.substring(address.indexOf("區")+1);
				        	}
				        	if(address.contains("镇")){
				        		address = address.substring(address.indexOf("镇")+1);
				        	}
				        	if(address.contains("乡")){
				        		address = address.substring(address.indexOf("乡")+1);
				        	}
				        	if(address.contains("县")){
				        		address = address.substring(address.indexOf("县")+1);
				        	}
				        	if(address.contains("区")){
				        		address = address.substring(address.indexOf("区")+1);
				        	}
				        	address = address.replaceAll("\"", "");
				        	System.out.println(address);
				        	temp_road.setName(address);
							gcjroadService.save(temp_road);
				        }
				        if(!address.contains("大道")&&!address.contains("路")&&
				        		!address.contains("高速")&&!address.contains("省道")&&!address.contains("国道")&&!address.contains("县道")&&!address.contains("乡道")){
				        	result = oresult.substring(oresult.indexOf("street"), oresult.indexOf("number") - 2);
				        	String[] resultarr = result.split(":");
				        	String street = resultarr[2];
				        	if(street.length()>2){
				        		street = street.replaceAll("\"", "");
				        		System.out.println(street);
				        		temp_road.setName(street);
				        		gcjroadService.save(temp_road);
				        	}
				        	
				        	System.out.println(result);
				        }}
				}
				//if(coordsize>2&&coordsize<5){
					//Coordinate coord = coords[3];
				
				        
					//}
						/*name = name.substring(1, name.length() - 1);
						System.out.println(name);
						temp_road.setName(name);
						gcjroadService.save(temp_road);
						}*/
				}
			}
		//}
		return "OK";
	}
	
	@RequestMapping(value="/modifyRoad",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String modifyRoad(){
		ArrayList<GCJRoad> temproads = gcjroadService.getGaoSuGCJRoad();
		for(int i = 0 ;i<temproads.size();i++){
			GCJRoad temp_road = temproads.get(i);
			String name = temp_road.getName();
			name = name.substring(0, name.length()-2);
			temp_road.setName(name);
			gcjroadService.save(temp_road);
		}
		return "OK";
	}

}
