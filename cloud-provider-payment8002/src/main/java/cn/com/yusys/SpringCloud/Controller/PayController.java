package cn.com.yusys.SpringCloud.Controller;

import cn.com.yusys.SpringCloud.entities.CommonResult;
import cn.com.yusys.SpringCloud.entities.Payment;
import cn.com.yusys.SpringCloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PayController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String Serverport;

    @PostMapping("/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){
        int result =paymentService.create(payment);
        log.info("插入结果集"+result);
        if(result>0){
            return new CommonResult(200,"插入成功,Serverport"+Serverport,payment);
        }else {
            return new CommonResult(444,"插入失败",null);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        System.out.println("2225555"+id);
      Payment payment =paymentService.getPaymentById(id);
        log.info("结果为"+payment);
        if(payment !=null){
            return new CommonResult(200,"查询成功Serverport"+Serverport,payment);
        }else {
            return new CommonResult(444,"查询失败",null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPort()
    {
        return Serverport;
    }
}
