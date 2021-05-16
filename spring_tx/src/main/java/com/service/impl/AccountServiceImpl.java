package com.service.impl;

import com.dao.AccountDao;
import com.service.AccountService;

/**
 *
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(String outMan, String inMan, double money) {

        accountDao.out(outMan, money);
        //这期间有异常会导致一个人前减少，而另一个没增加
        //制造 除0 错误检验


        //int i=1/0;//取消注释比较
        accountDao.in(inMan, money);
    }
}
