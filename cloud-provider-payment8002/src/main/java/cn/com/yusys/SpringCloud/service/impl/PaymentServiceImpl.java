package cn.com.yusys.SpringCloud.service.impl;

import cn.com.yusys.SpringCloud.dao.PaymentDao;
import cn.com.yusys.SpringCloud.entities.Payment;
import cn.com.yusys.SpringCloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        System.out.println("Service"+id);
        return paymentDao.getPaymentById(id);
    }
}
