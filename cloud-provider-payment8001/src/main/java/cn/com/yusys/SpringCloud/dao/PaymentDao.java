package cn.com.yusys.SpringCloud.dao;

import cn.com.yusys.SpringCloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper//于mybatis对应
public interface PaymentDao {
   public int create(Payment payment);

   public  Payment getPaymentById(@Param("id") Long id);
}

