package dao;

import java.util.List;

import model.Reservation;

public interface ReservationDAO {
	
	public List<Reservation> list();
	
	public void create(Reservation reservation);
	
}
