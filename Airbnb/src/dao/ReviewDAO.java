package dao;

import java.util.List;
import model.Review;

public interface ReviewDAO {
	
	public List<Review> list();
	
	public void create(Review review);
	
	public List<Review> reviews_for_houses();
	
	public List<Review> reviews_for_hosts();

}
