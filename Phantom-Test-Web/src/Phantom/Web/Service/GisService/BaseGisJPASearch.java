package Phantom.Web.Service.GisService;

import javax.persistence.EntityManagerFactory;

public class BaseGisJPASearch {
	
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

}
