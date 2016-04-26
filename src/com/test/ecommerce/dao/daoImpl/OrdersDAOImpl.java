package com.test.ecommerce.dao.daoImpl;

import static com.test.ecommerce.constants.EcommerceConstants.ADDING_RECORDS_IN_TABLE;
import static com.test.ecommerce.constants.EcommerceConstants.CAUGHT_EXCEPTION;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_DELETED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_INSERTED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_UPDATED_SUCCESSFULLY;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.test.ecommerce.dao.daoInterfaces.OrdersDAOInterface;
import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Orders;
import com.test.ecommerce.util.EcommerceUtil;

public class OrdersDAOImpl extends EcommerceUtil implements OrdersDAOInterface {

	final Logger logger = Logger.getLogger(ProductDAOImpl.class);
	private Orders orders;

	/**
	 * This method will set the values of Orders Table
	 */
	@Override
	public Orders addOrders(Cart orderId, Customer customerId, Date orderDate, String quantityOrdered, BigDecimal cPP,
			BigDecimal totalAmount, String status, String trackingNum) {
		orders = new Orders();
		System.out.println(ADDING_RECORDS_IN_TABLE + getClass().getName());
		orders.setCart(orderId);
		orders.setCustomer(customerId);
		orders.setOrderDate(orderDate);
		orders.setQuantityOrdered(quantityOrdered);
		orders.setCProdPrice(cPP);
		orders.setTotalAmount(totalAmount);
		orders.setStatus(status.toUpperCase());
		orders.setTrackingNum(trackingNum);
		System.out.println(RECORD_INSERTED_SUCCESSFULLY);
		return orders;
	}

	/**
	 * This method will the read all the records from table
	 */
	@Override
	public Orders getAllOrders(Session session) {
		System.out.println("Getting");
		Query hqlQuery = session.createQuery("from Orders");
		List<Orders> orderList = hqlQuery.list();
		for (Orders orders : orderList) {
			System.out.println("List of Orders" + orders);
		}
		return orders;
	}

	/**
	 * This method will return the OrderDetails from table using Customer Id
	 */
	@Override
	public Orders getOrdersFromCustomerId(Customer customerId, Session session) {
		orders = session.load(Orders.class, customerId);
		return orders;
	}

	/**
	 * This method will return the OrderDetails from table using Order Id
	 */
	@Override
	public Orders getOrdersFromOrderId(Orders orderId, Session session) {
		orders = session.load(Orders.class, orderId);
		return orders;
	}

	/**
	 * This method will update Status record from table using Order Id
	 */
	@Override
	public void updateOrdersStatus(Orders orderId, String status, Session session) {
		try {
			orders = session.get(Orders.class, orderId);
			orders.setStatus(status);
			session.saveOrUpdate(orders);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + orders.getStatus() + "$'");
		} catch (HibernateException e) {
			System.out.println(CAUGHT_EXCEPTION);
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will update trackingNum record from table using Order Id
	 */
	@Override
	public void updateOrdersTrackingNum(Orders orderId, String trackingNum, Session session) {
		try {
			orders = session.get(Orders.class, orderId);
			orders.setTrackingNum(trackingNum);
			session.saveOrUpdate(orders);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + orders.getTrackingNum() + "$'");
		} catch (HibernateException e) {
			System.out.println(CAUGHT_EXCEPTION);
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will delete the record
	 */
	@Override
	public void deleteOrders(int orderedCartId, Session session) {
		System.out.println("Inside DeleteOrders");
		orders = session.load(Orders.class, orderedCartId);
		System.out.println("Orders  : " + orders);
		session.delete(orders);
		System.out.println(RECORD_DELETED_SUCCESSFULLY);
	}

}
