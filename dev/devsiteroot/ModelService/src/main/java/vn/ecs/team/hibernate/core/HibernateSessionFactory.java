package vn.ecs.team.hibernate.core;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HibernateSessionFactory {
	private static Logger log = LoggerFactory.getLogger(HibernateSessionFactory.class);
	private static final ThreadLocal threadLocal = new ThreadLocal();
	private static org.hibernate.SessionFactory sessionFactory;

	private static Configuration configuration = new Configuration();
	private static StandardServiceRegistry registry;

	static {
		try {
			configuration.configure();
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures
																								// settings
																								// from
																								// hibernate.cfg.xml
					.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			log.error("Error Creating SessionFactory", e);
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	private HibernateSessionFactory() {
	}

	public static Session getSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;

			threadLocal.set(session);
		}
		return session;
	}

	public static void rebuildSessionFactory() {

		try {
			configuration.configure();
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures
																								// settings
																								// from
																								// hibernate.cfg.xml
					.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			log.error("Error Creating SessionFactory", e);
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.flush();
			session.close();

		}
	}

	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Configuration getConfiguration() {
		return configuration;
	}
}
