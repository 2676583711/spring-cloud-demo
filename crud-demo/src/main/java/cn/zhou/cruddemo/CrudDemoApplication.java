package cn.zhou.cruddemo;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

//@MapperScan("cn.zhou.cruddemo.dao") //扫描的mapper
@EnableEurekaClient
@SpringBootApplication
public class CrudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudDemoApplication.class, args);

        System.out.println("---------------CrudDemoApplication is start success------------------");
    }

}
