package com.test.ecommerce.output.entities;

// default package
// Generated Mar 23, 2016 4:48:05 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "CUSTOMER", catalog = "Ankita")
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer customerId;
	private String firstName;
	private String lastName;
	private String password;
	private String address;
	private String email;
	private Integer phNumber;

	public Customer() {
	}

	public Customer(String firstName, String password, String email) {
		this.firstName = firstName;
		this.password = password;
		this.email = email;
	}

	public Customer(String firstName, String lastName, String password, String address, String email,
			Integer phNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phNumber = phNumber;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CUSTOMER_ID", unique = true, nullable = false)
	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", length = 20)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "PASSWORD", nullable = false, length = 4)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ADDRESS", length = 20)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "EMAIL", nullable = false, length = 55)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PH_NUMBER")
	public Integer getPhNumber() {
		return this.phNumber;
	}

	public void setPhNumber(Integer phNumber) {
		this.phNumber = phNumber;
	}

}