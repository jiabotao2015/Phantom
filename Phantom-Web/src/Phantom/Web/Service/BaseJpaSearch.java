package Phantom.Web.Service;

import javax.persistence.EntityManagerFactory;

public class BaseJpaSearch {
	
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

}
