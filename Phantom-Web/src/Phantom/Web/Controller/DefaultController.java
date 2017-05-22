package Phantom.Web.Controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import Phantom.Web.Gis.Entity.CityPoint;
//import Phantom.Web.Gis.Entity.CityPoint;
import Phantom.Web.Gis.Service.CityPintService;
import Phantom.Web.Model.User;
import Phantom.Web.Service.SystemManage.UserService;
import Phantom.Web.WebSocket.DemoWebSocketHandler;


@Controller
public class DefaultController {

	@Autowired
	private Producer kaptchaProducer;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CityPintService pointService;

	/**
	 * @Description 登录URI跳转
	 * @return
	 */
	@RequestMapping(value = "/Login")
	public String Login() {
		return "Login";
	}

	/**
	 * @Description 登录验证Action
	 * @param Email
	 * @param password
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/LoginAction")
	public ModelAndView LoginAction(String Email, String password, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User loginuser = userService.Login(Email, password);
		if(loginuser!=null){
			session.setAttribute("user", loginuser);
			CityPoint cp = pointService.getCity(9);
			Geometry geom = cp.getShape();
			Coordinate coord = geom.getCoordinate();
			//System.out.println(cp.getCityName());
			return new ModelAndView("redirect:MainPage");
		}
		pointService.toString();
		return new ModelAndView("redirect:/Login");
	}

	/**
	 * @Description 登录成功跳转URI
	 * @return
	 */
	@RequestMapping(value = "/Home")
	public String Home() {
		return "Home";
	}
	
	@RequestMapping(value = "/MainPage")
	public String MainPage() {
		return "MainPage";
	}

	/**
	 * @Description 登录页验证码获取Action
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/kaptcha/*")
	public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0"); 
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg"); // 告诉浏览器返回了一个图片balabala..
		String capText = kaptchaProducer.createText(); // 根据aplicationContext.xml配置生成字符串
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText); // 在session中存储生成的验证码字符串
		BufferedImage bi = kaptchaProducer.createImage(capText); // 根据验证码字符串生成BI流
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/startWebsocket")
	public @ResponseBody String startWebsocket(HttpServletRequest request){
		HttpSession httpSession = request.getSession();
		User loginUser = (User)httpSession.getAttribute("user");
		DemoWebSocketHandler.sendMessage(loginUser, "hello");
		return "OK";
	}

}
