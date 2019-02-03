package dao;

import java.util.List;
import model.Message;

public interface MessageDAO {

    public List<Message> list();
    
    public List<Message> user_messages(int id);
    
    public void create(Message message);
    
    public boolean del_message(int idmessage); 
}
