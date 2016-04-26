package com.test.ecommerce.dao.daoImpl;

import static com.test.ecommerce.constants.EcommerceConstants.ADDING_RECORDS_IN_TABLE;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_DELETED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_INSERTED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_UPDATED_SUCCESSFULLY;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.test.ecommerce.dao.daoInterfaces.CarddetailsDAOInterface;
import com.test.ecommerce.output.entities.Carddetails;
import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.util.EcommerceUtil;

public class CarddetailsDAOImpl extends EcommerceUtil implements CarddetailsDAOInterface {

	private Carddetails cardDetails;
	private String regex = "[|/-]";

	/**
	 * The raw credit card number that the user has entered at the command line.
	 */
	private String creditCardNumber;
	/**
	 * The date on which the card expires. Generally, we are only going to use
	 * the month and year from this date.
	 */
	private Date expirationDate;

	/**
	 * Returns whether the CreditCard number is valid.
	 *
	 * @return a <code>boolean</code> value indicating whether the expiration
	 *         date of the credit card is in the future.
	 */
	public boolean isValidCardNumber(String cardNumber) {
		boolean isValid = false;
		// Clear out any existing dashes or spaces
		cardNumber = cardNumber.replaceAll(regex, "");
		if (cardNumber.length() == 16) {
			isValid = true;
		}
		return isValid;
	}

	/*
	 * It will return the protected card number like xxxx-xxxx-xxxx-1234
	 */
	public String getProtectedCardNumber() {

		StringBuffer protectedCardNumber = new StringBuffer();
		for (int i = 0; i < creditCardNumber.length() - 4; i++) {
			protectedCardNumber.append("X");
		}

		protectedCardNumber.append(creditCardNumber.substring(creditCardNumber.length() - 4));
		creditCardNumber = protectedCardNumber.toString();

		return creditCardNumber;

	}

	/**
	 * Returns whether the expiration date provided is later than the current
	 * date.
	 *
	 * @return a <code>boolean</code> value indicating whether the expiration
	 *         date of the credit card is in the future.
	 */
	public boolean isValidExpirationDate() {
		return expirationDate.after(new GregorianCalendar().getTime());
	}

	/**
	 * This method will set the values of Carddetails Table
	 */
	@Override
	public Carddetails addCardInfo(Customer customerId, Cart orderId, String cardNum, String pin, Date cardExpiryDt,
			String cardType, String bankName, BigDecimal amount) {
		cardDetails = new Carddetails();
		System.out.println(ADDING_RECORDS_IN_TABLE + getClass().getName());
		cardDetails.setCustomer(customerId);
		cardDetails.setCart(orderId);
		// if (isValidCardNumber(cardNum)) {
		cardDetails.setCardNumber(cardNum);
		// }
		cardDetails.setPin(pin);
		cardDetails.setCardExpiryDtm(cardExpiryDt);
		cardDetails.setCardType(cardType.toUpperCase());
		cardDetails.setBankName(bankName);
		cardDetails.setAmount(amount);
		System.out.println(RECORD_INSERTED_SUCCESSFULLY);
		return cardDetails;
	}

	/**
	 * This method will the read all the records from table
	 */
	@Override
	public Carddetails getCardInfo(Customer customerId, Session session) {
		System.out.println("Getting");
		Query hqlQuery = session.createQuery("from carddetails");
		List<Carddetails> carddetailsList = hqlQuery.list();
		for (Carddetails carddetails : carddetailsList) {
			System.out.println("List of CardDetails" + carddetails);
		}
		return cardDetails;
	}

	/**
	 * This method will u all the records from table
	 */
	@Override
	public void updateCardType(Customer customerId, String cardType, Session session) {
		try {
			cardDetails = session.get(Carddetails.class, customerId);
			cardDetails.setCardType(cardType);
			session.saveOrUpdate(cardDetails);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + cardDetails.getCardType() + "$'");
		} catch (HibernateException e) {
			System.out.println("Caught Exception");
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will delete the record from table
	 */
	@Override
	public void deleteCardInfo(Customer customerId, Session session) {
		System.out.println("Inside DeleteCart");
		cardDetails = session.load(Carddetails.class, customerId);
		System.out.println("CardDetails  : " + cardDetails);
		session.delete(cardDetails);
		System.out.println(RECORD_DELETED_SUCCESSFULLY);
	}

}
