package com.test.ecommerce.util;

import static com.test.ecommerce.constants.EcommerceConstants.DB_URL;
import static com.test.ecommerce.constants.EcommerceConstants.JDBC_DRIVER;
import static com.test.ecommerce.constants.EcommerceConstants.PASSWORD;
import static com.test.ecommerce.constants.EcommerceConstants.USER_NAME;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlJDBConn {

	private static Connection conn;

	/*
	 * This will create & return a JDBC connection
	 */
	private static Connection createConn() throws ClassNotFoundException, SQLException {

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

	/*
	 * This is responsible for DB cleaning i.e. dropping all tables. 
	 */
	public void resetDatabase() throws SQLException {
		String s = new String();
		StringBuffer sb = new StringBuffer();

		try {
			FileReader fr = new FileReader(new File("/Users/rinkeshshah/Documents/Ankee/workspace/MiniProject/src/com/test/ecommerce/sql/Clean_DB.sql"));
			// be sure to not have line starting with "--" or "/*" or any other
			// non alphabetical character

			BufferedReader br = new BufferedReader(fr);

			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();

			// here is our splitter ! We use ";" as a delimiter for each request
			// then we are sure to have well formed statements
			String[] inst = sb.toString().split(";");

			Connection c = MySqlJDBConn.createConn();
			Statement st = c.createStatement();

			for (int i = 0; i < inst.length; i++) {
				// we ensure that there is no spaces before or after the request
				// string
				// in order to not execute empty statements
				if (!inst[i].trim().equals("")) {
					st.executeUpdate(inst[i]);
					System.out.println(">>" + inst[i]);
				}
			}

		} catch (Exception e) {
			System.out.println("*** Error : " + e.toString());
			System.out.println("*** ");
			System.out.println("*** Error : ");
			e.printStackTrace();
			System.out.println("################################################");
			System.out.println(sb.toString());
		}
	}
}
