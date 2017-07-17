package com.kaishengit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/7/14.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);
    public static String isoToUtf(String str) {
        try {
            if(StringUtils.isNotEmpty(str)) {
                return new String(str.getBytes("ISO8859-1"),"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串{}转换异常",str);
            throw new RuntimeException("字符串转换异常",e);
        }
        return null;
    }

}
