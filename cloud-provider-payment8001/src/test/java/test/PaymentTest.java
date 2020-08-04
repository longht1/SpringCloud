package test;


import cn.com.yusys.SpringCloud.PaymentMain8001;
import cn.com.yusys.SpringCloud.dao.PaymentDao;
import cn.com.yusys.SpringCloud.entities.Payment;
import cn.com.yusys.SpringCloud.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootTest(classes= PaymentMain8001.class)
public class PaymentTest {

    @Autowired
    DataSource dataSource;
    @Resource
    PaymentService paymentService;
    @Test
    public  void  Test1(){
        Payment payment =paymentService.getPaymentById((long) 31);
        System.out.println(payment);
    }
    @Test
    public  void  Test2() throws SQLException {
        Connection connection=dataSource.getConnection();
        System.out.println(connection);
    }
    @Test
    public  void  Test3(){
       Payment payment =new Payment();
       payment.setId(1);
       payment.setSerial("hh");
       paymentService.create(payment);
    }
}
