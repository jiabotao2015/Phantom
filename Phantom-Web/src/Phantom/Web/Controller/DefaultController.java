package Phantom.Web.Controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import Phantom.Web.GISModel.Point;
import Phantom.Web.Model.User;
import Phantom.Web.Service.Gemotry.PointService;
import Phantom.Web.Service.SystemManage.UserService;


@Controller
public class DefaultController {

	@Autowired
	private Producer kaptchaProducer;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PointService pointService;

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
		System.out.println(request.getCharacterEncoding());
		User me = new User();
		me.setUserName("jiabotao");
		me.setPassWord("jiabotao0819");
		session.setAttribute("user", me);
		userService.saveUser(me);
		
		
		Point pt = new Point();
		pt.setGemo("this is the_geom for describe the pont shape");
		pt.setPointName("贾博韬测试点");
		pointService.save(pt);
		/*User user = new User();
		if (Email.equals("jiabotao@gmail.com") && password.equals("jiabotao")) {
			user.setUserId(1);
			user.setUserName(Email);
			user.setPassWord(password);
			session.setAttribute("user", user);
			return new ModelAndView("redirect:/Home");

		}
		
		Point
		
		userService.saveUser(me);*/
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
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate"); // Set
																					// standard
																					// HTTP/1.1
																					// no-cache
																					// headers.
		response.addHeader("Cache-Control", "post-check=0, pre-check=0"); // Set
																			// IE
																			// extended
																			// HTTP/1.1
																			// no-cache
																			// headers
																			// (use
																			// addHeader).
		response.setHeader("Pragma", "no-cache"); // Set standard HTTP/1.0
													// no-cache header.
		response.setContentType("image/jpeg"); // 告诉浏览器返回了一个图片balabala...

		String capText = kaptchaProducer.createText(); // 根据aplicationContext.xml配置生成字符串
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText); // 在session中存储生成的验证码字符串
		BufferedImage bi = kaptchaProducer.createImage(capText); // 根据验证码字符串生成BI流
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.flush();
		out.close();
	}

}
