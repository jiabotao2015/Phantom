package Phantom.Web.GisService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Phantom.Web.GISDao.GisPointDao;
import Phantom.Web.GISModel.Point;

@Service
public class GisPointService {
	
	@Resource
	GisPointDao dao;
	
	@Transactional(readOnly = false)
	public void saveUser(Point user) {
		dao.save(user);
	}

}
