package cn.com.yusys.SpringCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class orderConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(orderConsulMain80.class,args);
    }
}
