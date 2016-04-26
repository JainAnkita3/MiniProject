package com.test.ecommerce.dao.daoInterfaces;

import org.hibernate.Session;

import com.test.ecommerce.output.entities.Addresses;
import com.test.ecommerce.output.entities.Customer;

public interface AddressesDAOInterface {

	public abstract Addresses addAddress(Customer customerId, String streetNm, String city, String apt,
			String addressType);

	public abstract Addresses getAddress(Customer customerId, Session session);

	public abstract void updateAddressType(Addresses addressId, String addresstype, Session session);

	public abstract void updateAddress(Addresses addressId, String streetNm, String city, String apt,
			String addressType, Session session);

	public abstract void deleteAddress(Addresses addressId, Session session);

}
