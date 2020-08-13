package cn.com.yusys.SpringCloud.service.impl;

import cn.com.yusys.SpringCloud.dao.AccountDao;
import cn.com.yusys.SpringCloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("---> AccountService中扣减账户余额");
        accountDao.decrease(userId, money);
        log.info("---> AccountService中扣减账户余额完成");
    }

}
