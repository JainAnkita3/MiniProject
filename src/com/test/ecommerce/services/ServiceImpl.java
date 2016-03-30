package com.test.ecommerce.services;

import java.math.BigDecimal;

import com.test.ecommerce.dao.daoImpl.ProductDAOImpl;
import com.test.ecommerce.output.entities.Cart;
import com.test.ecommerce.output.entities.Orders;

public class ServiceImpl {

	public static void main(String[] args) {

		ProductDAOImpl prod = new ProductDAOImpl();

		Orders order = new Orders(101, 1001, new java.util.Date(), "5", new BigDecimal("2.99"), new BigDecimal("14.95"),
				"PROCESS", "AIRWY111");

		Cart cart = new Cart();

	}

}
