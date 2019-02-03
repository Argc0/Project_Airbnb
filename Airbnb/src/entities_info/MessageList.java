package entities_info;

import java.util.List;

public class MessageList {

	private String name;
	
	private List<MessageInfo> listMessage;
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public List<MessageInfo> getListMessage(){
		return this.listMessage;
	}
	
	public void setListMessage(List<MessageInfo> listMessage){
		this.listMessage=listMessage;
	}
}
