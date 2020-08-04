package cn.com.yusys.SpringCloud.service;

import cn.com.yusys.SpringCloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    public int create(Payment payment);

    public  Payment getPaymentById(@Param("id") Long id);
}
