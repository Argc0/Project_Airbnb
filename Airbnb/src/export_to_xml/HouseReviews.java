package export_to_xml;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reviews")
@XmlAccessorType (XmlAccessType.FIELD)

public class HouseReviews {
    @XmlElement(name = "house_review")
    private List<Reviews_for_house> reviews = null;
 
    public List<Reviews_for_house> getReviews() {
        return reviews;
    }
 
    public void setReviews(List<Reviews_for_house> reviews) {
        this.reviews = reviews;
    }
}
