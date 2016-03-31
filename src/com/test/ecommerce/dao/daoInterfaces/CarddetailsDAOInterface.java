package com.test.ecommerce.dao.daoInterfaces;

import com.test.ecommerce.output.entities.Customer;

public interface CarddetailsDAOInterface {

	public abstract void addCardInfo();

	public abstract void getCardInfo(Customer customerId);

	public abstract void updateCardInfo(Customer customerId);

	public abstract void deleteCardInfo(Customer customerId);
}
