/**   
* @Project: Phantom-Web
* @Title: 
* @Package Phantom.Web.WebService.WebServiceImpl
* @Files: TestWebServiceImpl.java
* @Description: webservice 实现类
* @author jiabotao 
* @date 2017年2月24日 下午11:34:37
* @version V1.0   
*/ 
package Phantom.Web.WebService.WebServiceImpl;

import Phantom.Web.WebService.IWebService.ITestWebService;

public class TestWebServiceImpl implements ITestWebService {

	/* (non-Javadoc)
	 * @see Phantom.Web.WebService.IWebService.ITestWebService#sayHello()
	 */
	@Override
	public String sayHello() {
		return "Hello";
	}

}
