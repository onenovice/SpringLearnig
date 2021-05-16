package com.dao.impl;

import com.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 16:30 2021/5/10
 * @ Version:
 */
//<bean id="userDao" class="com.dao.impl.UserDaoImpl"></bean>
//@Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save running ...");
    }
}
