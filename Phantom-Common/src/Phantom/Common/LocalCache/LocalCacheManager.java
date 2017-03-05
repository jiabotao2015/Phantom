/**   
* @Project: Phantom-Common
* @Title: 
* @Package Phantom.Common.LocalCache
* @Files: LocalCacheManager.java
* @Description: TODO
* @author jiabotao 
* @date 2017年3月5日 上午2:04:49
* @version V1.0   
*/ 
package Phantom.Common.LocalCache;


public class LocalCacheManager {
	
	public ILocalCache getLocalCahce(String cacheName){
		if(cacheName==""){
			ILocalCache userCache = new LocalUserCache();
			return userCache;
		}
		if(cacheName=="2"){
			ILocalCache gpsinfoCache = new LocalGPSInfoCache();
			return gpsinfoCache;
		}
		return null;
		
	}

}
