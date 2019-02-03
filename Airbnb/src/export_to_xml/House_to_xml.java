package export_to_xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement
public class House_to_xml { 

	private int id;
	
	private String owner;
	
	private String housename;
    
	private int squaremeters;
	
	private String country;
	
    private String city;
    
    private String address;
    
    private Date date_from;
    
    private Date date_to;
    
    private float price;
    
    private boolean isprivate;
  
    public int getId() {
    	return id;
    }
    
    @XmlAttribute
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getOwner() {
        return owner;
    }
    
    @XmlElement
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getHousename() {
        return housename;
    }
    
    @XmlElement
    public void setHousename(String housename) {
        this.housename = housename;
    }

    public int getSquaremeters() {
        return squaremeters;
    }
    @XmlElement
    public void setSquaremeters(int squaremeters) {
        this.squaremeters = squaremeters;
    }
    
    public String getCountry() {
        return country;
    }
    
    @XmlElement
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    @XmlElement
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }
    
    public Date getDate_from() {
        return date_from;
    }

    @XmlElement
    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }
    
    public Date getDate_to() {
        return date_to;
    }

    @XmlElement
    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }
    
    public float getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(float price) {
        this.price = price;
    }
    
    public boolean getIsprivate() {
        return isprivate;
    }

    @XmlElement
    public void setIsprivate(boolean isprivate) {
        this.isprivate = isprivate;
    }
}
