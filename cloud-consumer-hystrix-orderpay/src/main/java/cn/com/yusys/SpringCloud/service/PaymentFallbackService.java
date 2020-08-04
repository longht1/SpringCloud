package cn.com.yusys.SpringCloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(long id) {
        return  "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }
    @Override
    public String paymentInfo_Timeout(long id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
