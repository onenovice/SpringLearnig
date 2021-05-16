package com.domain;

import java.util.List;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 14:20 2021/5/16
 * @ Version:
 */
public class VO {
    private List<User> userList;

    @Override
    public String toString() {
        return "VO{" +
                "userList=" + userList +
                '}';
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
