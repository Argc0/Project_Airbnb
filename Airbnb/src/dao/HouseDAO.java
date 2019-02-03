package dao;

import java.util.List;

import model.House;
import entities_info.SearchInfo;

public interface HouseDAO {
	public void updateHouse(House house);
	
	public House find_by_id(int id);

    public List<House> list();
    
    public List<House> search(SearchInfo searchinfo);

    public void create(House house);

    public boolean insertPhoto(int id, String filename);
    
    public int return_count();
    
    public void insert_rate(int id, float rate);
}
