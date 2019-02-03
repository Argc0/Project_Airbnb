package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Review database table.
 * 
 */
@Entity
@Table(name="Review")
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReviewPK id;

	@Column(name="Review_for_host")
	private boolean review_for_host;

	@Column(name="Reviewtext")
	private String reviewtext;

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

	public Review() {
	}

	public ReviewPK getId() {
		return this.id;
	}

	public void setId(ReviewPK id) {
		this.id = id;
	}

	public boolean getReview_for_host() {
		return this.review_for_host;
	}

	public void setReview_for_host(boolean review_for_host) {
		this.review_for_host = review_for_host;
	}

	public String getReviewtext() {
		return this.reviewtext;
	}

	public void setReviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
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