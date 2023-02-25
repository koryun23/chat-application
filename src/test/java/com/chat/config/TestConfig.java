package com.chat.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = {
        "com.chat.repository"
})
@Configuration
public class TestConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.chat.entity");

        return entityManagerFactoryBean;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();

        dataSourceConfig.setJdbcUrl("jdbc:postgresql://localhost:6432/postgres");
        dataSourceConfig.setUsername("postgres");
        dataSourceConfig.setPassword("");

        return new HikariDataSource(dataSourceConfig);
    }
}
