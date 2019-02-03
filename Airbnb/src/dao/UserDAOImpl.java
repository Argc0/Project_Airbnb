package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.User;

public class UserDAOImpl implements UserDAO 
{

	@Override
	public User find_login(String username, String password) {
		User user = null;
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select u from User u where u.username = :username and u.password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
		tx.commit();

		if (users != null && users.size() == 1) {
			user = users.get(0);
		}
        return user;
	}
	
	@Override
	public boolean username_exists (String username) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select u from User u where u.username = :username");
		q.setParameter("username", username);
		
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
		tx.commit();

		if (users.size()>0) {
			return true;
		}
        return false;
	}
	@Override
	public int get_user_id (String username) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select u.idUser from User u where u.username = :username");
		q.setParameter("username", username);

		int id = (int) q.getSingleResult();
		tx.commit();
		return id;
	}
	
	public User get_user (String username) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.clear();
		
		Query q = em.createQuery("Select u from User u where u.username = :username");
		q.setParameter("username", username);
		
		User u = (User) q.getSingleResult();
		return u;
	}

	@Override
	public boolean aprrove_user(int id){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Update User set isApproved=true where idUser = :id");
		int updateCount = q.setParameter("id", id).executeUpdate();
		
		tx.commit();
		if(updateCount==1)
			return true;
		else
			return false;
	}
	
	@Override
	public void updateUser(User user) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			em.merge(user);
			em.flush();
			tx.commit();
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
				System.out.println(e.getMessage());
		}
	}
	
	public boolean changePassword (String usrname, String pwd) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Update User set password=:pwd where username = :usrname");
		int updateCount = q.setParameter("pwd", pwd).setParameter("usrname", usrname).executeUpdate();

		tx.commit();
		if(updateCount==1)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean insertPhoto(int id, String filename){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Update User u set u.photo = :filename where u.idUser = :id");
		
		int updateCount = q.setParameter("filename", filename).setParameter("id", id).executeUpdate();
		
		tx.commit();
		if(updateCount==1)
			return true;
		else
			return false;
	}
	
	@Override
	public List<User> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("User.findAll");
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
        return users;
	}

	@Override
	public void create(User user) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(user);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
				System.out.println(e.getMessage());
		}
	}
	
	@Override
	public int return_count(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("SELECT u.idUser FROM User u ORDER BY u.idUser DESC");
		query.setMaxResults(1);
        int  count = (int) query.getSingleResult();
        return count;
	}
}

