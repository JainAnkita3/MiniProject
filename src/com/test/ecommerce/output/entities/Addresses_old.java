package com.test.ecommerce.output.entities;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated Mar 23, 2016 4:48:05 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* Addresses generated by hbm2java
*/
@Entity
@Table(name = "ADDRESSES", catalog = "Ankita")
public class
Addresses implements java.io.Serializable {

private static final long serialVersionUID = 1L;
private Integer addressId;
/*
* For building Foreign Key concept and Many to One relationship between
* Customer & Addresses.
*/
private Customer customer;
private String streetName;
private String city;
private String apt;
private String addressType;

public Addresses() {
}

/*
* Constructor modified for Many to One mapping, made Customer Object as
an
* input argument
*/
public Addresses(int addressId, Customer customer) {
this.addressId = addressId;
this.customer = customer;
}

public Addresses(Customer customer, String streetName, String city, String
apt, String addressType) {
this.customer = customer;
this.streetName = streetName;
this.city = city;
this.apt = apt;
this.addressType = addressType;
}

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "ADDRESS_ID", unique = true, nullable = false)
public Integer getAddressId() {
return this.addressId;
}

public void setAddressId(Integer addressId) {
this.addressId = addressId;
}

/*
* Annotation used and changes done to support Many to One mapping
*/
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "CUSTOMER_ID", nullable = false)
public Customer getCustomer() {
return this.customer;
}

public void setCustomer(Customer customer) {
this.customer = customer;
}

@Column(name = "STREET_NAME", length = 20)
public String getStreetName() {
return this.streetName;
}

public void setStreetName(String streetName) {
this.streetName = streetName;
}

@Column(name = "CITY", length = 20)
public String getCity() {
return this.city;
}

public void setCity(String city) {
this.city = city;
}

@Column(name = "APT", length = 8)
public String getApt() {
return this.apt;
}

public void setApt(String apt) {
this.apt = apt;
}

@Column(name = "ADDRESS_TYPE", length = 20)
public String getAddressType() {
return this.addressType;
}

public void setAddressType(String addressType) {
this.addressType = addressType;
}

}
