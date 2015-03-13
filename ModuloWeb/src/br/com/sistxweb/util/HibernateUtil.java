package br.com.sistxweb.util;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory(){
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
		} catch (Throwable e){
			System.out.println("Criação Inicial do SessionFactory falhou. Erro:" + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
}
