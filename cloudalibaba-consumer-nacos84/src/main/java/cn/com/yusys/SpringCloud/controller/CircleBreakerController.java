package cn.com.yusys.SpringCloud.controller;


import cn.com.yusys.SpringCloud.entities.CommonResult;
import cn.com.yusys.SpringCloud.entities.Payment;
import cn.com.yusys.SpringCloud.service.PaymentService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {
    // nacos-payment-provider就是9003和9004微服务的spring.application.name的值
    public static final String SERVICE_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
   // @SentinelResource(value = "fallback")//未添加任何配置
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")//添加运行Java代码出错时的兜底方法
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler")//添加熔断兜底函数
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgument，非法参数异常...");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException，该ID没有对应记录，空指针异常");
        }
        return result;
    }


    public CommonResult handlerFallback(@PathVariable Long id, Throwable throwable) {
        Payment payment = new Payment(id, null);
        return new CommonResult(444, "异常fallback方法，异常内容是：" + throwable.getMessage(), payment);
    }

    //openfeign的控制层代码

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }
}