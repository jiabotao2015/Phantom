package Phantom.Web.Service.Gemotry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Phantom.Web.GISDao.GisPointDao;
import Phantom.Web.GISModel.Point;

@Service
public class PointService {
	
	@Resource
	GisPointDao dao;
	
	@Transactional(readOnly = false)
	public void save(Point point) {
		dao.save(point);
	}

}
