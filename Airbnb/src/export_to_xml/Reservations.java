package export_to_xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reservations")
@XmlAccessorType (XmlAccessType.FIELD)

public class Reservations {
    @XmlElement(name = "reservation")
    private List<Tenant_Reservation> reservations = null;
 
    public List<Tenant_Reservation> getReservations() {
        return reservations;
    }
 
    public void setReservations(List<Tenant_Reservation> reservations) {
        this.reservations = reservations;
    }
}
