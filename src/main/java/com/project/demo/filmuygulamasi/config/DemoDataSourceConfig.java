package com.project.demo.filmuygulamasi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages={"${spring.data.jpa.repository.packages}"})
public class DemoDataSourceConfig {

//	@Autowired
//	Environment env;

	@Primary
	@Bean
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql://filmuygulamasi.cmtlj9pa3l69.eu-west-3.rds.amazonaws.com:3306/film_uygulamasi?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC");
		dataSourceBuilder.username("filmuygulamasi");
		dataSourceBuilder.password("filmuygulamasi");
		return dataSourceBuilder.build();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.data.jpa.entity")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource appDataSource) {
		return builder
				.dataSource(appDataSource)
				.build();
	}

	@Bean
	@ConfigurationProperties(prefix="security.datasource")
	public DataSource securityDataSource() {
		return DataSourceBuilder.create().build();
	}
}
