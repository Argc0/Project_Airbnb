package export_to_xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "houses")
@XmlAccessorType (XmlAccessType.FIELD)

public class Houses {
    @XmlElement(name = "house")
    private List<House_to_xml> houses = null;
 
    public List<House_to_xml> getHouses() {
        return houses;
    }
 
    public void setHouses(List<House_to_xml> houses) {
        this.houses = houses;
    }
}