package com.test.ecommerce.dao.daoInterfaces;

import java.math.BigDecimal;

import org.hibernate.Session;

import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Product;

public interface CartDAOInterface {

	public abstract Cart addCart(Customer customer, Product product, String quantityOrdered, BigDecimal cartProdPrice,
			BigDecimal totalPrice);

	public abstract Cart getCartDetails(Session session);

	public abstract Cart getCartFromCustId(int customerId, Session session);

	public abstract Cart getCartFromOrderId(int orderId, Session session);

	public abstract void updateCartPPFromCustId(int customerId, BigDecimal productPrice, Session session);

	public abstract void updateCartPPFromOrderId(int orderId, BigDecimal productPrice, Session session);

	public abstract void deleteCart(int orderId, Session session);

}