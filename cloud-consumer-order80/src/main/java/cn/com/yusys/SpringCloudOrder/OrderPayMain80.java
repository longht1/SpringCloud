package cn.com.yusys.SpringCloudOrder;

import cn.com.yusys.SpringCloudOrder.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PROVIDER-SERVICE",configuration = MySelfRule.class)
public class OrderPayMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderPayMain80.class,args);
    }
}
