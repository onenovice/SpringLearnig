package com.test;

import com.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 增删改查测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    //修改
    public void updateTest() {
        jdbcTemplate.update("update account set money=? where name=?", 10000, "Tom");
    }

    @Test
    //删除
    public void deleteTest() {
        jdbcTemplate.update("delete from account where name=?", "Eric");
    }

    @Test
    //查询所有
    public void queryAllTest() {
        List<Account> query = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
        System.out.println(query);
    }

    @Test
    //查询一行
    public void queryOneTest() {
        Account query = jdbcTemplate.queryForObject("select * from account where name=?", new BeanPropertyRowMapper<Account>(Account.class), "Tom");
        System.out.println(query);
    }

    @Test
    //查询总行数
    public void queryCountTest() {
        Long count = jdbcTemplate.queryForObject("select count(*) from account", Long.class);
        System.out.println(count);
    }

}
