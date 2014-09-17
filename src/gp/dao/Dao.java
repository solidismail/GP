package gp.dao;

import gp.interfac.IDao;
import gp.javabeans.Project;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * 
 * @author Abderrahmane BOUTITAOU & Ismail SEBBANE & Adel BOUFRAH & Fares SALEM
 *         CHERIF La classe Dao, implemente l'interface IDao. Elle permet
 *         l'acces a la base de donnees.
 * 
 */

public class Dao implements IDao {

	private static final long serialVersionUID = 1L;
	
	

	private final EntityManager em;
	
	



	public Dao() {
	
	this.em=null;
	}

	public EntityManager getEm() {
		return em;
	}

	public Dao(EntityManager em) {
		this.em = em;
	}

	/**
	 * Iinitialise l'entity manager.
	 */
	/*
	 * public void init() { factory =
	 * Persistence.createEntityManagerFactory("gp"); }
	 * 
	 * 
	 * public void close() { if (factory != null) factory.close(); }
	 */
	/**
	 * Vide les tables dans la base de donnee.
	 */
	public void reset() {
		try {

			em.getTransaction().begin();
			
	
			TypedQuery<User> q = em.createQuery(
					"SELECT u FROM User u ORDER BY login ASC", User.class);
			List<User> userList = q.getResultList();
			for (int i = 0; i < userList.size(); i++) {
				User u = em.find(User.class, userList.get(i).getLogin());
				System.err.println(userList.get(i).getLogin());
				em.remove(u);
				}

			TypedQuery<Reunion> qR = em.createQuery(
					"SELECT r FROM Reunion r ORDER BY id ASC", Reunion.class);
			List<Reunion> reunionList = qR.getResultList();
			for (int i = 0; i < reunionList.size(); i++) {
				Reunion r = em.find(Reunion.class, reunionList.get(i).getId());
				System.err.println(reunionList.get(i).getId());
				em.remove(r);

			}

			TypedQuery<Project> qP = em.createQuery(
					"SELECT p FROM Project p ORDER BY id ASC", Project.class);
			List<Project> projectList = qP.getResultList();
			for (int i = 0; i < projectList.size(); i++) {
				Project r = em.find(Project.class, projectList.get(i).getId());
				System.err.println(projectList.get(i).getId());
				em.remove(r);

			}
	

	
		} finally {
			commit();
		}

	}

	private void commit() {
		if (em != null) {
			if (em.isOpen()) {
				EntityTransaction t = em.getTransaction();
				if (t.isActive()) {
					try {
						t.commit();
					} catch (PersistenceException e) {
					}
					
				}
			}
		}
		
		
		
	}

	public <T> T find(Class<T> clazz, Object id) {

		try {
			em.getTransaction().begin();
			
			return em.find(clazz, id);
			
		} finally {
			em.getTransaction().commit();
			//commit();
		}
	}

	public <T> void refresh(T entity) {

		try {
			em.refresh(entity);
		} finally {
			//commit();
		}
	}

	public <T> List<T> findAll(String query, Class<T> clazz) {
		try {
			TypedQuery<T> q = em.createQuery(query, clazz);
			return q.getResultList();
		} finally {
			//commit();
		}
	}

	public <T> T add(T entity) {
		// EntityManager em = null;
		try {
			// em = newEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			
			return (entity);
		} finally {
			//commit();
		}

	}

	public <T> T update(T entity) {
		try {
			em.getTransaction().begin();
			entity = em.merge(entity);
			em.getTransaction().commit();
		} finally {
			//commit();
		}
		return entity;
	}

	public <T> void remove(Class<T> clazz, Object pk) {
		try {
			em.getTransaction().begin();
			T entity = em.find(clazz, pk);
			if (entity != null) {
				em.remove(entity);
				em.getTransaction().commit();
			}
		} finally {
			//commit();
		}
	}

	public void close() {
//		if (em!=null) {
//			
//			em.close();
//		}
	}


	
}
