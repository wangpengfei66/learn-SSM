package com.kaishengit.util.orm;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 查询条件封装类
 */
public class Condition {
    private String propertyName;
    private Object value;
    private String type;

    public Condition() {}

    public Condition(String propertyName, Object value, String type) {
        this.propertyName = propertyName;
        this.value = value;
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 根据查询条件自动产生Condition集合
     * @param request
     * @return
     */
    public static List<Condition> buliderConditionList(HttpServletRequest request) {
        List<Condition> conditions = new ArrayList<>();
        //获取查询条件
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = request.getParameter(key);
            // 约定的方式
            // q_eq_s_userName=abc
            // q_like_s_custName=xyz
            //q_i_eq_id
            //所以 只处理以q_开头的并且值存在的键值对
            if(key.startsWith("q_") && StringUtils.isNotEmpty(value)) {
                try {
                    value = new String(value.getBytes("ISO8859-1"),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String [] array = key.split("_",4);
                String valueType = array[1];
                //将约定的数据类型进行相应的转换
                Object conditionValue = convertValueType(valueType,value);
                Condition condition = new Condition(array[3],conditionValue,array[2]);
                conditions.add(condition);
                request.setAttribute(key,value);
            }
        }
        return conditions;
    }

    /**
     * 将约定的数据类型进行相应的转换
     * @param valueType
     * @param value
     * @return
     */
    private static Object convertValueType(String valueType, String value) {
        if("s".equalsIgnoreCase(valueType)) {
            return value;
        } else if ("i".equalsIgnoreCase(valueType)) {
            return Integer.valueOf(value);
        } else if ("b".equalsIgnoreCase(valueType)) {
            return Boolean.valueOf(value);
        } else if("d".equalsIgnoreCase(valueType)) {
            return Double.valueOf(value);
        } else if ("f".equalsIgnoreCase(valueType)) {
            return Float.valueOf(value);
        } else if ("l".equalsIgnoreCase(valueType)) {
            return Long.valueOf(value);
        }
        return null;
    }
}
