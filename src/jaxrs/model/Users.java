package jaxrs.model;

import io.swagger.oas.annotations.media.Schema;

public class Users {
	
	@Schema(required = true, example = "3456")
	private int _id;
	
	@Schema(required = true, example = "Bob")
	private String firstName;
	
	@Schema(required = true, example = "Smith")
	private String lastName;
	
	@Schema(required = true, example = "M")
	private String sex;
	
	@Schema(required = true, example = "37")
	private int age;
	
	private Users() {	
	}
	
	/**
	 * Creates a Users instance with the parameters specified.
	 * 
	 * @param _id
	 * @param firstName
	 * @param lastName
	 * @param sex
	 * @param age
	 */
	
	public Users(int _id, String firstName, String lastName, String sex, int age){
		this._id = _id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.age = age;
	}
	
	/**
	 * Returns the _id property of a Users instance
	 * 
	 * @return int _id
	 */
	
	public int getId(){
		return _id;
	}
	
	/**
	 * Sets _id property of a Users instance to the parameter.
	 * 
	 * @param _id
	 */
	
	public void setId(int _id){
		this._id = _id;
	}
	
	/**
	 * Returns firstName property of a Users instance.
	 * 
	 * @return String firstName
	 */
	
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * Sets firstName property of a Users instance to the parameter.
	 * 
	 * @param firstName
	 */
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	/**
	 * Returns the lastName property of a Users instance.
	 * 
	 * @return String lastName
	 */
	
	public String getLastName(){
		return lastName;
	}
	
	/**
	 * Sets lastName property of a Users instance to the parameter.
	 * 
	 * @param lastName
	 */
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	/**
	 * Returns the sex property of a Users instance.
	 * 
	 * @return String sex
	 */
	
	public String getSex(){
		return sex;
	}
	
	/**
	 * Sets sex property of a Users instance to the parameter.
	 * 
	 * @param sex
	 */
	
	public void setSex(String sex){
		this.sex = sex;
	}
	
	/**
	 * Returns the age property of a Users instance.
	 * 
	 * @return int age
	 */
	
	public int getAge(){
		return age;
	}
	
	/**
	 * Sets age property of a Users instance to the parameter.
	 * 
	 * @param age
	 */
	
	public void setAge(int age){
		this.age = age;
	}
}
