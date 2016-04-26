package com.test.ecommerce.dao.daoInterfaces;

import org.hibernate.Session;

import com.test.ecommerce.output.entities.Customer;

public interface CustomerDAOInterface {

	public abstract Customer addCustomer(String fName, String lName, String pwd, String email, int phNo);

	public abstract Customer getAllCustomers(Session session);

	public abstract void updateCustomerPhNum(Customer customerId, int phNo, Session session);

	public abstract void updateCustomerEMail(Customer customerId, String email, Session session);

	public abstract void updateCustomerPwd(Customer customerId, String pwd, Session session);

	public abstract void deleteCustomer(Customer customerId, Session session);

}
