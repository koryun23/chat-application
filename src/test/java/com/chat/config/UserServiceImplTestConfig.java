package com.chat.config;

import com.chat.mapper.core.user.UserCreationParamsToUserMapper;
import com.chat.mapper.impl.user.UserCreationParamsToUserMapperImpl;
import com.chat.repository.UserRepository;
import com.chat.service.core.user.UserService;
import com.chat.service.impl.user.UserServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@EnableJpaRepositories(basePackages = {
        "com.chat.repository"
})
@EnableTransactionManagement
@Configuration
public class UserServiceImplTestConfig {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserCreationParamsToUserMapper userCreationParamsToUserMapper) {
        return new UserServiceImpl(userRepository, passwordEncoder, userCreationParamsToUserMapper);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserCreationParamsToUserMapper userCreationParamsToUserMapper(PasswordEncoder passwordEncoder) {
        return new UserCreationParamsToUserMapperImpl(passwordEncoder);
    }
}
