package cn.com.yusys.SpringCloud.controller;

import cn.com.yusys.SpringCloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
//@DefaultProperties(defaultFallback = "paymentTimeOutFallBack")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") long id){
        return paymentHystrixService.paymentInfo_OK(id);
    }


//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod",commandProperties = {
////            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
////    })
    //@HystrixCommand
    @GetMapping("/consumer/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") long id){
        return paymentHystrixService.paymentInfo_Timeout(id);
    }

    public String paymentTimeOutFallBackMethod(@PathVariable("id") long id){
        return "我是消费者80，对方支付系统繁忙，请稍后再试，o(╥﹏╥)o";
    }

    public String paymentTimeOutFallBack(){
        return "1111111111111111，o(╥﹏╥)o";
    }
}
