package cn.com.yusys.SpringCloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/byURL")
    public String testA(){ return "--------testA"; }

    @GetMapping("/testB")
    public String testB(){
        return "--------testB";
    }


    @GetMapping("/testC")
    public String testC() throws InterruptedException {
        Thread.sleep(1000);
        return "----testC----";
    }
    }