package Phantom.ReverseGeocoding.Controller;

import java.sql.SQLException;
import java.util.List;

import org.postgis.Geometry;
import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Phantom.ReverseGeocoding.Service.GeoService;
import Phantom.ReverseGeocoding.Service.ReverseGeocodingService;
import Phantom.ReverseGeocoding.Utils.HttpUtils;

@Controller
public class ReverseController {
	
	@Autowired
	private ReverseGeocodingService service;
	
	@Autowired
	private GeoService geoservice;
	
	//@RequestMapping("/test")
	@RequestMapping(value="/test",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String test(String lon,String lat) throws Exception{
		String result  = service.reserve(lon, lat);
		return result;
	}
	
	@RequestMapping(value="/getWKT",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String getWKT() throws SQLException{
		
		String url = "http://localhost:8080/Phantom-Reverse-Geocoding/test?lon=121.451370&lat=29.842689";
		String result = HttpUtils.sendGet(url, null);
		//String wkt = geoservice.getWKT();
		System.out.println(result);
		
		return "";
		
	}
	
	@RequestMapping(value="/getsynData",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String synbussnispoint() throws SQLException{
		geoservice.getzyywd();
		return "";
	}
	
	@RequestMapping(value="/ReverseEncoding",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String ReverseEncoding(String lon,String lat) throws Exception{
		long start = System.currentTimeMillis();
		String result  = service.reserve(lon, lat);
		long end = System.currentTimeMillis();
		long diff = end - start;
		System.out.println("Difference is : " + diff);
		return result;
	}
	
	
	
	

}
