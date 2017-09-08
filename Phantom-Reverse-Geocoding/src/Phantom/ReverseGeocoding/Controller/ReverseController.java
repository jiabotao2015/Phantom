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

@Controller
public class ReverseController {
	
	@Autowired
	private ReverseGeocodingService service;
	
	@Autowired
	private GeoService geoservice;
	
		
	@RequestMapping(value="/ReverseEncoding",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String ReverseEncoding(String lon,String lat) throws Exception{
		long start = System.currentTimeMillis();
		//String result  = service.reserve(lon, lat);
		String result  = service.reserveWithPool(lon, lat);
		double a = 114;
		double b = 30;
		for(int i=0;i<2000;i++){
			b = b + 0.0001;
			result  = service.reserveWithPool(a+"", b+"");
			System.out.println(result);
		}
		long end = System.currentTimeMillis();
		long diff = end - start;
		System.out.println("Difference is : " + diff);
		return result;
	}
	
	@RequestMapping(value="/ReverseEncodingWithHibernate",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public @ResponseBody String ReverseEncodingWithHibernate(String lon,String lat) throws Exception{
		long start = System.currentTimeMillis();
		String result  = service.reserve(lon, lat);
		//String result  = service.reserveWithPool(lon, lat);
		
		
		double a = 114;
		double b = 30;
		for(int i=0;i<2000;i++){
			b = b + 0.0001;
			result  = service.reserve(a+"", b+"");
		}
		long end = System.currentTimeMillis();
		long diff = end - start;
		System.out.println("Difference is : " + diff);
		return result;
	}
	
	
	
	
	
	

}
