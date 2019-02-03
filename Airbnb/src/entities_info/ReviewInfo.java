package entities_info;

import java.util.Date;

public class ReviewInfo {
	
	private String text;
	
	private Boolean forhost;
	
	private int idhouse;
	
	private String username;
	
	private Date date;
	
	private float rate;
	
	public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    public Boolean getForhost() {
    	return forhost;
    }
    
    public void setForhost(Boolean forhost) {
    	this.forhost = forhost;
    }
    
    public int getIdhouse() {
    	return idhouse;
    }
    
    public void setIdhouse(int idhouse) {
    	this.idhouse = idhouse;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public float getRate() {
    	return rate;
    }
    
    public void setRate(float rate) {
    	this.rate = rate;
    }
   
}
