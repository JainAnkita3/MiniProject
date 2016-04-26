package com.test.ecommerce.util;

import static com.test.ecommerce.constants.EcommerceConstants.CFGFILE;
import static com.test.ecommerce.constants.EcommerceConstants.SESSION;
import static com.test.ecommerce.constants.EcommerceConstants.SESSIONFACTORY;
import static com.test.ecommerce.constants.EcommerceConstants.TRANSACTION;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * This Class will have Hibernate Session related methods.
 * 
 * @author ank
 *
 */
public class EcommerceUtil {

	final static Logger logger = Logger.getLogger(EcommerceUtil.class);

	/**
	 * This method will create a Session if doesn't exist already
	 * 
	 * @return
	 */
	public synchronized static Session getSession() {
		if (SESSION == null || !SESSION.isOpen()) {
			System.out.println("No Session is open, so creating the new one");
			try {
				SESSIONFACTORY = new Configuration().configure(CFGFILE).buildSessionFactory();
				SESSION = createSession(SESSIONFACTORY);
			} catch (Exception e) {
				System.err.println("Session creation failed." + e);
				e.printStackTrace();
			}
			// System.out.println("Session created successfully");

		}

		return SESSION;
	}

	/**
	 * Not in use, not sure how to use regarding multiple places This method
	 * will create a Transaction if doesn't exist already
	 * 
	 * @return
	 */
	public synchronized static Transaction getTransaction() {
		if (SESSION == null) {
			System.out.println("No Session is open, so creating the new one");
			try {
				SESSIONFACTORY = new Configuration().configure(CFGFILE).buildSessionFactory();
				SESSION = createSession(SESSIONFACTORY);
				TRANSACTION = SESSION.beginTransaction();
				System.out.println("Txn created");
			} catch (Exception e) {
				System.err.println("Transaction creation failed." + e);
				e.printStackTrace();
			}
		}
		System.out.println("Transaction created successfully");

		return TRANSACTION;
	}

	/**
	 * This method will create the session for getSession method
	 * 
	 * @param sFactory
	 * @return
	 */
	private static Session createSession(SessionFactory sFactory) {
		SESSION = SESSIONFACTORY.openSession();
		System.out.println("**Session created successfully**");
		return SESSION;

	}

	/**
	 * This method will create the transaction
	 * 
	 * @param session
	 * @return
	 */
	public static Transaction createTransaction(Session session) {
		TRANSACTION = session.beginTransaction();
		TRANSACTION.begin();
		System.out.println("Txn created successfully");
		return TRANSACTION;

	}

	/**
	 * This method commit the transaction, if exist and close the session if
	 * open.
	 */
	public static void closeSession() {
		System.out.println("**** Closing Transaction & Session ****");
		if (TRANSACTION != null) {
			TRANSACTION.commit();
		}
		if (SESSION.isOpen()) {
			SESSION.close();
		}
	}

	/**
	 * This method will rollback the transaction
	 */
	public static void txnRollback() {
		System.out.println("Transaction Rollback");
		TRANSACTION.rollback();
	}

}
