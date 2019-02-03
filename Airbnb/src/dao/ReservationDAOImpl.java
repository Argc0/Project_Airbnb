package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Reservation;

public class ReservationDAOImpl implements ReservationDAO {

	@Override
	public List<Reservation> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Reservation.findAll");
		@SuppressWarnings("unchecked")
		List<Reservation> reservations = query.getResultList();  
        return reservations;
	}

	@Override
	public void create(Reservation reservation) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(reservation);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
				System.out.println(e.getMessage());
		}
	}
}
