package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities_info.SearchInfo;
import jpautils.EntityManagerHelper;
import model.House;

public class HouseDAOImpl implements HouseDAO 
{
	@Override
	public House find_by_id(int id){
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.clear();
		Query q = em.createQuery("Select h from House h where h.id.idHouse = :id");
		q.setParameter("id", id);
		House h = (House) q.getSingleResult();
		return h;
	}
	
	@Override
	public void updateHouse(House house){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(house);
			em.flush();
			tx.commit();
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
				System.out.println(e.getMessage());
		}
	}
	
	@Override
	public List<House> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("House.findAll");
		@SuppressWarnings("unchecked")
		List<House> houses = query.getResultList();  
        return houses;
	}

	@Override
	public List<House> search(SearchInfo searchinfo){
		boolean flag=false;
		EntityManager em = EntityManagerHelper.getEntityManager();
		String querystring= "Select h from House h where " ;
		if(searchinfo.getCity()!=null && searchinfo.getCity() != "" ){
			 querystring= querystring +"h.city= :city ";
			 flag=true;
		}
		if(searchinfo.getCountry()!=null && searchinfo.getCountry() != "" ){
			if(flag)  
				querystring= querystring + "and h.country= :country ";
			else
				querystring= querystring + "h.country= :country ";
			flag=true;
		}
		if(searchinfo.getNum_guests()!= 0){
			if(flag)
				querystring= querystring +"and h.maxnumofp >= :numofp ";
			else
				 querystring= querystring +"h.maxnumofp >= :numofp ";
			flag=true;
		}
		if(searchinfo.getAddress()!=null && searchinfo.getAddress() != "" ){
			if(flag)
				querystring= querystring +"and h.address = :address ";
			else
				querystring= querystring +"h.address = :address ";
			flag=true;
		}
		if((searchinfo.getDate_from()!=null) && (searchinfo.getDate_to()!=null)){
			if(flag){
				querystring= querystring + "and :datefrom between h.startAvailability and h.endAvailability ";
				querystring= querystring + " and :dateto between h.startAvailability and h.endAvailability ";
			}else{
				querystring= querystring + ":datefrom between h.startAvailability and h.endAvailability ";
				querystring= querystring + "and :dateto between h.startAvailability and h.endAvailability ";
			}
		}
		if(searchinfo.getPrice()!=0){
			if(flag)
				querystring = querystring + "and h.price <= :price ";
			else
				querystring = querystring + "h.price <= :price ";
		}
		if((searchinfo.getIsPrivate()!= null) && (searchinfo.getIsPrivate()!= false)){
			if(flag)
				querystring = querystring + "and h.isPrivate = :private ";
			else
				querystring = querystring + "h.isPrivate = :private ";
		}
		if((searchinfo.getIsRoom()!= null) && (searchinfo.getIsRoom()!= false)){
			if(flag)
				querystring = querystring + "and h.isRoom = :room ";
			else
				querystring = querystring + "h.isRoom = :room ";
		}
		if((searchinfo.getWiFi()!= null) && (searchinfo.getWiFi()!= false)){
			if(flag)
				querystring = querystring + "and h.wiFi = :wifi ";
			else
				querystring = querystring + "h.wiFi = :wifi ";
		}
		if((searchinfo.getAc()!= null) && (searchinfo.getAc()!= false)){
			if(flag)
				querystring = querystring + "and h.ac = :AC ";
			else
				querystring = querystring + "h.ac = :AC ";
		}
		if((searchinfo.getHeating()!= null) && (searchinfo.getHeating()!= false)){
			if(flag)
				querystring = querystring + "and h.heating = :heating ";
			else
				querystring = querystring + "h.heating = :heating ";
		}
		if((searchinfo.getKitchen()!= null) && (searchinfo.getKitchen()!= false)){
			if(flag)
				querystring = querystring + "and h.kitchen = :kitchen ";
			else
				querystring = querystring + "h.kitchen = :kitchen ";
		}
		if((searchinfo.getTv()!= null) && (searchinfo.getTv()!= false)){
			if(flag)
				querystring = querystring + "and h.tv = :TV ";
			else
				querystring = querystring + "h.tv = :TV ";
		}
		if((searchinfo.getParking()!= null) && (searchinfo.getParking()!= false)){
			if(flag)
				querystring = querystring + "and h.parking = :parking ";
			else
				querystring = querystring + "h.parking = :parking ";
		}
		if((searchinfo.getElevator()!= null) && (searchinfo.getElevator()!= false)){
			if(flag)
				querystring = querystring + "and h.elevator = :elevator ";
			else
				querystring = querystring + "h.elevator = :elevator ";
		}
		querystring= querystring +"order by h.price";
		Query query = em.createQuery(querystring);
		if(searchinfo.getCity()!=null && searchinfo.getCity() != "" )
			query.setParameter("city", searchinfo.getCity());
		if(searchinfo.getCountry()!=null && searchinfo.getCountry() != "" )
			query.setParameter("country", searchinfo.getCountry());
		if(searchinfo.getNum_guests()!= 0)
			 query.setParameter("numofp", searchinfo.getNum_guests());
		if(searchinfo.getAddress()!=null && searchinfo.getAddress() != "" )
			query.setParameter("address", searchinfo.getAddress());
		if((searchinfo.getDate_from()!=null) && (searchinfo.getDate_to()!=null)){
			query.setParameter("datefrom", searchinfo.getDate_from());
			query.setParameter("dateto", searchinfo.getDate_to());
		}
		if(searchinfo.getPrice()!=0)
			query.setParameter("price", searchinfo.getPrice());
		if((searchinfo.getIsPrivate()!= null) && (searchinfo.getIsPrivate()!= false))
			query.setParameter("private", searchinfo.getIsPrivate());
		if((searchinfo.getIsRoom()!= null) && (searchinfo.getIsRoom()!= false))
			query.setParameter("room", searchinfo.getIsRoom());
		if((searchinfo.getWiFi()!= null) && (searchinfo.getWiFi()!= false))
			query.setParameter("wifi", searchinfo.getWiFi());
		if((searchinfo.getAc()!= null) && (searchinfo.getAc()!= false))
			query.setParameter("AC", searchinfo.getAc());
		if((searchinfo.getHeating()!= null) && (searchinfo.getHeating()!= false))
			query.setParameter("heating", searchinfo.getHeating());
		if((searchinfo.getKitchen()!= null) && (searchinfo.getKitchen()!= false))
			query.setParameter("kitchen", searchinfo.getKitchen());
		if((searchinfo.getTv()!= null) && (searchinfo.getTv()!= false))
			query.setParameter("TV", searchinfo.getTv());
		if((searchinfo.getParking()!= null) && (searchinfo.getParking()!= false))
			query.setParameter("parking", searchinfo.getParking());
		if((searchinfo.getElevator()!= null) && (searchinfo.getElevator()!= false))
			query.setParameter("elevator", searchinfo.getElevator());
		@SuppressWarnings("unchecked")
		List<House> houses = query.getResultList();  
        return houses;
	}
	
	@Override
	public void create(House house) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(house);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx.isActive())
				tx.rollback();
				System.out.println(e.getMessage());
		}
	}
	
	@Override
	public boolean insertPhoto(int id, String filename){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("Update House h set h.photo = :filename where h.id.idHouse = :id");
		
		int updateCount = q.setParameter("filename", filename).setParameter("id", id).executeUpdate();
		
		tx.commit();
		if(updateCount==1)
			return true;
		else
			return false;
	}
	
	@Override
	public int return_count(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery("SELECT h.id.idHouse FROM House h ORDER BY h.id.idHouse DESC");
		query.setMaxResults(1);
        int  count = (int) query.getSingleResult();
        return count;
	}
	
	@Override
	public void insert_rate(int id, float rate){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createQuery("Update House h set h.rate = :rate where h.id.idHouse = :id");
		
		q.setParameter("rate", rate).setParameter("id", id).executeUpdate();
		
		tx.commit();
	}
}
