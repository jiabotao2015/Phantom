package Phantom.API;

import java.math.BigDecimal;

import Phantom.API.Bean.LonLat;
import Phantom.API.Util.LngLatUtil;

/*import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.API.Bean.LonLat;
import Phantom.API.Util.LngLatUtil;
import Phantom.API.Util.SpatialUtil;*/

public class Test {

	public static void main(String[] args) {

		double a = 123.456787623456;

		double b = new BigDecimal(a).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		System.out.println(b);
		
		
		

	}

}
