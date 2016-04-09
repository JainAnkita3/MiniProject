package com.test.ecommerce.dao.daoInterfaces;

import com.test.ecommerce.output.entities.Product;

public interface ProductDAOInterface {

	public abstract Product addProduct(String prodNm, String mfg, String sp, String stock);

	public abstract void getProduct();

	// public abstract void getProduct(Product productId);

	// public abstract void updateProduct(Product productId);

	// public abstract void deleteProduct(Product productId);

}
