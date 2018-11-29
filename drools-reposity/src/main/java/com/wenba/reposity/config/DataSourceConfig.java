package com.wenba.reposity.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Getter
@Setter
@Configuration
@EnableTransactionManagement
public class DataSourceConfig implements TransactionManagementConfigurer {

    @Value("${spring.datasource.type}")
//    private Class<? extends DataSource> dataSourceType = com.alibaba.druid.pool.DruidDataSource.class;
    private Class<? extends DataSource> dataSourceType;

    private String mapperLocation = "classpath:mapper/*.xml";
    private String domainPackage = "com.wenba.domain";

//    @Autowired
//    DataSource dataSource;

    @Primary
    @Bean(name = "wenba-master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource getWenBaMaster() {
        return DataSourceBuilder.create().type(this.dataSourceType).build();
    }

//    @Bean(name = "wenba-slave")
//    @ConfigurationProperties(prefix = "spring.datasource.slave")
//    public DataSource getWenBaSlaveOne() {
//        return DataSourceBuilder.create().type(this.dataSourceType).build();
//    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(getWenBaMaster());
        bean.setTypeAliasesPackage(domainPackage);


        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(mapperLocation));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(getWenBaMaster());
    }
}
