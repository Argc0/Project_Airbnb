package dao;

import java.util.List;
import model.User;

public interface UserDAO 
{
	public User find_login(String username, String password);
	
	public boolean username_exists (String username);
	
	public int get_user_id (String username);
	
	public boolean insertPhoto(int id, String filename);
	
	public User get_user (String username);
	
	public void updateUser(User user);
	
	public boolean changePassword (String username, String password);
	
	public boolean aprrove_user(int id);

    public List<User> list();

    public void create(User user);
    
    public int return_count();

}