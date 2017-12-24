package com.epam.university.spring.enote.config;

//import com.epam.university.spring.enote.util.JpaUtil;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.epam.university.spring.enote.util.JpaUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.epam.**")
@EnableJpaRepositories("com.epam.university.spring.enote.repository")
@EnableTransactionManagement
@ImportResource({"classpath:aspect-conf.xml", "classpath:spring/spring-cache.xml"})

public class AppConfig {

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        bean.setDatabase(Database.H2);
        bean.setGenerateDdl(true);
        return bean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan("com.epam");
        bean.setJpaProperties(hibernateProperties());
        return bean;
    }

    Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
        properties.put(org.hibernate.cfg.Environment.USE_SQL_COMMENTS, "true");
        properties.put(org.hibernate.cfg.Environment.FORMAT_SQL, "true");
        properties.put(org.hibernate.cfg.Environment.CACHE_REGION_FACTORY,
                "org.hibernate.cache.jcache.JCacheRegionFactory");
        properties.put(org.hibernate.cache.jcache.JCacheRegionFactory.PROVIDER,
                "org.ehcache.jsr107.EhcacheCachingProvider");
        properties.put(org.hibernate.cfg.AvailableSettings.USE_SECOND_LEVEL_CACHE, "true");
        properties.put(org.hibernate.cfg.AvailableSettings.USE_QUERY_CACHE, "false");
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaUtil jpaUtil() {
        return new JpaUtil();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("db/initDB.sql"));
        databasePopulator.addScript(new ClassPathResource("db/populateDB.sql"));
        return databasePopulator;
    }

    private SimpleDriverDataSource createDataSource() {
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
        simpleDriverDataSource.setUrl("jdbc:h2:mem:test;LOCK_MODE=1;DB_CLOSE_DELAY=-1;" +
                "ACCESS_MODE_DATA=rws;PAGE_SIZE=4");
        simpleDriverDataSource.setUsername("");
        simpleDriverDataSource.setPassword("");
        return simpleDriverDataSource;
    }
}