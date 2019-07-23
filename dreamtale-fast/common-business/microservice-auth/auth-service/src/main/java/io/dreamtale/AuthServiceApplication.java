package io.dreamtale;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import io.dreamtale.datasources.DynamicDataSourceConfig;

@SpringBootApplication(scanBasePackages = "io.dreamtale")
@EnableDiscoveryClient
@Import({DynamicDataSourceConfig.class})
@MapperScan({"io.dreamtale.dao"})
@EnableDubbo(scanBasePackages = "io.dreamtale")
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}

