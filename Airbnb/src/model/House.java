package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the House database table.
 * 
 */
@Entity
@Table (name="House")
@NamedQuery(name="House.findAll", query="SELECT h FROM House h")
public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HousePK id;

	@Column(name="AC")
	private boolean ac;

	@Column(name="Address")
	private String address;

	@Column(name="City")
	private String city;

	@Column(name="Country")
	private String country;

	@Column(name="Elevator")
	private boolean elevator;

	@Column(name="Floor")
	private int floor;

	@Column(name="Heating")
	private boolean heating;

	@Column(name="Information")
	private String information;

	@Column(name="IsPrivate")
	private boolean isPrivate;

	@Column(name="IsRoom")
	private boolean isRoom;

	@Column(name="Kitchen")
	private boolean kitchen;

	@Column(name="LivingRoom")
	private boolean livingRoom;

	@Column(name="Name")
	private String name;

	@Column(name="NumberBaths")
	private int numberBaths;

	@Column(name="NumberBedrooms")
	private int numberBedrooms;

	@Column(name="NumberBeds")
	private int numberBeds;

	@Column(name="NumberReviews")
	private int numberReviews;

	@Column(name="Parking")
	private boolean parking;

	@Column(name="Photo")
	private String photo;

	@Column(name="Rate")
	private float rate;

	@Column(name="RentingRules")
	private String rentingRules;

	@Column(name="Square_meters")
	private int square_meters;
	
	@Column(name="Summary")
	private String summary;

	@Column(name="TransitInfo")
	private String transitInfo;

	@Column(name="TV")
	private boolean tv;

	@Column(name="WiFi")
	private boolean wiFi;
	
	@Column(name="coord_x")
	private double coord_x;
	
	@Column(name="coord_y")
	private double coord_y;
	
	@Column(name="MaxnumofP")
	private int maxnumofp;
	
	@Column(name="PriceperP")
	private int priceperp;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EndAvailability")
	private Date endAvailability;

	@Column(name="Price")
	private int price;

	@Temporal(TemporalType.DATE)
	@Column(name="StartAvailability")
	private Date startAvailability;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Host_id")
	private User user;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="house")
	private List<Reservation> reservations;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="house")
	private List<Review> reviews;

	public House() {
	}

	public HousePK getId() {
		return this.id;
	}

	public void setId(HousePK id) {
		this.id = id;
	}

	public boolean getAc() {
		return this.ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean getElevator() {
		return this.elevator;
	}

	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}

	public int getFloor() {
		return this.floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public boolean getHeating() {
		return this.heating;
	}

	public void setHeating(boolean heating) {
		this.heating = heating;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public boolean getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public boolean getIsRoom() {
		return this.isRoom;
	}

	public void setIsRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}

	public boolean getKitchen() {
		return this.kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean getLivingRoom() {
		return this.livingRoom;
	}

	public void setLivingRoom(boolean livingRoom) {
		this.livingRoom = livingRoom;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberBaths() {
		return this.numberBaths;
	}

	public void setNumberBaths(int numberBaths) {
		this.numberBaths = numberBaths;
	}

	public int getNumberBedrooms() {
		return this.numberBedrooms;
	}

	public void setNumberBedrooms(int numberBedrooms) {
		this.numberBedrooms = numberBedrooms;
	}

	public int getNumberBeds() {
		return this.numberBeds;
	}

	public void setNumberBeds(int numberBeds) {
		this.numberBeds = numberBeds;
	}

	public int getNumberReviews() {
		return this.numberReviews;
	}

	public void setNumberReviews(int numberReviews) {
		this.numberReviews = numberReviews;
	}

	public boolean getParking() {
		return this.parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public float getRate() {
		return this.rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getRentingRules() {
		return this.rentingRules;
	}

	public void setRentingRules(String rentingRules) {
		this.rentingRules = rentingRules;
	}

	public int getSquare_meters() {
		return this.square_meters;
	}

	public void setSquare_meters(int square_meters) {
		this.square_meters = square_meters;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTransitInfo() {
		return this.transitInfo;
	}

	public void setTransitInfo(String transitInfo) {
		this.transitInfo = transitInfo;
	}

	public boolean getTv() {
		return this.tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean getWiFi() {
		return this.wiFi;
	}

	public void setWiFi(boolean wiFi) {
		this.wiFi = wiFi;
	}
	
	public double getCoord_x() {
		return this.coord_x;
	}

	public void setCoord_x(double coord_x) {
		this.coord_x = coord_x;
	}
	
	public double getCoord_y() {
		return this.coord_y;
	}

	public void setCoord_y(double coord_y) {
		this.coord_y = coord_y;
	}

	public int getMaxnumofp() {
		return this.maxnumofp;
	}

	public void setMaxnumofP(int maxnumofp) {
		this.maxnumofp = maxnumofp;
	}
	
	public int getPriceperp() {
		return this.priceperp;
	}

	public void setPriceperp(int priceperp) {
		this.priceperp = priceperp;
	}
	
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public Date getEndAvailability() {
		return this.endAvailability;
	}

	public void setEndAvailability(Date endAvailability) {
		this.endAvailability = endAvailability;
	}
	
	public Date getStartAvailability() {
		return this.startAvailability;
	}

	public void setStartAvailability(Date startAvailability) {
		this.startAvailability = startAvailability;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setHouse(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setHouse(null);

		return reservation;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setHouse(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setHouse(null);

		return review;
	}

}