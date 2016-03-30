package com.test.ecommerce.dao.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.test.ecommerce.dao.daoInterfaces.CustomerDAOInterface;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.util.EcommerceUtil;

public class CustomerDAOImpl extends EcommerceUtil implements CustomerDAOInterface {

	final Logger logger = Logger.getLogger(CustomerDAOImpl.class);

	/**
	 * This method will the read records from table
	 */
	public void getCustomer() {

		createTransaction(getSession());
		Query hqlQuery = getSession().createQuery("from Item");
		List<Customer> customers = hqlQuery.list();
		for (Customer customer : customers) {
			logger.debug("first : '" + customer.getFirstName() + "', Last Name : '" + customer.getLastName()
					+ "', Id : '" + customer.getCustomerId() + "', Manufacturer : '" + customer.getAddresses());
		}
		closeSession();
	}
}
