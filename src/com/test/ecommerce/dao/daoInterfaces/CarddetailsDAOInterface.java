package com.test.ecommerce.dao.daoInterfaces;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;

import com.test.ecommerce.output.entities.Carddetails;
import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;

public interface CarddetailsDAOInterface {

	public abstract Carddetails addCardInfo(Customer customerId, Cart orderId, String cardNum, String pin,
			Date cardExpiryDt, String cardType, String bankName, BigDecimal amount);

	public abstract Carddetails getCardInfo(Customer customerId, Session session);

	public abstract void updateCardType(Customer customerId, String cardType, Session session);

	public abstract void deleteCardInfo(Customer customerId, Session session);

}
