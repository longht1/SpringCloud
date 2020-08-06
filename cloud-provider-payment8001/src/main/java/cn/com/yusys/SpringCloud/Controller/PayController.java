package cn.com.yusys.SpringCloud.Controller;

import cn.com.yusys.SpringCloud.entities.CommonResult;
import cn.com.yusys.SpringCloud.entities.Payment;
import cn.com.yusys.SpringCloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PayController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public Object discovery()
    {
        List<String> server = discoveryClient.getServices();
        for(String s:server){
            log.info("222"+s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instances)
        {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPort()
    {
        return Serverport;
    }

    @GetMapping(value = "/payment/timeout")
    public String timeout()
    {
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return  Serverport;
    }
    //zipkin和Sluoth
    @GetMapping("/payment/zipkin")
    public String paymentzipkin(){
        return "hi ,i am paymentzipkin server fall back,welcome to yusys,哈哈";
    }
}
