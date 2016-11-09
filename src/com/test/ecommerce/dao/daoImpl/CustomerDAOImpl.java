package com.test.ecommerce.dao.daoImpl;

import static com.test.ecommerce.constants.EcommerceConstants.ADDING_RECORDS_IN_TABLE;
import static com.test.ecommerce.constants.EcommerceConstants.CAUGHT_EXCEPTION;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_DELETED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_INSERTED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_UPDATED_SUCCESSFULLY;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.test.ecommerce.dao.daoInterfaces.CustomerDAOInterface;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.util.EcommerceUtil;

public class CustomerDAOImpl extends EcommerceUtil implements CustomerDAOInterface {

	final Logger logger = Logger.getLogger(CustomerDAOImpl.class);
	private Customer customer;

	/**
	 * This method will set the values of Customer Table
	 */
	@Override
	public Customer addCustomer(String fName, String lName, String pwd, String email, int phNo) {
		customer = new Customer();
		System.out.println(ADDING_RECORDS_IN_TABLE + getClass().getName());
		customer.setFirstName(fName);
		customer.setLastName(lName);
		customer.setPassword(pwd);
		customer.setEmail(email);
		customer.setPhNumber(phNo);
		System.out.println(RECORD_INSERTED_SUCCESSFULLY + getClass().getName().substring(31));
		return customer;
	}

	/**
	 * This method will the read all the records from table
	 */
	@Override
	public Customer getAllCustomers(Session session) {
		System.out.println("Getting");
		Query hqlQuery = session.createQuery("from Customer");
		List<Customer> customerList = hqlQuery.list();
		for (Customer customer : customerList) {
			System.out.println("List of Customer" + customer);
		}
		return customer;
	}

	/**
	 * This method will update phNum record from table using Customer Id
	 */
	@Override
	public void updateCustomerPhNum(Customer customerId, int phNo, Session session) {
		try {
			customer = session.get(Customer.class, customerId);
			customer.setPhNumber(phNo);
			session.saveOrUpdate(customer);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + customer.getPhNumber() + "$'");
		} catch (HibernateException e) {
			System.out.println(CAUGHT_EXCEPTION);
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will update email record from table using customer Id
	 */
	@Override
	public void updateCustomerEMail(Customer customerId, String email, Session session) {
		try {
			customer = session.get(Customer.class, customerId);
			customer.setEmail(email);
			session.saveOrUpdate(customer);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + customer.getEmail() + "$'");
		} catch (HibernateException e) {
			System.out.println(CAUGHT_EXCEPTION);
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will update pwd record from table using customer Id
	 */
	@Override
	public void updateCustomerPwd(Customer customerId, String pwd, Session session) {
		try {
			customer = session.get(Customer.class, customerId);
			customer.setPassword(pwd);
			session.saveOrUpdate(customer);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + customer.getPassword() + "$'");
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
	public void deleteCustomer(Customer customerId, Session session) {
		System.out.println("Inside Delete Customer");
		customer = session.load(Customer.class, customerId);
		System.out.println("Item  : " + customer);
		session.delete(customer);
		System.out.println(RECORD_DELETED_SUCCESSFULLY);
	}
}
