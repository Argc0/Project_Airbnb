package export_to_xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement
public class Reservation_to_xml {

	private int idreservation;
    
	private String housename;
	
    private Date datefrom;
    
    private Date dateto;
    

    public int getIdreservation() {
    	return idreservation;
    }
    @XmlAttribute
    public void setIdreservation(int idreservation) {
    	this.idreservation = idreservation;
    }
    
    public String getHousename() {
        return housename;
    }
	
	@XmlElement
    public void setHousename(String housename) {
        this.housename = housename;
    }

    public Date getDatefrom() {
        return datefrom;
    }
    
    @XmlElement
    public void setDatefrom(Date datefrom) {
        this.datefrom = datefrom;
    }
   
    public Date getDateto() {
        return dateto;
    }
    
    @XmlElement
    public void setDateto(Date dateto) {
        this.dateto = dateto;
    }
}
