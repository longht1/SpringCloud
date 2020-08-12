package cn.com.yusys.SpringCloud.controller;

import cn.com.yusys.SpringCloud.domian.CommonResult;
import cn.com.yusys.SpringCloud.domian.Order;
import cn.com.yusys.SpringCloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

}
