package edu.miu.eshop.eshopadmingateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class EshopAdmingatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopAdmingatewayApplication.class, args);
	}

}
