package export_to_xml;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reviews")
@XmlAccessorType (XmlAccessType.FIELD)

public class HostReviews {
    @XmlElement(name = "host_review")
    private List<Reviews_for_host> reviews = null;
 
    public List<Reviews_for_host> getReviews() {
        return reviews;
    }
 
    public void setReviews(List<Reviews_for_host> reviews) {
        this.reviews = reviews;
    }
}