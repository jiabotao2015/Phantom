package Phantom.ReverseGeocoding.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReverseGeocodingService {
	
	@Autowired
	private BaseJpaSearch jpaSearch;
	
	/**
	 * 执行逆地址解析存储过程
	 * @param lon
	 * @param lat
	 * @return
	 * @throws Exception
	 */
	public String reserve(String lon, String lat) throws Exception{
		String reversesql = "select reverse("+lon+","+lat+")";
		List<Object[]> reverseObj = jpaSearch.findBySQL(reversesql);
		Object[] reversearr = reverseObj.toArray();
		String reverse = reversearr[0].toString();
		return reverse;
	}

}
