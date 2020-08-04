package cn.com.yusys.SpringCloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)  //指定调用哪个微服务
public interface PaymentHystrixService {
    @GetMapping(value = "/payment/hystrix/ok/{id}")    //哪个地址
    public String paymentInfo_OK(@PathVariable("id")long id);
    @GetMapping(value = "/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id")long id);
}
