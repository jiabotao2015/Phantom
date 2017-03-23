/**   
* @Project: Phantom-Common
* @Title: 
* @Package Phantom.Common.LocalCache
* @Files: LocalGPSInfoCache.java
* @Description: 
* @author jiabotao 
* @date 2017年3月5日 上午2:23:46
* @version V1.0   
*/ 
package Phantom.Common.LocalCache;

import java.util.HashMap;
import java.util.Map;

import Phantom.API.Bean.GpsInfo;

public class LocalGPSInfoCache implements ILocalCache{

	private Map<String,GpsInfo> usercache= new HashMap<String,GpsInfo>();
	
	public GpsInfo getCache(String vehicleId){
		GpsInfo gpsinfo = usercache.get(vehicleId);
		return gpsinfo;
	}
}
