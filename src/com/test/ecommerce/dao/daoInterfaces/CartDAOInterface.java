package com.test.ecommerce.dao.daoInterfaces;

import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Orders;

public interface CartDAOInterface {

	public abstract void addCart();

	public abstract void getCart(Customer customerId);

	public abstract void getCart(Orders orderId);

	public abstract void updateCart(Customer customerId);

	public abstract void updateCart(Orders orderId);

	public abstract void deleteCart(Customer customerId);

	public abstract void deleteCart(Orders orderId);
}