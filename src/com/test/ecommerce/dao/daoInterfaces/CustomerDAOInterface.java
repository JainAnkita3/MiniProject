package com.test.ecommerce.dao.daoInterfaces;

import com.test.ecommerce.output.entities.Customer;

public interface CustomerDAOInterface {

	public abstract void addCustomer();

	public abstract void getCustomer(Customer customerId);

	public abstract void updateCustomer(Customer customerId);

	public abstract void deleteCustomer(Customer customerId);

}
