package com.test.ecommerce.dao.daoImpl;

import java.math.BigDecimal;

import com.test.ecommerce.dao.daoInterfaces.CartDAOInterface;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Orders;
import com.test.ecommerce.util.EcommerceUtil;

public class CartDAOImpl extends EcommerceUtil implements CartDAOInterface {

	private static BigDecimal totalPrice;

	public BigDecimal getCartProdTotalPrice(BigDecimal prodPrice, String quantityOrdered) {
		totalPrice = prodPrice.multiply(new BigDecimal(quantityOrdered));
		return totalPrice;
	}

	/*
	 *  This method will return the total amount needs to pay by customer
	 */
	public BigDecimal gettotalCartProdPrice(BigDecimal price) {
		totalPrice = totalPrice;
		return totalPrice;
	}

	@Override
	public void addCart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCart(Customer customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCart(Orders orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCart(Customer customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCart(Orders orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCart(Customer customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCart(Orders orderId) {
		// TODO Auto-generated method stub

	}

}
