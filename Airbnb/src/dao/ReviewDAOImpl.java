package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Review;

public class ReviewDAOImpl implements ReviewDAO {
	
	@Override
	public List<Review> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Review.findAll");
		@SuppressWarnings("unchecked")
		List<Review> reviews = query.getResultList();  
        return reviews;
	}

	@Override
	public void create(Review review) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(review);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
				System.out.println(e.getMessage());
		}
	}
	
	@Override
	public List<Review> reviews_for_houses(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("Select r from Review r where r.review_for_host=false");
		@SuppressWarnings("unchecked")
		List<Review> reviews = query.getResultList();  
        return reviews;
	}
	
	@Override
	public List<Review> reviews_for_hosts(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("Select r from Review r where r.review_for_host=true");
		@SuppressWarnings("unchecked")
		List<Review> reviews = query.getResultList();  
        return reviews;
	}

}
