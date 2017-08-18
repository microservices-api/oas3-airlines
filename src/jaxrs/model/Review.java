package jaxrs.model;

public class Review {

	private String id;
	private String userID;
	private String airlinesID;
	private int rating;
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
	public Review(String id, String userID, String storeID, int rating, String comment) {
		super();
		this.id = id;
		this.userID = userID;
		this.airlinesID = storeID;
		this.rating = rating;
		this.comment = comment;
	}

	/**
	 * Returns the id of this Review
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id for this Review
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the userID of this Review
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Sets the userID for this Review
	 * @param userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * Returns the Airlines of this Review
	 * @return airlinesID
	 */
	public String getAirlinesID() {
		return airlinesID;
	}

	/**
	 * Sets the airlines for this Review
	 * @param airlinesID
	 */
	public void setAirlinesID(String airlinesID) {
		this.airlinesID = airlinesID;
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
		return "Review [id=" + id + ", userID=" + userID + ", airlinesID=" + airlinesID + ", rating=" + rating
				+ ", comment=" + comment + "]";
	}
	
	
	
	
	
	
	
}
