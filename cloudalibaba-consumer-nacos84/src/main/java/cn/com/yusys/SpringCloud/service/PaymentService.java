package cn.com.yusys.SpringCloud.service;


import cn.com.yusys.SpringCloud.entities.CommonResult;
import cn.com.yusys.SpringCloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 指定fallback表示Feign的服务降级处理类，PaymentFallbackService实现这个接口，在对应的方法里，编写服务降级代码
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping(value = "/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
