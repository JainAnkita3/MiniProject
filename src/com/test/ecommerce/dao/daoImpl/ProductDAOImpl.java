package com.test.ecommerce.dao.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.test.ecommerce.constants.EcommerceConstants;
import com.test.ecommerce.dao.daoInterfaces.ProductDAOInterface;
import com.test.ecommerce.output.entities.Product;
import com.test.ecommerce.util.EcommerceUtil;

public class ProductDAOImpl extends EcommerceUtil implements ProductDAOInterface {

	final Logger logger = Logger.getLogger(ProductDAOImpl.class);

	private List<Integer> idList = new ArrayList<>();
	private List<Integer> itemId = new ArrayList<Integer>();
	private Product item = new Product();

	public ProductDAOImpl() {
		logger.debug("Product Construtor");
	}

	/**
	 * This method invokes setProductDetails and then saves the records in
	 * Product table
	 */
	public void addProduct() {

		try {

			item = setProductDetails("Milk", "HEB", "3.99", "50");
			// setProductDetails("Water", "HEB", "2.99", "70");
			// setProductDetails("Sugar", "", "5.99", "20");
			// setProductDetails("Tea", "", "4.99", "30");
			getSession().save(item);
			logger.debug("save successful");
		} catch (HibernateException e) {
			logger.error("Caught Exception" + e);
			txnRollback();
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
			logger.debug("Product Id : '" + item.getProductId() + "', Product Name : '" + item.getProductName()
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

			logger.debug("Price Updated Succesfully, now it : '" + item.getSellingPrice() + "$'");
		} catch (HibernateException e) {
			logger.debug("Caught Exception");
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

		createTransaction(getSession());
		// TODO
		// Needs to check session here and everywhere whether its coming and
		// singleton or not
		idList = doSqlQuery("Water");
		for (Integer id : idList) {
			Product item = getSession().load(Product.class, id);
			getSession().delete(item);
			logger.debug("Record Deleted Succesfully");
		}
		closeSession();
	}

	/**
	 * This method queries the Product table from ProductName and returns the
	 * ProductID.
	 * 
	 * @param productName
	 * @return
	 */
	private List<Integer> doSqlQuery(String productName) {

		String sql = "SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NAME = '%productName%'";
		logger.debug("   ********* " + sql);
		SQLQuery sqlQuery = EcommerceConstants.SESSION.createSQLQuery(sql);
		List<Integer> idList = sqlQuery.list();
		return idList;

	}

	/**
	 * This method sets the data in Product table
	 * 
	 * @param ProdName
	 * @param Mfg
	 * @param SellingPrice
	 * @param Stock
	 * @return
	 */
	private Product setProductDetails(String ProdName, String Mfg, String SellingPrice, String Stock) {

		logger.debug(" Adding Items in Product Table");

		item.setProductName(ProdName);
		item.setManufacturer(Mfg);
		item.setSellingPrice(new BigDecimal(SellingPrice));
		item.setStock(Stock);

		logger.debug("Records Inserted successfully" + itemId);
		return item;

	}
}
