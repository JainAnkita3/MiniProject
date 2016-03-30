package com.test.ecommerce.util;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EcommerceUtil {

	private static File cfgFile = new File(
			"/Users/rinkeshshah/Documents/Ankee/workspace/MiniProject/src/com/test/ecommerce/resources/hibernate.cfg.xml");
	private static SessionFactory sFactory;
	private static Session session;
	private static Transaction txn;

	/*protected EcommerceUtil() {
		System.out.println("EcommerceUtil Constructor");
	}*/

	public synchronized static Transaction getTransaction() {
		if (session == null) {
			System.out.println("No Session is open, so creating the new one");
			try {
				sFactory = new Configuration().configure(cfgFile).buildSessionFactory();
				session = createSession(sFactory);
				txn = session.beginTransaction();
			} catch (Exception e) {
				System.err.println("Transaction creation failed." + e);
				e.printStackTrace();
			}
		}
		System.out.println("Transaction created successfully");

		return txn;
	}

	public static Session createSession(SessionFactory sFactory) {
		session = sFactory.openSession();
		System.out.println("Session created successfully");
		return session;

	}

	public static void closeSession() {
		System.out.println("Session Closing");
		txn.commit();
		session.close();
	}

	/*
	 * public static void main(String[] args) { System.out.println("Main");
	 * //EcommerceDAO e = new EcommerceDAO();
	 * 
	 * }
	 */

}
