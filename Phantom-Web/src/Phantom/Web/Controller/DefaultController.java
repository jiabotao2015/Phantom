package Phantom.Web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Phantom.API.Bean.User;

@Controller
public class DefaultController {
	
	@RequestMapping(value = "/Login")
	public String Login(){
		return "Login";
	}
	
	@RequestMapping(value = "/LoginAction")
	public ModelAndView LoginAction(String Email, String password, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = new User();		
		if(Email.equals("jiabotao@gmail.com")&&password.equals("jiabotao")){
			user.setUserId(1);
			user.setUserName(Email);
			user.setPassWord(password);
			session.setAttribute("user",user);
			return new ModelAndView("redirect:/Home");
		}
		return new ModelAndView("redirect:/Login");
	}
	
	@RequestMapping(value = "/VehicleMonitor/Map")
	public String Map(){
		return "VehicleMonitor/Map";
	}
	
	@RequestMapping(value = "/Home")
	public String Home(){
		return "Home";
	}
	

}
