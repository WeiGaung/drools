package com.wenba;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

//jetcache
/*@SpringBootApplication
@EnableMethodCache(basePackages = "com.wenba.service.drools")
@EnableCreateCacheAnnotation*/

@SpringBootApplication
@MapperScan("com.wenba.reposity.*")
@Slf4j
@EnableDiscoveryClient
//  @Scheduled
//@EnableScheduling
@ImportResource({"classpath:urule-console-context.xml"})
public class ExampleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
        log.info("Example api start successful.");

    }

}

