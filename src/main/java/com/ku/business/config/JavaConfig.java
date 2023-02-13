package com.ku.business.config;

import com.ku.business.entity.Company;
import com.ku.business.entity.Content;
import com.ku.business.entity.Detail;
import com.ku.business.entity.Document;
import com.ku.business.entity.Order;
import com.ku.business.entity.Service;
import com.ku.business.entity.Storage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@ComponentScan("com.ku.business")
@PropertySource("application.properties")
@EnableJpaRepositories("com.ku.business.repository")
public class JavaConfig {
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.application.name}")
//    private String user;
//    @Value("${spring.application.password}")
//    private String password;
//    @Value("${spring.datasource.driverClassName}")
//    private String driver;
@Value("${spring.datasource.url}")
private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.entity-path}")
    private String entityPath;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    private static SessionFactory sessionFactory;
    @Bean
    public java.sql.Connection getConnection() throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
    @Bean
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, driver);
                settings.put(Environment.URL, url);
                settings.put(Environment.USER, user);
                settings.put(Environment.PASS, password);

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
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        entityManagerFactoryBean.setPackagesToScan(entityPath);
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
