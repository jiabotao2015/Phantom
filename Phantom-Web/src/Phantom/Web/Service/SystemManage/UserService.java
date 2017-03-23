package Phantom.Web.Service.SystemManage;

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
	public void saveUser(User user) {
		userDao.save(user);
	}

}
