package org.fade.demo.springframework.transaction;

import cn.hutool.setting.dialect.Props;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 配置
 *
 * @author fade
 * @date 2021/12/23
 */
@ComponentScan(basePackages = {"org.fade.demo.springframework.transaction"})
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
public class PropagationConfig {

	@Bean
	public TransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

	@Bean
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		Props props = new Props("org/fade/demo/springframework/transaction/database.properties");
		String url = props.getStr("mysql.url");
		String username = props.getStr("mysql.username");
		String password = props.getStr("mysql.password");
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setInitialSize(1);
		druidDataSource.setMaxActive(300);
		druidDataSource.setMinIdle(1);
		return druidDataSource;
	}

	@Bean
	public HibernateProperties hibernateProperties() {
		HibernateProperties hibernateProperties = new HibernateProperties();
		hibernateProperties.setDdlAuto("update");
		return hibernateProperties;
	}

	@Bean
	public JpaProperties jpaProperties() {
		JpaProperties jpaProperties = new JpaProperties();
		jpaProperties.setShowSql(true);
		return jpaProperties;
	}

}
