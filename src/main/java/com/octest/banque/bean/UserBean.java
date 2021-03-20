package com.octest.banque.bean;
import java.sql.Timestamp;

/**
 * User JavaBean encapsulates TimeTable attributes
 Les classes Bean sont utilisées pour définir et obtenir la valeur.
 En d'autres termes,ce sont des classes getter et setter pour les attributs.
 * 
 */
import java.util.Date;
public class UserBean extends BaseBean {

	/**
	 * Login of User
	 */
	private String login;
	/**
	 * Password of User
	 */
	
    private String firstName;
    private String LastName;
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	private String password;
	/**
	 * Confirm Password of User
	 */
	private String confirmPassword;
	/**
	 * Role Id of User
	 */
	private long roleId;
	/**
	 * Customer of User
	 */
	
	private CustomerBean customer;
	
		
 

	

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	/**
	 * @return Login id Of User
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param Login
	 *            Id To set User Login ID
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Password Of User
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param Password
	 *            To set User Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Confirm Password Of User
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param Confirm
	 *            PAssword To set User Confirm Password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	

	/**
	 * @return ROle Id Of User
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * @param Role
	 *            Id To set User ROle Id
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return unSuccessfulLogin Of User
	 */
	
	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return login;
	}
}
