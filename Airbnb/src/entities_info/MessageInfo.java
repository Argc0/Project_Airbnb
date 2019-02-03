package entities_info;

public class MessageInfo {
	
	private int idMessage;
	
	private String text;
	
	private int host_id;
	
	private int tenant_id;
	
	private String host_name;
	
	private String tenant_name;
	
	public int getIdMessage(){
		return this.idMessage;
	}
	
	public void setIdMessage(int idMessage){
		this.idMessage = idMessage;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public int getHost_id(){
		return this.host_id;
	}
	
	public void setHost_id(int host_id){
		this.host_id = host_id;
	}
	
	public int getTenant_id(){
		return this.tenant_id;
	}
	
	public void setTenant_id(int tenant_id){
		this.tenant_id = tenant_id;
	}
	
	public String getHost_name(){
		return this.host_name;
	}
	
	public void setHost_name(String host_name){
		this.host_name = host_name;
	}
	
	public String getTenant_name(){
		return this.tenant_name;
	}
	
	public void setTenant_name(String tenant_name){
		this.tenant_name = tenant_name;
	}
}
