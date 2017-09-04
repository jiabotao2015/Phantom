package Phantom.Web.Controller.GisController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Util.SpatialUtil;
import Phantom.Web.Gis.Entity.City;
import Phantom.Web.Gis.Service.CityService;
import Phantom.Web.Utils.HttpUtils;

@Controller
public class GisDataManagementController {
	
	@Autowired
	private CityService cityService;
	
	@ResponseBody
	@RequestMapping(value="/City", method = RequestMethod.GET)  
	public String getCity(String citycode){
		
		
		String url = "http://restapi.amap.com/v3/config/district?subdistrict=1&showbiz=false&extensions=all&key=1b0e5166d02fbd3961027fb68c4c8de0&s=rsv3&output=json&level=province&"
				+ "keywords="+citycode
				+ "&callback=jsonp_11464_&platform=JS&logversion=2.0&sdkversion=1.3&appname=http%3A%2F%2Flocalhost%3A8080%2FPhantom-Web%2Findex.jsp&csid=A1CE8095-519E-4951-8927-638A9FD3159D";
		
		String all = HttpUtils.sendGetString(url);
		//System.out.println(all);
		String name = all.substring(all.indexOf("name")+7,all.indexOf("polyline")-3);
		String polyString = all.substring(all.indexOf("polyline")+11, all.indexOf("center")-3);
		polyString = polyString.replaceAll(",", " ");
		String[] polygons = polyString.split("\\|");
		String wkt = "MULTIPOLYGON((";
		for(int i = 0; i<polygons.length;i++){
			String pp = polygons[i];
			pp = pp.replace(";", ",");
			wkt = wkt+"("+pp+"),";
		}
		wkt = wkt.substring(0,wkt.length()-1);
		wkt = wkt+"))";
		Geometry geom= SpatialUtil.createGeometryByWKT(wkt);
		geom.setSRID(4326);
		City city = new City();
		city.setAdcode(citycode);
		city.setCityName(name);
		city.setGeom(geom);
		cityService.save(city);
		
		return "OK";
	}
	
	@RequestMapping(value = "/MainSMap")
	public String MainMapUrl(){
		return "GIS/MainMap";
	}

}
