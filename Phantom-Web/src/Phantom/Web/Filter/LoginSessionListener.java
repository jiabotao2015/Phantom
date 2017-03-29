/**   
* @Project: Phantom-Web
* @Title: 单点登录Listener
* @Package Phantom.Web.Filter
* @Files: LoginSessionListener.java
* @Description: 监听session变化，实现单点登录，后登陆的用户挤掉前登陆的人
* @author jiabotao 
* @date 2017年2月24日 上午1:03:23
* @version V1.0   
*/
package Phantom.Web.Filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import Phantom.Web.Model.User;

public class LoginSessionListener implements HttpSessionAttributeListener {

	private Map<String, HttpSession> map = new ConcurrentHashMap<String, HttpSession>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.
	 * servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		if (name.equals("user")) {
			User user = (User) event.getValue();
			if (map.get(user.getUserName()) != null) {
				HttpSession session = map.get(user.getUserName());
				session.removeAttribute(user.getUserName());
				session.invalidate();
			}
			map.put(user.getUserName(), event.getSession());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.
	 * servlet.http.HttpSessionBindingEvent) 
	 * 当向session中移除数据触发
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		if (name.equals("user")) {
			User user = (User) event.getValue();
			map.remove(user.getUserName());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.
	 * servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {

	}

	public Map<String, HttpSession> getMap() {
		return map;
	}

	public void setMap(Map<String, HttpSession> map) {
		this.map = map;
	}

}
