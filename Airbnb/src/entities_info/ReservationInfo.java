package entities_info;

import java.util.Date;

public class ReservationInfo {
	
	private String username;
    
    private Date datefrom;
    
    private Date dateto;
    
    private int idhouse;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(Date datefrom) {
        this.datefrom = datefrom;
    }
   
    public Date getDateto() {
        return dateto;
    }

    public void setDateto(Date dateto) {
        this.dateto = dateto;
    }
    
    public int getIdhouse() {
    	return idhouse;
    }
    
    public void setIdhouse(int idhouse) {
    	this.idhouse = idhouse;
    }

}
