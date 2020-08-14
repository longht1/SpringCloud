package cn.com.yusys.SpringCloud.service;

import cn.com.yusys.SpringCloud.entities.CommonResult;
import cn.com.yusys.SpringCloud.entities.Payment;
import org.springframework.stereotype.Component;


@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回-PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
