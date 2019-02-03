package export_to_xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "tenant_reservations")
@XmlAccessorType (XmlAccessType.FIELD)

public class Tenant_Reservation {

	@XmlElement(name = "tenant_name")
	private String tenantname;
	
	@XmlElement(name = "reservation_of_tenant")
	private List<Reservation_to_xml> reservations;
	
	public String getTenantname() {
        return tenantname;
    }
    public void setTenantname(String tenantname) {
        this.tenantname = tenantname;
    }
    
    public List<Reservation_to_xml> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation_to_xml> reservations) {
        this.reservations = reservations;
    }
}
