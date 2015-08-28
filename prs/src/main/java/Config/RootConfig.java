/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This is where middle tier and data beans/components are configured/created(beans)
 * This is in contrast to the 
 * @author Stuart
 */
@Configuration
@ComponentScan( basePackages = {"Website.Controllers, BOL, Webflow.Controllers, BOLO.Validators, Website.Initialisation, Config"},
        excludeFilters = {
            @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
public class RootConfig {
  @Bean
  public BOL.Interfaces.IUser userBOL()
  {
    return new BOL.User();
  }
    
  @Bean
  public DAL.Interfaces.IUserDAO userDAO()
  {
    return new DAL.UserDAO();
  }
  
  
  
  @Bean
  public BOLO.R3GlobalConfig r3config()
  {
    BOLO.R3GlobalConfig global = new BOLO.R3GlobalConfig();
    
    return global;    
  }
  
   @Bean   
   public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory);
 
      return txManager;
   }
  
   Properties hibernateProperties() {
      return new Properties() {
         {
            setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
            setProperty("hibernate.show_sql", "true");
            setProperty("hibernate.c3p0.min_size", "5");
            setProperty("hibernate.c3p0.max_size", "20");
            setProperty("hibernate.c3p0.timeout", "300");
            setProperty("hibernate.c3p0.max_statements", "50");
            setProperty("hibernate.c3p0.idle_test_period", "3000");
            setProperty("hibernate.hbm2ddl.auto", "create");
         }
      };
   }
  
   
  @Bean 
  public org.springframework.jdbc.datasource.DriverManagerDataSource myDataSource()
  {
    DriverManagerDataSource myDataSource = new DriverManagerDataSource();
    myDataSource.setDriverClassName("org.hsqldb.jdbcDriver");
    myDataSource.setUrl("jdbc:hsqldb:mem:r3");
    myDataSource.setUsername("sa");
    myDataSource.setPassword("");
    return myDataSource;    
  }
  
    
  @Bean
  public LocalSessionFactoryBean sessionFactory(DataSource myDataSource)
  {    
    
   LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
   sessionFactory.setDataSource(myDataSource);
   Properties properties = new Properties();    
   
   properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
   properties.put("hibernate.show_sql", "true");
   properties.put("hibernate.c3p0.min_size", "5");
   properties.put("hibernate.c3p0.max_size", "20");
   properties.put("hibernate.c3p0.timeout", "300");
   properties.put("hibernate.c3p0.max_statements", "50");
   properties.put("hibernate.c3p0.idle_test_period", "3000");
   properties.put("hibernate.hbm2ddl.auto", "create");
   sessionFactory.setMappingResources( new String[] {
     "DEL/User.hbm.xml"
   });
   sessionFactory.setHibernateProperties(properties);   
   
   return sessionFactory;
  }
    
  @Bean public HibernateExceptionTranslator hibernateExceptionTranslator()
  {
    return new HibernateExceptionTranslator();
  }
  
  @Bean 
  public BeanPostProcessor persistenceTranslation(){    
    return new PersistenceExceptionTranslationPostProcessor();
  }
}
