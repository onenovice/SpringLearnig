package com.service.impl;

import com.dao.UserDao;
import com.service.UserService;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 19:34 2021/5/7
 * @ Version:
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }
}
