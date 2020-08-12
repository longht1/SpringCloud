package cn.com.yusys.SpringCloud.service.impl;

import cn.com.yusys.SpringCloud.dao.StorageDao;
import cn.com.yusys.SpringCloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("----> StorageService中扣减库存");
        storageDao.decrease(productId, count);
        log.info("----> StorageService中扣减库存完成");
    }
}
