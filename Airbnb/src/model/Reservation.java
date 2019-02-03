package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Reservation database table.
 * 
 */
@Entity
@Table(name="Reservation")
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReservationPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="Check_in")
	private Date check_in;

	@Temporal(TemporalType.DATE)
	@Column(name="Check_out")
	private Date check_out;


	//bi-directional many-to-one association to House
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Host_id", referencedColumnName="Host_id"),
		@JoinColumn(name="House_id", referencedColumnName="idHouse")
		})
	private House house;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Tenant_id")
	private User user;

	public Reservation() {
	}

	public ReservationPK getId() {
		return this.id;
	}

	public void setId(ReservationPK id) {
		this.id = id;
	}

	public Date getCheck_in() {
		return this.check_in;
	}

	public void setCheck_in(Date check_in) {
		this.check_in = check_in;
	}

	public Date getCheck_out() {
		return this.check_out;
	}

	public void setCheck_out(Date check_out) {
		this.check_out = check_out;
	}

	public House getHouse() {
		return this.house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}