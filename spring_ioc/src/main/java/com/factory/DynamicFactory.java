package com.factory;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 19:28 2021/5/7
 * @ Version:
 */
public class DynamicFactory {
    //非静态，需要先有工厂对象
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
