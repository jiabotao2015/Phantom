package Phantom.ReverseGeocoding.Controller;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Phantom.ReverseGeocoding.Service.GeoService;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
	        String address = "阳澄湖生态休闲旅游度假区澄林路";
	        address = address.substring(0,address.indexOf("路")+1);
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
	        

	}

}
