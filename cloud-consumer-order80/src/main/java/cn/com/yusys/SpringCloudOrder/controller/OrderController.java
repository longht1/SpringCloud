package cn.com.yusys.SpringCloudOrder.controller;

import cn.com.yusys.SpringCloud.entities.CommonResult;
import cn.com.yusys.SpringCloud.entities.Payment;
import cn.com.yusys.SpringCloudOrder.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    //未集成支付模块时
    //private static final String Payment_url ="http://localhost:8001";
    private static final String Payment_url ="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    //注入自己的规则
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/creat")
    public CommonResult<Payment> creat(Payment payment){
        return restTemplate.postForObject(Payment_url+"/payment/creat",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymet(@PathVariable("id") Long id){
        System.out.println("1111"+id);
        return restTemplate.getForObject(Payment_url+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/Payment/entity/{id}")
    public CommonResult<Payment> getPaymet1(@PathVariable("id") Long id){
        System.out.println("1111"+id);
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(Payment_url+"/Payment/get/"+id,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
           return entity.getBody();
        }else{
            return new CommonResult(444,"查询失败");
        }
    }
    /**
     * 路由规则：轮询
     * @param
     * @return
     */
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
    @GetMapping("/consumer/payment/zipkin")
    public String paymentzipkin(){
        return restTemplate.getForObject(Payment_url+"/payment/zipkin",String.class);
    }
}
