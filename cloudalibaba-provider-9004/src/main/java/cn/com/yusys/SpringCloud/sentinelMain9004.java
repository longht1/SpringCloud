package cn.com.yusys.SpringCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class sentinelMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(sentinelMain9004.class,args);
    }
}
