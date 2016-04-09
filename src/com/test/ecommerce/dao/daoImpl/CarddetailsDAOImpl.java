package com.test.ecommerce.dao.daoImpl;

import java.util.Date;
import java.util.GregorianCalendar;

import com.test.ecommerce.dao.daoInterfaces.CarddetailsDAOInterface;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.util.EcommerceUtil;

public class CarddetailsDAOImpl extends EcommerceUtil implements CarddetailsDAOInterface {

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
		cardNumber.replaceAll("[ ]-|", "");
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

	@Override
	public void addCardInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCardInfo(Customer customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCardInfo(Customer customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCardInfo(Customer customerId) {
		// TODO Auto-generated method stub

	}

}
