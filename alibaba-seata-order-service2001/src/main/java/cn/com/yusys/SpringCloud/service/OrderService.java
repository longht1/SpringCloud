package cn.com.yusys.SpringCloud.service;

import cn.com.yusys.SpringCloud.domian.Order;

public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);

}
