package com.yixiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yixiang"})
@MapperScan({"com.yixiang.data.mapper","com.yixiang.task.mapper" })
@EnableSwagger2
public class RoseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RoseApplication.class,args);
    }
}
