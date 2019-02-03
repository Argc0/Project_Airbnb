package export_to_xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reviews_for_house")
@XmlAccessorType (XmlAccessType.FIELD)

public class Reviews_for_house {

	@XmlAttribute
	private int idhouse;
	
	@XmlElement(name = "housename")
	private String housename;
	
	@XmlElement(name = "review")
	private List<Review_to_xml> reviews;
	
	
	public int getIdhouse() {
    	return idhouse;
    }
	
    public void setIdhouse(int idhouse) {
    	this.idhouse = idhouse;
    }
    
	public String getHousename() {
        return housename;
    }
    public void setHousename(String housename) {
        this.housename = housename;
    }
    
    public List<Review_to_xml> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review_to_xml> reviews) {
        this.reviews = reviews;
    }
}
