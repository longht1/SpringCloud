package cn.com.yusys.SpringCloud.service;

public interface PaymentService {
    public String paymentInfo_OK(long id);
    public String paymentInfo_Timeout(long id);
    public String paymentCircuitBreaker(long id);
}
