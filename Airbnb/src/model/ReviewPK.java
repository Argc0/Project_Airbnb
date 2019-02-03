package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Review database table.
 * 
 */
@Embeddable
public class ReviewPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idReview;

	@Column(name="Tenant_id", insertable=false, updatable=false)
	private int tenant_id;

	@Column(name="House_id", insertable=false, updatable=false)
	private int house_id;

	@Column(name="Host_id", insertable=false, updatable=false)
	private int host_id;

	public ReviewPK() {
	}
	public int getIdReview() {
		return this.idReview;
	}
	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}
	public int getTenant_id() {
		return this.tenant_id;
	}
	public void setTenant_id(int tenant_id) {
		this.tenant_id = tenant_id;
	}
	public int getHouse_id() {
		return this.house_id;
	}
	public void setHouse_id(int house_id) {
		this.house_id = house_id;
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
		if (!(other instanceof ReviewPK)) {
			return false;
		}
		ReviewPK castOther = (ReviewPK)other;
		return 
			(this.idReview == castOther.idReview)
			&& (this.tenant_id == castOther.tenant_id)
			&& (this.house_id == castOther.house_id)
			&& (this.host_id == castOther.host_id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idReview;
		hash = hash * prime + this.tenant_id;
		hash = hash * prime + this.house_id;
		hash = hash * prime + this.host_id;
		
		return hash;
	}
}