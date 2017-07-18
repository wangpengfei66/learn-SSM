package com.kaishengit;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
@MapperScan("com.kaishengit.mapper")
public class Application {

	@Autowired
	Environment environment;
	
	//配置数据库连接池
	@Bean
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
		return basicDataSource;
	}
	//事务管理器
	@Bean
	public DataSourceTransactionManager transactionManager () {
		return new DataSourceTransactionManager(dataSource());
	}
	
	//SqlSessionFactory
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setTypeAliasesPackage("com.kaishengit.entity");
		
		//加载mapper配置文件
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath:mapper/*.xml");
		factory.setMapperLocations(resources);
		//设置下划线转驼峰命名法
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		config.setMapUnderscoreToCamelCase(true);
		factory.setConfiguration(config);
		
		//设置分页插件
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties pro = new Properties();
		pro.setProperty("helperDialect", "mysql");
		pageInterceptor.setProperties(pro);
		factory.setPlugins(new Interceptor[]{pageInterceptor});
		
		return factory;
	}
	
	
	
	
	
}