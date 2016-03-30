package com.test.ecommerce.output.entities;

// default package
// Generated Mar 23, 2016 4:48:05 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Carddetails generated by hbm2java
 */
@Entity
@Table(name = "CARDDETAILS", catalog = "Ankita")
public class Carddetails implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String cardNumber;
	private Customer customerId;
	private String pin;
	private Date cardExpiryDtm;
	private String cardType;
	private String bankName;
	private BigDecimal amount;

	public Carddetails() {
	}

	/*
	 * Modified Constructors to support mapping
	 */
	public Carddetails(String cardNumber, Customer customerId, Date cardExpiryDtm, String cardType, BigDecimal amount) {
		this.cardNumber = cardNumber;
		this.customerId = customerId;
		this.cardExpiryDtm = cardExpiryDtm;
		this.cardType = cardType;
		this.amount = amount;
	}

	public Carddetails(String cardNumber, Customer customerId, String pin, Date cardExpiryDtm, String cardType,
			String bankName, BigDecimal amount) {
		this.cardNumber = cardNumber;
		this.customerId = customerId;
		this.pin = pin;
		this.cardExpiryDtm = cardExpiryDtm;
		this.cardType = cardType;
		this.bankName = bankName;
		this.amount = amount;
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
	 * Added to Support One to One mapping based on CustomerId
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
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
