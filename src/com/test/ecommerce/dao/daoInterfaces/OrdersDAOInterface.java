package com.test.ecommerce.dao.daoInterfaces;

import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Orders;

public interface OrdersDAOInterface {

	public abstract void addOrders();

	public abstract void getOrders(Customer customerId);

	public abstract void getOrders(Orders orderId);

	public abstract void updateOrders(Customer customerId);

	public abstract void updateOrders(Orders orderId);

	public abstract void deleteOrders(Customer customerId);

	public abstract void deleteOrders(Orders orderId);

}
