package com.test.ecommerce.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.test.ecommerce.dao.daoImpl.ProductDAOImpl;
import com.test.ecommerce.output.entities.Addresses;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.util.EcommerceUtil;

public class ServiceImpl extends EcommerceUtil {

	public static void main(String[] args) {

		final Logger logger = Logger.getLogger(ServiceImpl.class.getName());

		/*
		 * Customer customer = new Customer("Aj", "Jain", "1234", "SA",
		 * "jain@gmail", 123456789); session.save(customer);
		 * 
		 * Addresses addresses = new Addresses(customer, 2201, "HuntLane", "SA",
		 * "13201", "BILLING"); session.save(addresses);
		 * 
		 * addresses.setCustomer(customer); session.save(addresses);
		 * 
		 * customer.getAddresses().add(addresses);
		 */

		Customer customer = new Customer("Aj", "1234", "jain@gmail");

		Addresses address1 = new Addresses(customer, 2201, "HuntLane", "SA", "13201", "BILLING");
		//Addresses address2 = new Addresses(customer, 2201, "Eckhert", "SA", "15200", "BILLING");

		//Addresses address3 = new Addresses(customer, 2201, "Alamo", "SA", "14205", "BILLING");
		//Addresses address4 = new Addresses(customer, 2201, "Huebner", "SA", "12207", "BILLING");

		Set<Addresses> addrs = new HashSet<Addresses>();
		addrs.add(address1);
		//addrs.add(address2);
		//addrs.add(address3);
		//addrs.add(address4);

		customer.setAddresses(addrs);

		getSession().save(customer);

		// session.save(addrs);
		closeSession();

		ProductDAOImpl p = new ProductDAOImpl();

		p.addProduct();

		// Orders order = new Orders(101, 1001, new java.util.Date(), "5", new
		// BigDecimal("2.99"), new BigDecimal("14.95"),
		// "PROCESS", "AIRWY111");

		// Cart cart = new Cart();

	}

}
