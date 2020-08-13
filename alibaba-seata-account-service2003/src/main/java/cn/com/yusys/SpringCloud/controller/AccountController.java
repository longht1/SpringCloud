package cn.com.yusys.SpringCloud.controller;

import cn.com.yusys.SpringCloud.domian.CommonResult;
import cn.com.yusys.SpringCloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {

        log.info("---> AccountService中扣减账户余额");
        try {
            Thread.sleep(5000); // sleep5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减库存成功!");
    }

}
