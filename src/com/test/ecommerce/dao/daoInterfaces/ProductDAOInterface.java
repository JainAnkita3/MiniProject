package com.test.ecommerce.dao.daoInterfaces;

import com.test.ecommerce.output.entities.Product;

public interface ProductDAOInterface {

	public abstract void addProduct();

	public abstract void getProduct();

	public abstract void getProduct(Product productId);

	public abstract void updateProduct(Product productId);

	public abstract void deleteProduct(Product productId);

}
