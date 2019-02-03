package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the House database table.
 * 
 */
@Embeddable
public class HousePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idHouse;

	@Column(name="Host_id", insertable=false, updatable=false)
	private int host_id;

	public HousePK() {
	}
	public int getIdHouse() {
		return this.idHouse;
	}
	public void setIdHouse(int idHouse) {
		this.idHouse = idHouse;
	}
	public int getHost_id() {
		return this.host_id;
	}
	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HousePK)) {
			return false;
		}
		HousePK castOther = (HousePK)other;
		return 
			(this.idHouse == castOther.idHouse)
			&& (this.host_id == castOther.host_id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idHouse;
		hash = hash * prime + this.host_id;
		
		return hash;
	}
}