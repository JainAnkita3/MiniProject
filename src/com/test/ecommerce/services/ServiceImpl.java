package com.test.ecommerce.services;

import static com.test.ecommerce.constants.EcommerceConstants.SESSION;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.test.ecommerce.dao.daoImpl.CartDAOImpl;
import com.test.ecommerce.dao.daoImpl.ProductDAOImpl;
import com.test.ecommerce.output.entities.Addresses;
import com.test.ecommerce.output.entities.Carddetails;
import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Product;
import com.test.ecommerce.util.EcommerceUtil;

public class ServiceImpl extends EcommerceUtil {

	public static void main(String[] args) {

		final Logger logger = Logger.getLogger(ServiceImpl.class.getName());

		Customer customer = new Customer("aja", "", "ajain@gmail");
		Addresses address1 = new Addresses(customer, 2201, "HuntLane", "SA", "13201", "BILLING");
		Addresses address2 = new Addresses(customer, 2201, "Eckhert", "SA", "15200", "BILLING");
		Addresses address3 = new Addresses(customer, 2201, "Alamo", "SA", "14205", "BILLING");
		Addresses address4 = new Addresses(customer, 2201, "Huebner", "SA", "12207", "BILLING");

		Set<Addresses> addrsSet = new HashSet<Addresses>();
		addrsSet.add(address1);
		addrsSet.add(address2);
		addrsSet.add(address3);
		addrsSet.add(address4);
		customer.setAddresses(addrsSet);

		Product product1 = new Product("Milk", new BigDecimal("3.99"), "50");
		Product product2 = new Product("Water", new BigDecimal("2.99"), "50");

		Carddetails cd = new Carddetails();

		CartDAOImpl cartDao = new CartDAOImpl();
		BigDecimal totalCartProdPrice = cartDao.getCartProdTotalPrice(product1.getSellingPrice(), "2");
		System.out.println("fjh : " + totalCartProdPrice);

		// TODO Future use for total amount payable by customer to put in Cart
		// BigDecimal totalCartProdPrice;
		Cart cart = new Cart(customer, product1, "1", totalCartProdPrice, totalCartProdPrice);
		cart = new Cart(customer, product2, "5", totalCartProdPrice, totalCartProdPrice);

		cart.setCustomer(customer);

		SESSION = getSession();
		SESSION.save(customer);
		SESSION.save(address1);
		SESSION.save(address2);
		SESSION.save(address3);
		SESSION.save(address4);
		SESSION.save(product1);
		SESSION.save(cart);

		// Txn commit & Closing session
		closeSession();

		ProductDAOImpl p = new ProductDAOImpl();

		// p.addProduct();

		// Orders order = new Orders(101, 1001, new java.util.Date(), "5", new
		// BigDecimal("2.99"), new BigDecimal("14.95"),
		// "PROCESS", "AIRWY111");

		// Cart cart = new Cart();

	}

}
