package com.service;

import com.exception.MyException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 15:05 2021/5/17
 * @ Version:
 */
public interface DemoService {
    public void show1();
    public void show2();
    public void show3() throws FileNotFoundException;
    public void show4();
    public void show5() throws MyException;
}
