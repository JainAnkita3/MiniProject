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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name = "ORDERS", catalog = "Ankita")
public class Orders implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Customer customerId;
	private Date orderDate;
	private String quantityOrdered;
	private BigDecimal CProdPrice;
	private BigDecimal totalAmount;
	private String status;
	private String trackingNum;

	/*
	 * Changes Done for One to One mapping for Primary Key OrderId
	 */
	@OneToOne
	@PrimaryKeyJoinColumn
	private Cart cartOrderId;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	public Cart getOrderId() {
		return cartOrderId;
	}

	public void setOrderId(Cart orderId) {
		this.cartOrderId = orderId;
	}

	/*
	 * Changes Done for One to One mapping for Foreign Key CustomerID
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public Customer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE", nullable = false, length = 19)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "QUANTITY_ORDERED", nullable = false, length = 20)
	public String getQuantityOrdered() {
		return this.quantityOrdered;
	}

	public void setQuantityOrdered(String quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	@Column(name = "C_PROD_PRICE", nullable = false, precision = 10)
	public BigDecimal getCProdPrice() {
		return this.CProdPrice;
	}

	public void setCProdPrice(BigDecimal CProdPrice) {
		this.CProdPrice = CProdPrice;
	}

	@Column(name = "TOTAL_AMOUNT", nullable = false, precision = 10)
	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "STATUS", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "TRACKING_NUM", length = 32)
	public String getTrackingNum() {
		return this.trackingNum;
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}

	public Orders() {
	}

	/*
	 * Modified Constructors to support mapping
	 */
	public Orders(Cart orderId, Customer customerId, Date orderDate, String quantityOrdered, BigDecimal CProdPrice,
			BigDecimal totalAmount) {
		this.cartOrderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.quantityOrdered = quantityOrdered;
		this.CProdPrice = CProdPrice;
		this.totalAmount = totalAmount;
	}

	public Orders(Cart orderId, Customer customerId, Date orderDate, String quantityOrdered, BigDecimal CProdPrice,
			BigDecimal totalAmount, String status, String trackingNum) {
		this.cartOrderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.quantityOrdered = quantityOrdered;
		this.CProdPrice = CProdPrice;
		this.totalAmount = totalAmount;
		this.status = status;
		this.trackingNum = trackingNum;
	}

}
