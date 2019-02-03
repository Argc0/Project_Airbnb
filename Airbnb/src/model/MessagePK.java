package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Message database table.
 * 
 */
@Embeddable
public class MessagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idMessage;

	@Column(name="Host_id", insertable=false, updatable=false)
	private int host_id;

	@Column(name="Tenant_id", insertable=false, updatable=false)
	private int tenant_id;

	public MessagePK() {
	}
	public int getIdMessage() {
		return this.idMessage;
	}
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	public int getHost_id() {
		return this.host_id;
	}
	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}
	public int getTenant_id() {
		return this.tenant_id;
	}
	public void setTenant_id(int tenant_id) {
		this.tenant_id = tenant_id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MessagePK)) {
			return false;
		}
		MessagePK castOther = (MessagePK)other;
		return 
			(this.idMessage == castOther.idMessage)
			&& (this.host_id == castOther.host_id)
			&& (this.tenant_id == castOther.tenant_id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idMessage;
		hash = hash * prime + this.host_id;
		hash = hash * prime + this.tenant_id;
		
		return hash;
	}
}