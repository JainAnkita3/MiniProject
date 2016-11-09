package com.test.ecommerce.dao.daoImpl;

import static com.test.ecommerce.constants.EcommerceConstants.ADDING_RECORDS_IN_TABLE;
import static com.test.ecommerce.constants.EcommerceConstants.CAUGHT_EXCEPTION;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_DELETED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_INSERTED_SUCCESSFULLY;
import static com.test.ecommerce.constants.EcommerceConstants.RECORD_UPDATED_SUCCESSFULLY;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.test.ecommerce.dao.daoInterfaces.ProductDAOInterface;
import com.test.ecommerce.output.entities.Product;
import com.test.ecommerce.util.EcommerceUtil;

public class ProductDAOImpl extends EcommerceUtil implements ProductDAOInterface {

	final Logger logger = Logger.getLogger(ProductDAOImpl.class);
	private Product item;
	private int prodId;

	/**
	 * This method will set the values of Product Table
	 */
	public Product addProduct(String prodNm, String mfg, String sp, String stock) {
		item = new Product();
		System.out.println(ADDING_RECORDS_IN_TABLE + getClass().getName());
		item.setProductName(prodNm);
		item.setManufacturer(mfg);
		item.setSellingPrice(new BigDecimal(sp));
		item.setStock(stock);
		System.out.println(RECORD_INSERTED_SUCCESSFULLY + getClass().getName().substring(31));
		return item;
	}

	/**
	 * This method will the read all the records from table
	 */
	public Product getAllProducts(Session session) {
		Query hqlQuery = session.createQuery("from Item");
		List<Product> items = hqlQuery.list();
		for (Product item : items) {
			System.out
					.println("    Product Id : '" + item.getProductId() + "', Product Name : '" + item.getProductName()
							+ "', Price : '" + item.getSellingPrice() + "', Manufacturer : '" + item.getManufacturer());
		}
		return item;
	}

	/**
	 * This method will the return record from table using product Id
	 */
	public Product getProductFromProdId(Session session, int prodId) {
		item = session.load(Product.class, prodId);
		System.out.println("Product Id : '" + item.getProductId() + "', Product Name : '" + item.getProductName()
				+ "', Price : '" + item.getSellingPrice() + "', Manufacturer : '" + item.getManufacturer());
		return item;
	}

	/**
	 * This method will the update the record of SP
	 */
	public void updateProductSP(int productId, BigDecimal newSellingPrice, Session session) {
		try {
			System.out.println("afssafasf :  :  " + productId);
			item = session.get(Product.class, productId);
			item.setSellingPrice(newSellingPrice);
			session.saveOrUpdate(item);
			System.out.println(RECORD_UPDATED_SUCCESSFULLY + " new value is : " + item.getSellingPrice() + "$'");
		} catch (HibernateException e) {
			System.out.println(CAUGHT_EXCEPTION);
			txnRollback();
			e.printStackTrace();
		}
	}

	/**
	 * This method will Delete the records from table
	 */
	public void deleteProduct(int productId, Session session) {
		System.out.println("Inside DeleteProd");
		prodId = doSqlQuery("Salt", session);
		System.out.println("ProdId :::: " + prodId);
		item = session.load(Product.class, prodId);
		System.out.println("Item  : " + item);
		session.delete(item);
		System.out.println(RECORD_DELETED_SUCCESSFULLY);
	}

	/**
	 * This method queries the Product table from ProductName and returns the
	 * ProductID.
	 * 
	 * @param productName
	 * @return
	 */
	public Integer doSqlQuery(String productName, Session session) {
		String sql = "SELECT PRODUCT_ID FROM PRODUCT p WHERE p.PRODUCT_NAME = ?";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setParameter(0, productName);
		List<Integer> idList = sqlQuery.list();
		Iterator<Integer> it = idList.iterator();
		while (it.hasNext()) {
			prodId = it.next();
		}
		return prodId;
	}

}
