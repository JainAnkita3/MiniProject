package com.test.ecommerce.dao.daoInterfaces;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;

import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Orders;

public interface OrdersDAOInterface {

	public abstract Orders addOrders(Cart orderId, Customer customerId, Date orderDate, String quantityOrdered,
			BigDecimal cPP, BigDecimal totalAmount, String status, String trackingNum);

	public abstract Orders getAllOrders(Session session);
	
	public abstract Orders getOrdersFromCustomerId(Customer customerId, Session session);

	public abstract Orders getOrdersFromOrderId(Orders orderId, Session session);

	public abstract void updateOrdersStatus(Orders orderId, String status, Session session);

	public abstract void updateOrdersTrackingNum(Orders orderId, String trackingNum, Session session);

	public abstract void deleteOrders(int orderedCartId, Session session);

}
