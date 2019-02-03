package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Message;

public class MessageDAOImpl implements MessageDAO {

	@Override
	public List<Message> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Message.findAll");
		@SuppressWarnings("unchecked")
		List<Message> messages = query.getResultList();  
        return messages;
	}
	
	@Override
	public List<Message> user_messages(int id){
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("Select m from Message m where m.id.host_id = :id or m.id.tenant_id = :id");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Message> messages = query.getResultList();  
        return messages;
	}
	
	@Override
	public void create(Message message) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(message);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
				System.out.println(e.getMessage());
		}
	}
	
	@Override
	public boolean del_message(int idmessage){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query query = em.createQuery("DELETE FROM Message m WHERE m.id.idMessage = :id");
		int deletedCount = query.setParameter("id", idmessage).executeUpdate();
		tx.commit();
		if(deletedCount==1)
			return true;
		else
			return false;
	}
}
