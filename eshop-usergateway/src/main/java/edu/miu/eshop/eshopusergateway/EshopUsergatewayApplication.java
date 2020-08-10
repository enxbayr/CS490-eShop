package edu.miu.eshop.eshopusergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class EshopUsergatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopUsergatewayApplication.class, args);
    }

}
