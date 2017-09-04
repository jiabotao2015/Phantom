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
import Phantom.ReverseGeocoding.Utils.HttpUtils;

@Controller
public class GCJRoadController {

	@Autowired
	private GCJRoadService gcjroadService;
	
	private static int p = 5;//key请求量用完以后，换新的key时可用此记录不发生重复请求

	
	/**
	 * 1取数据库中的道路
	 * 2在道路上取点，取道路上中间的点作为请求的经纬度（去线段头尾）
	 * 3调用高德api 获取http响应 解析字符串，保存有用的字段
	 * @param key
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/UpdateRoadFromGaode",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String UpdateRoadFromGaode(String key,String page){
		//p=p-1;
		for (int k =p; k < 150; k++) {
		p = p+1;
		System.out.println(k);
			ArrayList<GCJRoad> temproads = gcjroadService.getUnNamedGCJRoad(2000, 2000 * k);//分页查询道路
			for (int i = 0; i < temproads.size(); i++) {
				GCJRoad temp_road = temproads.get(i);
				Geometry geom = temp_road.getGeom();
				Coordinate[] coords = geom.getCoordinates();//取道路上的点
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
					String oresult = HttpUtils.sendGet(url, null);//请求高德api获取result
					System.out.println("gid:"+temp_road.getGid());
					System.out.println("经纬度："+lon+","+lat);
					System.out.println("result"+oresult);
					String result = oresult.substring(oresult.indexOf("formatted_address"), oresult.indexOf("addressComponent") - 2);
					String[] namearr = result.split(":");
					String address = namearr[1];
					if (address.length() > 2) {//如果adress的长度大于2，认为请求正常，开始解析道路地址
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


}
