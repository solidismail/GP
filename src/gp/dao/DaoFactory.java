package gp.dao;

import gp.interfac.IDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {
	
	private EntityManagerFactory factory = null;

	
	public DaoFactory() {
		factory = Persistence.createEntityManagerFactory("gp");
	}
	
	public Dao createDAO(){
		return new Dao(factory.createEntityManager());
		//return factory.createEntityManager():
	}
	
}
