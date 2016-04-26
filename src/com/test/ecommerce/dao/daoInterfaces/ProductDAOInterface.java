package com.test.ecommerce.dao.daoInterfaces;

import java.math.BigDecimal;

import org.hibernate.Session;

import com.test.ecommerce.output.entities.Product;

public interface ProductDAOInterface {

	public abstract Product addProduct(String prodNm, String mfg, String sp, String stock);

	public abstract Product getProductFromProdId(Session session, int prodId);

	public abstract Product getAllProducts(Session session);

	public abstract void updateProductSP(int productId, BigDecimal sellingPrice, Session session);

	public abstract void deleteProduct(int productId, Session session);

}
