package com.test.ecommerce.services;

import static com.test.ecommerce.constants.EcommerceConstants.RECORD_INSERTED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.SESSION;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.test.ecommerce.dao.daoImpl.CartDAOImpl;
import com.test.ecommerce.dao.daoImpl.ProductDAOImpl;
import com.test.ecommerce.output.entities.Addresses;
import com.test.ecommerce.output.entities.Carddetails;
import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Orders;
import com.test.ecommerce.output.entities.Product;
import com.test.ecommerce.util.EcommerceUtil;

public class ServiceImpl extends EcommerceUtil {

	public static void main(String[] args) {

		final Logger logger = Logger.getLogger(ServiceImpl.class);

		System.out.println(logger.isInfoEnabled());
		logger.info("AJ");

		Product product1 ;
		Product product2 ;
		Product product3 ;
		Product product4 ;
		Product product5 = new Product();
		Product product6 = new Product();

		Customer customer = new Customer("aja", "", "ajain@gmail");
		Addresses address1 = new Addresses(customer, "HuntLane", "SA", "13201", "BILLING");
		Addresses address2 = new Addresses(customer, "Eckhert", "SA", "15200", "BILLING");
		Addresses address3 = new Addresses(customer, "Alamo", "SA", "14205", "BILLING");
		Addresses address4 = new Addresses(customer, "Huebner", "SA", "12207", "BILLING");

		Set<Addresses> addrsSet = new HashSet<Addresses>();
		addrsSet.add(address1);
		addrsSet.add(address2);
		addrsSet.add(address3);
		addrsSet.add(address4);
		customer.setAddresses(addrsSet);

		product5 = new Product("Milk", new BigDecimal("3.99"), "50");
		product6 = new Product("Water", new BigDecimal("2.99"), "50");

		CartDAOImpl cartDao = new CartDAOImpl();
		BigDecimal totalCartProdPrice1 = cartDao.getCartProdTotalPrice(product5.getSellingPrice(), "2");
		System.out.println("fjh : " + totalCartProdPrice1);

		// TODO Future use for total amount payable by customer to put in Cart
		// BigDecimal totalCartProdPrice;
		Cart cart = new Cart(customer, product5, "1", totalCartProdPrice1, totalCartProdPrice1);
		// TODO need to add multiple Products with same Cart Id
		cart = new Cart(customer, product6, "5", totalCartProdPrice1, totalCartProdPrice1);

		cart.setCustomer(customer);
		customer.setCart(cart);

		// TODO Orders should have HashSet<ProductID, Quantity> from Cart
		Orders custOrder = new Orders(cart, customer, new java.util.Date(), "2", totalCartProdPrice1,
				totalCartProdPrice1);
		custOrder.setCart(cart);
		custOrder.setCustomer(customer);

		Date creditCardExpiry = Date.valueOf("2019-06-01");
		// Carddetails cardDetails = new Carddetails(customer, cart,
		// "123456789123", "1234", creditCardExpiry, "Credit", new
		// BigDecimal("90.99"));
		Carddetails cardDetails = new Carddetails();
		cardDetails.setCustomer(customer);
		cardDetails.setCart(cart);
		cardDetails.setCardNumber("1234-1234-1233-4536");
		cardDetails.setPin("1234");
		cardDetails.setCardExpiryDtm(creditCardExpiry);
		cardDetails.setCardType("credit");
		cardDetails.setAmount(new BigDecimal("90.99"));

		Set<Carddetails> cardSet = new HashSet<Carddetails>();
		cardSet.add(cardDetails);
		customer.setCardDetails(cardSet);

		cart.setCarddetails(cardSet);

		// Creating Session.
		SESSION = getSession();

		/*SESSION.saveOrUpdate(customer);
		SESSION.saveOrUpdate(cardDetails);

		
		 SESSION.saveOrUpdate(customer);
		 SESSION.saveOrUpdate(address1);
		 SESSION.saveOrUpdate(address2); 
		 SESSION.saveOrUpdate(address3);
		 SESSION.saveOrUpdate(address4); 
		//SESSION.saveOrUpdate(product1);
		 SESSION.saveOrUpdate(cart); 
		 SESSION.saveOrUpdate(custOrder);
		 SESSION.saveOrUpdate(cardDetails);*/
		 


		ProductDAOImpl productDAOImpl = new ProductDAOImpl();

		product1 = productDAOImpl.addProduct("Spice", "HEB", "2.99", "70");
		product2 = productDAOImpl.addProduct("Salt", "HEB", "2.99", "70");
		product3 = productDAOImpl.addProduct("Sugar", "GV", "5.99", "20");
		product4 = productDAOImpl.addProduct("Tea2", "RedLable", "4.99", "30");

		//try{ -- Use this for exception handling
		/*SESSION.saveOrUpdate(product1);
		SESSION.saveOrUpdate(product2);
		SESSION.saveOrUpdate(product3);
		SESSION.saveOrUpdate(product4);
		System.out.println(RECORD_INSERTED_SUCCESSFULLY);*/
		//}catch(){
			
		//}
		
		// Txn commit & Closing session
		//closeSession();


		// Orders order = new Orders(101, 1001, new java.util.Date(), "5", new
		// BigDecimal("2.99"), new BigDecimal("14.95"),
		// "PROCESS", "AIRWY111");

		// Cart cart = new Cart();

	}

}
