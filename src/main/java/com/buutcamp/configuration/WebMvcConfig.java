package com.buutcamp.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan("com.buutcamp")
@EnableWebMvc
@EnableTransactionManagement
@PropertySources({@PropertySource("classpath:webconfig.properties"), @PropertySource("classpath:hibernate.properties")})
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${prefix}")
    private String prefix;

    @Value("${suffix}")
    private String suffix;

    @Value("${database}")
    private String database;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Value("${timezone}")
    private String timezone;

    @Value("${dialect}")
    private String dialect;

    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setPrefix(prefix);
        urlBasedViewResolver.setSuffix(suffix);
        urlBasedViewResolver.setViewClass(JstlView.class);

        return urlBasedViewResolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {

        //Initialize sessionfactorybean
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        //set sessionfactory data source
        sessionFactory.setDataSource(myDataSource());
        //tell hibernate where to find classes to manage
        sessionFactory.setPackagesToScan("com.buutcamp.entity");
        //set hibernate proterties
        sessionFactory.setHibernateProperties(hibernateProperties());
        //return sessionfactory
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory ) {

        HibernateTransactionManager txManager =
                new HibernateTransactionManager();

        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    DataSource myDataSource() throws SQLException {

        MysqlDataSource dataSource = new MysqlDataSource();
        //Set connection info
        dataSource.setDatabaseName(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setServerTimezone(timezone);

        return dataSource;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                // let hibernate create and manage tables
                setProperty("hibernate.hbm2ddl.auto", "update");

                // have hibernate show SQL commands it writes
                setProperty("hibernate.show_sql", "true");

                // depending on database, this needs to be set to correct dialect
                setProperty("hibernate.dialect", dialect);
            }
        };
    }
}
