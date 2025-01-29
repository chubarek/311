package web.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class AppConfig {

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hibernate");
        dataSource.setUsername("root");
        dataSource.setPassword("root1111");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan("web.model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getJpaProperties());

        return em;
    }

    @Bean
    public Properties getJpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.connection.charSet", "UTF-8");
        return jpaProperties;
    }

    @Bean
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory em) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(em);
        return transactionManager;
    }
}
