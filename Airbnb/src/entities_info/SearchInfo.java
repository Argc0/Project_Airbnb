package entities_info;

import java.util.Date;

public class SearchInfo {
	private String country;
	
	private String city;
    
    private String address;
    
    private Date date_from;
    
    private Date date_to;
    
    private int num_guests;
	
	private Boolean ac;

	private Boolean elevator;

	private Boolean heating;

	private Boolean isPrivate;

	private Boolean isRoom;

	private Boolean kitchen;

	private Boolean parking;
	
	private int price;
	
	private Boolean tv;

	private Boolean wiFi;
	
	private String name;


	public Boolean getAc() {
		return this.ac;
	}

	public void setAc(Boolean ac) {
		this.ac = ac;
	}

	public Boolean getElevator() {
		return this.elevator;
	}

	public void setElevator(Boolean elevator) {
		this.elevator = elevator;
	}
	
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Boolean getHeating() {
		return this.heating;
	}

	public void setHeating(Boolean heating) {
		this.heating = heating;
	}

	public Boolean getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Boolean getIsRoom() {
		return this.isRoom;
	}

	public void setIsRoom(Boolean isRoom) {
		this.isRoom = isRoom;
	}

	public Boolean getKitchen() {
		return this.kitchen;
	}

	public void setKitchen(Boolean kitchen) {
		this.kitchen = kitchen;
	}
	
	public Boolean getParking() {
		return this.parking;
	}

	public void setParking(Boolean parking) {
		this.parking = parking;
	}
	public Boolean getTv() {
		return this.tv;
	}

	public void setTv(Boolean tv) {
		this.tv = tv;
	}

	public Boolean getWiFi() {
		return this.wiFi;
	}

	public void setWiFi(Boolean wiFi) {
		this.wiFi = wiFi;
	}
	
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }
    
    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }
    
    public int getNum_guests() {
        return num_guests;
    }

    public void setNum_guests(int num_guests) {
        this.num_guests = num_guests;
    }
    
    public String getName() {
        return name;
    }

    public void setNamee(String name) {
        this.name = name;
    }
    
}
