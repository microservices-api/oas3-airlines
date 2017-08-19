package jaxrs.model;

import io.swagger.oas.annotations.media.Schema;

public class Review {

//	@Schema(example = "0", required = true)
//	private String id;
	
	@Schema(required = true)
	private User user;
	
	@Schema(required = true)
	private Airline airlines;
	
	@Schema(example = "8", required = true)
	private int rating;
	
	@Schema(example = "Great service!")
	private String comment;
	
	/**
	 * Creates an instance of the Review object with the given fields.
	 * 
	 * @param id the unique id of this Review
	 * @param userID the user associated with this Review
	 * @param airlinesID the Airline associated with this Revoew
	 * @param rating the rating for this Review
	 * @param comment the comments for this Review
	 */
	public Review(User user, Airline airlines, int rating, String comment) {
		super();
		//this.id = id;
		this.user = user;
		this.airlines = airlines;
		this.rating = rating;
		this.comment = comment;
	}

	/**
	 * Returns the id of this Review
	 * @return id
	 */
//	public String getId() {
//		return id;
//	}

	/**
	 * Sets the id for this Review
	 * @param id
	 */
//	public void setId(String id) {
//		this.id = id;
//	}

	/**
	 * Returns the user of this Review
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Sets the user for this Review
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the Airlines of this Review
	 * @return airlines
	 */
	public Airline getAirlines() {
		return airlines;
	}

	/**
	 * Sets the airlines for this Review
	 * @param airlines
	 */
	public void setAirlinesID(Airline airlines) {
		this.airlines = airlines;
	}

	/**
	 * Returns the rating of this Review
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets the rating for this Review
	 * @param rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Returns the comments of this Review
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comments for this Review
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Review [userID=" + user + ", airlinesID=" + airlines + ", rating=" + rating
				+ ", comment=" + comment + "]";
	}
	
	
	
	
	
	
	
}
