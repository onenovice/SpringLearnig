package com.dao;


/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 18:04 2021/5/12
 * @ Version:
 */
public interface AccountDao {

    void out(String outMan, double money);

    void in(String inMan, double money);

}
