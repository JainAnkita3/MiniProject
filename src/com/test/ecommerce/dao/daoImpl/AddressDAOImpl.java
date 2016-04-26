package com.test.ecommerce.dao.daoImpl;

import static com.test.ecommerce.constants.EcommerceConstants.ADDING_RECORDS_IN_TABLE;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_DELETED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_INSERTED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_UPDATED_SUCCESSFULLY;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.test.ecommerce.dao.daoInterfaces.AddressesDAOInterface;
import com.test.ecommerce.output.entities.Addresses;
import com.test.ecommerce.output.entities.Customer;
import com.test.ecommerce.util.EcommerceUtil;

public class AddressDAOImpl extends EcommerceUtil implements AddressesDAOInterface {

	private Addresses addresses;

	/**
	 * This method will set the values of Addresses Table
	 */
	@Override
	public Addresses addAddress(Customer customerId, String streetNm, String city, String apt, String addressType) {
		addresses = new Addresses();
		System.out.println(ADDING_RECORDS_IN_TABLE);
		addresses.setCustomer(customerId);
		addresses.setStreetName(streetNm);
		addresses.setCity(city);
		addresses.setApt(apt);
		addresses.setAddressType(addressType.toUpperCase());
		System.out.println(RECORD_INSERTED_SUCCESSFULLY);
		return addresses;
	}

	/**
	 * This method will the read all the records from table
	 */
	@Override
	public Addresses getAddress(Customer customerId, Session session) {
		System.out.println("Getting");
		Query hqlQuery = session.createQuery("from Addresses");
		List<Addresses> addressList = hqlQuery.list();
		for (Addresses addresses : addressList) {
			System.out.println("List of Address" + addresses);
		}
		return addresses;
	}

	/**
	 * This method will the update the address type records in table
	 */
	@Override
	public void updateAddressType(Addresses addressId, String addressType, Session session) {
		try {
			addresses = session.get(Addresses.class, addressId);
			addresses.setAddressType(addressType);
			session.saveOrUpdate(addresses);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + addresses.getAddressType() + "$'");
		} catch (HibernateException e) {
			System.out.println("Caught Exception");
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will the read all the records from table
	 */
	@Override
	public void updateAddress(Addresses addressId, String streetNm, String city, String apt, String addressType,
			Session session) {
		try {
			addresses = session.get(Addresses.class, addressId);
			addresses.setStreetName(streetNm);
			addresses.setCity(city);
			addresses.setApt(apt);
			addresses.setAddressType(addressType);
			session.saveOrUpdate(addresses);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + addresses.getStreetName() + "$'");
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
	public void deleteAddress(Addresses addressId, Session session) {
		System.out.println("Inside Delete Addresses");
		addresses = session.load(Addresses.class, addressId);
		System.out.println("Addresses  : " + addresses);
		session.delete(addresses);
		System.out.println(RECORD_DELETED_SUCCESSFULLY);
	}

}
