package com.service.impl;

import com.dao.AccountDao;
import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service("accountService")
//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
//写在类前代表适用类中所有方法，但若具体方法也使用了该注解则以方法前的为准
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void transfer(String outMan, String inMan, double money) {

        accountDao.out(outMan, money);
        //这期间有异常会导致一个人前减少，而另一个没增加
        //制造 除0 错误检验

        int i = 1 / 0;//取消注释比较
        accountDao.in(inMan, money);
    }
}
