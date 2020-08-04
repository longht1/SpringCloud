package cn.com.yusys.SpringCloud.service.Impl;

import cn.com.yusys.SpringCloud.service.PaymentService;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class paymentServiceImpl implements PaymentService {

    public String paymentInfo_OK(long id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id："+id+"\t"+"O(∩_∩)O哈哈~";
    }

    public String paymentInfo_Timeout(long id){
//        int timeNumber = 3;
//       // int a=10/0;
//        try{
//            TimeUnit.SECONDS.sleep(timeNumber);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Timeout,id："+id+"\t"+"O(∩_∩)O哈哈~"+"   耗时(秒)：";
    }

    //服务熔断代码
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value ="60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id")long id){
        if(id<0){
            throw new RuntimeException("******id 不能为负数");

        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id")long id){
        return "id 不能为负数，请稍后再试，#### id："+id;
    }
}
