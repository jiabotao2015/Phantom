/**   
* @Project: Phantom-Web
* @Title: 
* @Package Phantom.Web.WebService.IWebService
* @Files: ITestWebService.java
* @Description: Spring 集成 webservice 接口
* @author jiabotao 
* @date 2017年2月24日 下午11:33:08
* @version V1.0   
*/ 
package Phantom.Web.WebService.IWebService;

import javax.jws.WebService;

@WebService
public interface ITestWebService {
	
	public String sayHello();

}
