package com.factory;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

/**
 * 工厂静态方法实例化
 */
public class StaticFactory {
    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
