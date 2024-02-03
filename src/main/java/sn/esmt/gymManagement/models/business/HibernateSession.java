package sn.esmt.gymManagement.models.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSession {
	private static SessionFactory factory = null;
	
	private static Session session = null;
	
	private static Logger logger = LogManager.getLogger(HibernateSession.class);
	
	private HibernateSession() {
        try {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

            factory = meta.getSessionFactoryBuilder().build();

        } catch (Exception exception) {
            logger.error("Unable to load Hibernate configuration : " + exception.getMessage());
        }
    }
	
	// create hibernate's SessionFactory object
	public static Session getSession () {
        if (session == null) {
            factory = getSessionFactory ();
            session = factory.openSession();
        }
        return session;
    }
	
	private static SessionFactory getSessionFactory() {
        init();
        return factory;
    }

    public static void init () {
        if (factory == null ) {
            new HibernateSession();
            logger.info("HibernateSession created.");
        }
    }
    
    public static void closeSession() {
        if (session != null) {
            session.close();
            session = null;
        }
    }

    public static void closeSessionFactory() {
        if ( factory != null ) {
            closeSession ();
            factory.close();
            factory = null;
        }
    }
}
