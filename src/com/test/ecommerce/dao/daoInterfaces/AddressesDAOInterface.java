package com.test.ecommerce.dao.daoInterfaces;

import com.test.ecommerce.output.entities.Addresses;

public interface AddressesDAOInterface {

	public abstract void addAddress();

	public abstract void getAddress(Addresses addressId);

	public abstract void updateAddress(Addresses addressId);

	public abstract void deleteAddress(Addresses addressId);

}
