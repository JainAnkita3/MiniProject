package com.test.ecommerce.dao.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.test.ecommerce.constants.EcommerceConstants;
import com.test.ecommerce.dao.daoInterfaces.ProductDAOInterface;
import com.test.ecommerce.output.entities.Product;
import com.test.ecommerce.util.EcommerceUtil;

public class ProductDAOImpl extends EcommerceUtil implements ProductDAOInterface {

	private List<Integer> idList = new ArrayList<>();
	private List<Integer> itemId = new ArrayList<Integer>();
	private Product item = new Product();

	public ProductDAOImpl() {
		System.out.println("Construtor");
		addProduct();
		getProduct();
		updateProduct();
		deleteProduct();

		/*
		 * if (!(EcommerceUtil.getSessionFactory().isClosed())) {
		 * EcommerceUtil.getSessionFactory().close(); System.out.println(
		 * "Closing Session Factory"); }
		 */
	}

	/**
	 * This method will Add the records in table
	 */
	public void addProduct() {

		try {
			EcommerceConstants.TRANSACTION = getTransaction();
			setProductDetails("Milk", "HEB", "3.99", "50");
			setProductDetails("Water", "HEB", "2.99", "70");
			setProductDetails("Sugar", "", "5.99", "20");
			setProductDetails("Tea", "", "4.99", "30");
			EcommerceConstants.TRANSACTION.commit();
		} catch (HibernateException e) {
			System.out.println("Caught Exception");
			EcommerceConstants.TRANSACTION.rollback();
			e.printStackTrace();
		} finally {
			closeSession();
		}

	}

	/**
	 * This method will the read records from table
	 */
	public void getProduct() {

		EcommerceConstants.TRANSACTION = getTransaction();
		Query hqlQuery = EcommerceConstants.SESSION.createQuery("from Item");
		List<Product> items = hqlQuery.list();
		for (Product item : items) {
			System.out.println("Product Id : '" + item.getProductId() + "', Product Name : '" + item.getProductName()
					+ "', Price : '" + item.getSellingPrice() + "', Manufacturer : '" + item.getManufacturer());
		}
		closeSession();
	}

	/**
	 * This method will update the records of table
	 */
	public void updateProduct() {

		try {
			EcommerceConstants.TRANSACTION = getTransaction();

			idList = doSqlQuery("Milk");
			for (Integer id : idList) {
				item = EcommerceConstants.SESSION.get(Product.class, id);
				item.setSellingPrice(new BigDecimal("7.99"));
				EcommerceConstants.SESSION.saveOrUpdate(item);
			}
			EcommerceConstants.TRANSACTION.commit();

			System.out.println("Price Updated Succesfully, now it : '" + item.getSellingPrice() + "$'");
		} catch (HibernateException e) {
			System.out.println("Caught Exception");
			EcommerceConstants.TRANSACTION.rollback();
			e.printStackTrace();
		} finally {
			closeSession();
		}
	}

	/**
	 * This method will Delete the records from table
	 */
	public void deleteProduct() {

		EcommerceConstants.TRANSACTION = getTransaction();

		// TODO
		// Needs to check session here and everywhere whether its coming and
		// singleton or not
		idList = doSqlQuery("Water");
		for (Integer id : idList) {
			Product item = EcommerceConstants.SESSION.load(Product.class, id);
			EcommerceConstants.SESSION.delete(item);
			System.out.println("Record Deleted Succesfully");
		}
		closeSession();
	}

	private List<Integer> doSqlQuery(String productName) {

		String sql = "SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NAME = '%productName%'";
		System.out.println("   ********* " + sql);
		SQLQuery sqlQuery = EcommerceConstants.SESSION.createSQLQuery(sql);
		List<Integer> idList = sqlQuery.list();
		return idList;

	}

	private void setProductDetails(String ProdName, String Mfg, String SellingPrice, String Stock) {

		System.out.println(" Adding Items in Product Table");

		item.setProductName(ProdName);
		item.setManufacturer(Mfg);
		item.setSellingPrice(new BigDecimal(SellingPrice));
		item.setStock(Stock);
		itemId.add((Integer) EcommerceConstants.SESSION.save(item));

		System.out.println("Records Inserted successfully" + itemId);

	}
}
