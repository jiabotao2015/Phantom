/**   
* @Project: Phantom-Common
* @Title: 
* @Package Phantom.Common.LocalCache
* @Files: LocalUserCache.java
* @Description: TODO
* @author jiabotao 
* @date 2017年3月5日 上午2:11:39
* @version V1.0   
*/ 
package Phantom.Common.LocalCache;

import java.util.HashMap;
import java.util.Map;

import Phantom.API.Bean.User;

public class LocalUserCache implements ILocalCache {
	
	private Map<String,User> usercache= new HashMap<String,User>();
	
	
	public User getCache(String userName){
		User user = usercache.get(userName);
		return user;
	}

}
