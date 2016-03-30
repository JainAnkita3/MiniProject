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

}
