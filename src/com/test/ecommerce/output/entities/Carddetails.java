package com.test.ecommerce.output.entities;

// default package
// Generated Mar 23, 2016 4:48:05 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Carddetails generated by hbm2java
 */
@Entity(name = "carddetails")
@Table(name = "CARDDETAILS", catalog = "Ankita")
public class Carddetails implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String cardNumber;
	private String pin;
	private Date cardExpiryDtm;
	private String cardType;
	private String bankName;
	private BigDecimal amount;
	private Cart cart;
	private Customer customer;

	public Carddetails() {
	}

	/*
	 * Modified Constructors to support mapping
	 */
	public Carddetails(Customer customer, Cart cart, String cardNumber, String pin, Date cardExpiryDtm, String cardType, BigDecimal amount) {
		this.cardNumber = cardNumber;
		this.customer = customer;
		this.cardExpiryDtm = cardExpiryDtm;
		this.cardType = cardType;
		this.amount = amount;
		this.cart = cart;
	}

	public Carddetails(Customer customer, Cart cart, String cardNumber, String pin, Date cardExpiryDtm, String cardType,
			String bankName, BigDecimal amount) {
		this.cardNumber = cardNumber;
		this.customer = customer;
		this.pin = pin;
		this.cardExpiryDtm = cardExpiryDtm;
		this.cardType = cardType;
		this.bankName = bankName;
		this.amount = amount;
		this.cart = cart;
	}

	@Id
	@Column(name = "CARD_NUMBER", unique = true, nullable = false, length = 20)
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/*
	 * Added to Support Many-to-One mapping with Cart
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	/*
	 * Added to Support Many-to-One mapping with Customer
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "PIN", length = 4)
	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CARD_EXPIRY_DTM", nullable = false, length = 19)
	public Date getCardExpiryDtm() {
		return this.cardExpiryDtm;
	}

	public void setCardExpiryDtm(Date cardExpiryDtm) {
		this.cardExpiryDtm = cardExpiryDtm;
	}

	@Column(name = "CARD_TYPE", nullable = false, length = 20)
	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Column(name = "BANK_NAME", length = 20)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "AMOUNT", nullable = false, precision = 15)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
