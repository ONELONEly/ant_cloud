package com.gree.bean;

import com.gree.util.DatabaseType;
import com.gree.util.DynamicDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.gree.dao")
public class MybatisConfig {

    @Value("${mybatis.type-aliases-package}")
    private String typeAliases;
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.master.url}")
    private String masterUrl;
    @Value("${spring.datasource.master.username}")
    private String masterUsername;
    @Value("${spring.datasource.master.password}")
    private String masterPassword;

    @Value("${spring.datasource.slave.url}")
    private String slaveUrl;
    @Value("${spring.datasource.slave.username}")
    private String slaveUsername;
    @Value("${spring.datasource.slave.password}")
    private String slavePassword;

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean
//    @Primary
    public DataSource masterDataSource(){
        return getDataSource(masterUrl, masterUsername, masterPassword);
    }

    @Bean
    public DataSource slaveDataSource(){
        return getDataSource(slaveUrl, slaveUsername, slavePassword);
    }

    private DataSource getDataSource(String slaveUrl, String slaveUsername, String slavePassword) {
        Properties props = new Properties();
        props.put("driverClassName", driverClassName);
        props.put("jdbcUrl", slaveUrl);
        props.put("username", slaveUsername);
        props.put("password", slavePassword);
        return new HikariDataSource(new HikariConfig(props));
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource DynamicdataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.master, masterDataSource);
        targetDataSources.put(DatabaseType.slave, slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(masterDataSource);// 默认的datasource设置
        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(typeAliases);// 指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));//

        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
