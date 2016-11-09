package com.test.ecommerce.services;

import static com.test.ecommerce.constants.EcommerceConstants.RECORD_INSERTED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.SESSION;
import static com.test.ecommerce.constants.EcommerceConstants.TRANSACTION;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.test.ecommerce.dao.daoImpl.AddressDAOImpl;
import com.test.ecommerce.dao.daoImpl.CarddetailsDAOImpl;
import com.test.ecommerce.dao.daoImpl.CartDAOImpl;
import com.test.ecommerce.dao.daoImpl.CustomerDAOImpl;
import com.test.ecommerce.dao.daoImpl.OrdersDAOImpl;
import com.test.ecommerce.dao.daoImpl.ProductDAOImpl;
import com.test.ecommerce.output.entities.Addresses;
import com.test.ecommerce.output.entities.Carddetails;
import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.output.entities.Orders;
import com.test.ecommerce.output.entities.Product;
import com.test.ecommerce.util.EcommerceUtil;

public class ServiceImpl extends EcommerceUtil {

	public static void main(String[] args) throws Exception {

		final Logger logger = Logger.getLogger(ServiceImpl.class);

		System.out.println("Logger info enabled : " + logger.isInfoEnabled());
		logger.info("AJ");

		final int prodId;
		final String productName = "Salt";
		final String quantityOrdered;
		final String cardNumber;
		BigDecimal totalAmount = new BigDecimal("0.00");
		final BigDecimal cartPP1;
		final BigDecimal cartPP2;
		final Product product;
		final Product product1;
		final Product product2;
		final Product product3;
		final Product product4;
		final Customer customer1;
		final Customer customer2;
		final Customer customer3;
		final Addresses address1;
		final Addresses address2;
		final Addresses address3;
		final Addresses address4;
		final Orders order1;
		final Orders order2;
		final Cart cart1;
		final Cart cart2;
		Carddetails carddetails = new Carddetails();
		DateFormat formatter = new SimpleDateFormat("mm/dd/yy");
		Date expiryDate = formatter.parse("12/12/21");

		ProductDAOImpl productDAOImpl = new ProductDAOImpl();
		CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
		AddressDAOImpl addressDAOImpl = new AddressDAOImpl();
		CartDAOImpl cartDAOImpl = new CartDAOImpl();
		OrdersDAOImpl orderDAOImpl = new OrdersDAOImpl();
		CarddetailsDAOImpl carddetailsDAOImpl = new CarddetailsDAOImpl();

		// System.out.println("Cleaning DB......!!!!");
		// MySqlJDBConn m = new MySqlJDBConn();
		// m.resetDatabase();

		// Creating Session.
		SESSION = getSession();
		TRANSACTION = createTransaction(SESSION);

		product = productDAOImpl.addProduct("milk", "WM", "2.49", "70");
		product1 = productDAOImpl.addProduct("Spicee", "HEB", "2.99", "70");
		product2 = productDAOImpl.addProduct("Salt", "HEB", "3.99", "70");
		product3 = productDAOImpl.addProduct("Sugar", "GV", "5.99", "20");
		product4 = productDAOImpl.addProduct("Tea", "RedLable", "4.99", "30");

		customer1 = customerDAOImpl.addCustomer("ank", "jain", "1234", "jain.ank@gmail.com", 123456789);
		customer2 = customerDAOImpl.addCustomer("tina", "shah", "1234", "shah.tina@gmail.com", 223456789);
		customer3 = customerDAOImpl.addCustomer("mina", "morya", "1234", "morya.mina@gmail.com", 323456789);

		address1 = addressDAOImpl.addAddress(customer1, "Hunt ln", "SA", "1111", "RESI");
		address2 = addressDAOImpl.addAddress(customer1, "De zavala ln", "SA", "1221", "BUSSINESS");
		address3 = addressDAOImpl.addAddress(customer2, "Hubner ln", "HOU", "3111", "resi");
		address4 = addressDAOImpl.addAddress(customer3, "whitby ln", "DALLAS", "2111", "RESI");

		quantityOrdered = "3";
		totalAmount = getTotalAmount(new BigDecimal("0.00"), totalAmount);
		cartPP1 = cartDAOImpl.getTotalCartProdPrice(product1, quantityOrdered);
		cartPP2 = cartDAOImpl.getTotalCartProdPrice(product2, quantityOrdered);
		cart1 = cartDAOImpl.addCart(customer1, product1, quantityOrdered, cartPP1,
				getTotalAmount(cartPP1, totalAmount));
		cart2 = cartDAOImpl.addCart(customer1, product2, quantityOrdered, cartPP2,
				getTotalAmount(cartPP2, totalAmount));

		
		order1 = orderDAOImpl.addOrders(cart1, customer1, new java.util.Date(), quantityOrdered, cartPP1,
				getTotalAmount(cartPP1, totalAmount), "SHIPPED", "AIRWY123");
		order2 = orderDAOImpl.addOrders(cart2, customer1, new java.util.Date(), quantityOrdered, cartPP2,
				getTotalAmount(cartPP2, totalAmount), "SHIPPED", "AIRWY123");
		
		cardNumber = "7242-1234-1234-1234";
		carddetails = carddetailsDAOImpl.addCardInfo(customer1, cart1, cardNumber, "1234", expiryDate, "Visa", "Chase",
				totalAmount);
		try {
			SESSION.save(customer1);
			SESSION.save(customer2);
			SESSION.save(customer3);
			SESSION.save(customer1);

			SESSION.save(address1);
			SESSION.save(address2);
			SESSION.save(address3);
			SESSION.save(address4);

			SESSION.save(product);
			SESSION.save(product1);
			SESSION.save(product2);
			SESSION.save(product3);
			SESSION.save(product4);
			
			SESSION.save(cart1);
			SESSION.save(cart2);

			SESSION.save(order1);
			SESSION.save(order2);

			if (carddetailsDAOImpl.isValidCardNumber(cardNumber)) {
				SESSION.save(carddetails);
			}
			System.out.println(RECORD_INSERTED_SUCCESSFULLY);
		} catch (Exception e) {
			System.out.println("Exception occured, Something went really wrong");
			e.getMessage();
			e.printStackTrace();
		} finally {
			closeSession();
		}

		prodId = productDAOImpl.doSqlQuery(productName, SESSION);
		System.out.println("prodID : " + prodId);
		productDAOImpl.getProductFromProdId(SESSION, prodId);
		productDAOImpl.getAllProducts(SESSION);

		// TODO not working throwing Error PRODUCT_ID cant be null
		// productDAOImpl.updateProductSP(prodId, (new BigDecimal("7.99")),
		// SESSION);
		// productDAOImpl.deleteProduct(prodId, SESSION);

		// Criteria & Projection
		// Criteria ct = SESSION.createCriteria(Cart.class);
		// Criterion crt = Restrictions.and(Restrictions.like(propertyName,
		// value))

		// Txn commit & Closing session
		closeSession();
	}

	public static BigDecimal getTotalAmount(BigDecimal cartPP, BigDecimal totalAmount) {
		totalAmount = cartPP.add(totalAmount);
		return totalAmount;
	}

}
