package com.ku.business;

import com.ku.business.entity.Company;
import com.ku.business.entity.Content;
import com.ku.business.entity.Service;
import com.ku.business.entity.Storage;
import com.ku.business.entity.Detail;
import com.ku.business.entity.Document;
import com.ku.business.entity.Order;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    @Bean
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "postgres");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Company.class);
                configuration.addAnnotatedClass(Content.class);
                configuration.addAnnotatedClass(Detail.class);
                configuration.addAnnotatedClass(Document.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Service.class);
                configuration.addAnnotatedClass(Storage.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
