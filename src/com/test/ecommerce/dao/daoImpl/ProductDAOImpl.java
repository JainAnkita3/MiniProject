package com.test.ecommerce.dao.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.test.ecommerce.dao.daoInterfaces.ProductDAOInterface;
import com.test.ecommerce.output.entities.Product;
import com.test.ecommerce.util.EcommerceUtil;

public class ProductDAOImpl extends EcommerceUtil implements ProductDAOInterface {

	final Logger logger = Logger.getLogger(ProductDAOImpl.class);

	private List<Integer> idList = new ArrayList<>();
	private List<Integer> itemId = new ArrayList<Integer>();
	private Product item;

	public ProductDAOImpl() {
		System.out.println("ProductDAOImpl Construtor");
		logger.info("ProductDAO");

	}

	/**
	 * This method invokes setProductDetails and then saves the records in
	 * Product table
	 */
	public Product addProduct(String prodNm, String mfg, String sp, String stock) {
		item = new Product();
		System.out.println(" Adding Items in Product Table");
		item.setProductName(prodNm);
		item.setManufacturer(mfg);
		item.setSellingPrice(new BigDecimal(sp));
		item.setStock(stock);
		return item;
	}

	/**
	 * This method will the read records from table
	 */
	public void getProduct() {

		createTransaction(getSession());
		Query hqlQuery = getSession().createQuery("from Item");
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
	public void updateProduct(Product productId) {

		try {
			createTransaction(getSession());

			item = getSession().get(Product.class, productId);
			item.setSellingPrice(new BigDecimal("7.99"));
			getSession().saveOrUpdate(item);

			System.out.println("Price Updated Succesfully, now it : '" + item.getSellingPrice() + "$'");

		} catch (HibernateException e) {
			System.out.println("Caught Exception");
			txnRollback();
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
			System.out.println("Record Deleted Succesfully");
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
		System.out.println("   ********* " + sql);
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		List<Integer> idList = sqlQuery.list();
		return idList;

	}

}
