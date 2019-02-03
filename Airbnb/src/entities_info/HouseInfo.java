package entities_info;

import java.util.Date;
import java.util.List;

public class HouseInfo { 
	
	private String owner;
	
	private String photo_owner;
	
	private int idHouse;
	
	private boolean ac;

	private String address;

	private String city;

	private String country;

	private boolean elevator;

	private int floor;

	private boolean heating;

	private String information;

	private boolean isPrivate;

	private boolean isRoom;

	private boolean kitchen;

	private boolean livingRoom;

	private String housename;

	private int numberBaths;

	private int numberBedrooms;

	private int numberBeds;

	private boolean parking;

	private String rentingRules;

	private int square_meters;

	private String summary;

	private String transitInfo;

	private String photo;
	
	private String filename;
	
	private int price;
	
	private boolean tv;

	private boolean wiFi;
	
	private double coord_x;
	
	private double coord_y;
	
	private int maxnumofp;
	
	private int priceperp;
	
	private Date date_from;
	
	private Date date_to;
	
	private int numberReviews;
	
	private float rate;

	private List<ReviewInfo> listReview;
	
	private List<ReviewInfo> hostReviews;
	
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPhoto_owner() {
		return this.photo_owner;
	}

	public void setPhoto_owner(String photo_owner) {
		this.photo_owner = photo_owner;
	}
	
	public int getIdHouse() {
		return this.idHouse;
	}

	public void setIdHouse(int idHouse) {
		this.idHouse = idHouse;
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
	
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public String getHousename() {
		return this.housename;
	}

	public void setHousename(String housename) {
		this.housename = housename;
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
	
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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
	
	public Date getDate_from() {
		return this.date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}
	
	public Date getDate_to() {
		return this.date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}
	
	public int getNumberReviews() {
		return this.numberReviews;
	}

	public void setNumberReviews(int numberReviews) {
		this.numberReviews = numberReviews;
	}
	
	public float getRate() {
		return this.rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public List<ReviewInfo> getListReview() {
		return this.listReview;
	}

	public void setListReview(List<ReviewInfo> listReview) {
		this.listReview = listReview;
	}
	
	public List<ReviewInfo> getHostReviews() {
		return this.hostReviews;
	}

	public void setHostReviews(List<ReviewInfo> hostReviews) {
		this.hostReviews = hostReviews;
	}
}
