package com.test.ecommerce.dao.daoImpl;

import static com.test.ecommerce.constants.EcommerceConstants.ADDING_RECORDS_IN_TABLE;
import static com.test.ecommerce.constants.EcommerceConstants.CAUGHT_EXCEPTION;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_DELETED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_INSERTED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_UPDATED_SUCCESSFULLY;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.test.ecommerce.dao.daoInterfaces.CartDAOInterface;
import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Product;
import com.test.ecommerce.util.EcommerceUtil;

public class CartDAOImpl extends EcommerceUtil implements CartDAOInterface {

	final Logger logger = Logger.getLogger(ProductDAOImpl.class);
	private Cart cart;
	private BigDecimal totalPrice;

	/**
	 * This method will return the Cart Product Total price
	 */
	public BigDecimal getTotalCartProdPrice(Product prodPrice, String quantityOrdered) {
		totalPrice = (prodPrice.getSellingPrice()).multiply(new BigDecimal(quantityOrdered));
		return totalPrice;
	}

	/**
	 * This method will return the total amount needs to pay by customer
	 */
	public BigDecimal getTotalCartAmount(BigDecimal price) {
		this.totalPrice = price;
		return totalPrice;
	}

	/**
	 * This method will set the values of Order Table
	 */
	@Override
	public Cart addCart(Customer customer, Product product, String quantityOrdered, BigDecimal cartProdPrice,
			BigDecimal totalAmount) {
		cart = new Cart();
		System.out.println(ADDING_RECORDS_IN_TABLE + getClass().getName());
		cart.setCustomer(customer);
		cart.setProduct(product);
		cart.setQuantityOrdered(quantityOrdered);
		cart.setCProdPrice(cartProdPrice);
		cart.setTotalAmount(totalAmount);
		System.out.println(RECORD_INSERTED_SUCCESSFULLY);
		return cart;
	}

	/**
	 * This method will the read all the records from table
	 */
	@Override
	public Cart getCartDetails(Session session) {
		System.out.println("Getting");
		Query hqlQuery = session.createQuery("from Orders");
		List<Cart> cartList = hqlQuery.list();
		for (Cart cart : cartList) {
			System.out.println("List of Orders" + cart);
		}
		return cart;
	}

	/**
	 * This method will return the record from table using Customer Id
	 */
	@Override
	public Cart getCartFromCustId(int customerId, Session session) {
		cart = session.load(Cart.class, customerId);
		return cart;
	}

	/**
	 * This method will return the record from table using Order Id
	 */
	@Override
	public Cart getCartFromOrderId(int orderId, Session session) {
		cart = session.load(Cart.class, orderId);
		return cart;
	}

	/**
	 * This method will update the PP record from table using Customer Id
	 */
	@Override
	public void updateCartPPFromCustId(int customerId, BigDecimal productPrice, Session session) {
		try {
			cart = session.get(Cart.class, customerId);
			cart.setCProdPrice(productPrice);
			session.saveOrUpdate(cart);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + cart.getCProdPrice() + "$'");
		} catch (HibernateException e) {
			System.out.println(CAUGHT_EXCEPTION);
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will update the PP record from table using Order Id
	 */
	@Override
	public void updateCartPPFromOrderId(int orderId, BigDecimal productPrice, Session session) {
		try {
			cart = session.get(Cart.class, orderId);
			cart.setCProdPrice(productPrice);
			session.saveOrUpdate(cart);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + cart.getCProdPrice() + "$'");
		} catch (HibernateException e) {
			System.out.println("Caught Exception");
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will delete the record from table
	 */
	@Override
	public void deleteCart(int orderId, Session session) {
		System.out.println("Inside DeleteCart");
		cart = session.load(Cart.class, orderId);
		System.out.println("Cart  : " + cart);
		session.delete(cart);
		System.out.println(RECORD_DELETED_SUCCESSFULLY);
	}

}
