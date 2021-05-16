package com.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 16:03 2021/5/16
 * @ Version:
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String DateStr) {
        //将日期字符串转换成日期对象，返回
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = format.parse(DateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
