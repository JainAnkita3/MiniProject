package com.test.ecommerce.constants;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EcommerceConstants {

	public static Transaction TRANSACTION;
	public static SessionFactory SESSIONFACTORY;
	public static Session SESSION = null;

	public static final File CFGFILE = new File(
			"/Users/rinkeshshah/Documents/Ankee/workspace/MiniProject/src/com/test/ecommerce/resources/hibernate.cfg.xml");

	// JDBC driver name and database URL
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/ankita";

	// Database credentials
	public static final String USER_NAME = "test";
	public static final String PASSWORD = "welcome";

	public static final String RECORD_INSERTED_SUCCESSFULLY = "Record Inserted successfully....";

	public static final String RECORD_DELETED_SUCCESSFULLY = "Record Deleted successfully....";

	public static final String RECORD_UPDATED_SUCCESSFULLY = "Record Updated successfully....";

	public static final String ADDING_RECORDS_IN_TABLE = "....Adding Records in Table....";

	public static final String CAUGHT_EXCEPTION = "Caught Exception....";

}
