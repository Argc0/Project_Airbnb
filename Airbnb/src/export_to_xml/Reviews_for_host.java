package export_to_xml;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reviews_for_host")
@XmlAccessorType (XmlAccessType.FIELD)

public class Reviews_for_host {
	
	@XmlElement(name = "hostname")
	private String hostname;
	
	@XmlElement(name = "review")
	private List<Review_to_xml> reviews;
	
    
	public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    public List<Review_to_xml> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review_to_xml> reviews) {
        this.reviews = reviews;
    }
}
