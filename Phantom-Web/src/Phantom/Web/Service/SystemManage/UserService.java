package Phantom.Web.Service.SystemManage;

//import java.util.List;

import javax.annotation.Resource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Phantom.Web.Dao.SystemManage.UserDao;
import Phantom.Web.Model.User;

@Service
public class UserService {
	@Resource
	private UserDao userDao;

	@Transactional(readOnly = false)
	public User saveUser(User user) {
		return userDao.save(user);
	}
	
	public User findUser(int userid){
		return userDao.findOne(userid);
	}
	
	public User Login(String username,String password){
		User users = userDao.Login(username,password);
		return users;
	}

}
