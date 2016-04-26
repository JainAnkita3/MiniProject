package com.test.ecommerce.util;

import static com.test.ecommerce.constants.EcommerceConstants.DB_URL;
import static com.test.ecommerce.constants.EcommerceConstants.JDBC_DRIVER;
import static com.test.ecommerce.constants.EcommerceConstants.PASSWORD;
import static com.test.ecommerce.constants.EcommerceConstants.USER_NAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlJDBConn {

	private static Connection conn;

	private Connection createConn() throws ClassNotFoundException, SQLException {

		// STEP 1: Register JDBC driver
		Class.forName(JDBC_DRIVER);

		// STEP 2: Create a connection
		conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		if (conn != null)

		{
			System.out.println("Connected to 'ankita' database...");
		}

		return conn;

	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		System.out.println("Getting mysql DB connection");
		conn = createConn();
		return conn;
	}
}
