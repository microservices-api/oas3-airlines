package jaxrs.model;

import io.swagger.oas.annotations.media.Schema;

public class User {
	
	@Schema(required = true, example = "3456")
	private int _id;
	
	@Schema(required = true, example = "user1")
	private String userName;
	
	@Schema(required = true, example = "bobSm37")
	private String password;
	
	@Schema(required = true, example = "Bob")
	private String firstName;
	
	@Schema(required = true, example = "Smith")
	private String lastName;
	
	@Schema(required = true, example = "M")
	private String sex;
	
	@Schema(required = true, example = "37")
	private int age;
	
	@Schema(required = true, example = "bob@mail.ca")
	private String email;
	
	@Schema(required = true, example = "123-456-7890")
	private String phone;
	
	@Schema(required = true, example = "1")
	private int status;
	
	/**
	 * Creates a User instance with the parameters specified.
	 * 
	 * @param _id
	 * @param firstName
	 * @param lastName
	 * @param sex
	 * @param age
	 * @param email
	 */
	
	public User(){

	}
	
	/**
	 * Returns the _id property of a User instance
	 * 
	 * @return int _id
	 */
	
	public int getId(){
		return _id;
	}
	
	/**
	 * Sets the _id property of a User instance to the parameter.
	 * 
	 * @param _id
	 */
	
	public void setId(int _id){
		this._id = _id;
	}
	
	/**
	 * Returns the userName property of a User instance.
	 * 
	 * @return String userName
	 */
	
	public String getUsername() {
	    return userName;
	}
	
	/**
	 * Sets the userName property of a User instance to the parameter.
	 * 
	 * @param userName
	 */

	public void setUsername(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Returns the password property of a User instance.
	 * 
	 * @return String password
	 */
	
	public String getPassword() {
	    return password;
	}
	
	/**
	 * Sets the password property of a User instance to the parameter.
	 * 
	 * @param password
	 */

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns firstName property of a User instance.
	 * 
	 * @return String firstName
	 */
	
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * Sets the firstName property of a User instance to the parameter.
	 * 
	 * @param firstName
	 */
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	/**
	 * Returns the lastName property of a User instance.
	 * 
	 * @return String lastName
	 */
	
	public String getLastName(){
		return lastName;
	}
	
	/**
	 * Sets the lastName property of a User instance to the parameter.
	 * 
	 * @param lastName
	 */
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	/**
	 * Returns the sex property of a User instance.
	 * 
	 * @return String sex
	 */
	
	public String getSex(){
		return sex;
	}
	
	/**
	 * Sets the sex property of a User instance to the parameter.
	 * 
	 * @param sex
	 */
	
	public void setSex(String sex){
		this.sex = sex;
	}
	
	/**
	 * Returns the age property of a User instance.
	 * 
	 * @return int age
	 */
	
	public int getAge(){
		return age;
	}
	
	/**
	 * Sets the age property of a User instance to the parameter.
	 * 
	 * @param age
	 */
	
	public void setAge(int age){
		this.age = age;
	}
	
	/**
	 * Returns the email property of a User instance.
	 * 
	 * @return String email
	 */
	
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email property of a User instance to the parameter.
	 * 
	 * @param email
	 */

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Returns the phone property of a User instance.
	 * 
	 * @return String phone
	 */
	
	public String getPhone() {
	    return phone;
	}
	
	/**
	 * Sets the phone property of a User instance to the parameter.
	 * 
	 * @param phone
	 */

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Schema(
			  name = "status",
			  title = "User Status",
			  _enum = {"1-registered", "2-active", "3-closed"}
			  )
	    

	  
	  public int getUserStatus() {
	    return status;
	  }

	  public void setUserStatus(int status) {
	    this.status = status;
	  }

}
