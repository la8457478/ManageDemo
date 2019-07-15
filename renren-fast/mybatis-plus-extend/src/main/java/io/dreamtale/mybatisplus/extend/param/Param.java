package io.dreamtale.mybatisplus.extend.param;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.Map;

public class Param implements Serializable {

    /**
     * 操作符的key，如查询时的name,id之类
     */
    private String path;
    /**
     * 操作符的value，具体要查询的值
     */
    private Object value;
    /**
     * 操作符，自己定义的一组操作符，用来方便查询
     */
    private String oper;
    /**
     * 连接的方式：and或者or
     */
    private String join;


    public Param() {

    }


    public Param(Map.Entry<String, Object> entry) {
        String key = entry.getKey();
        Object value = entry.getValue();
        //如果是Jpa的话，连表查询Key,mybatis false
        boolean isPath = key.contains(".");
        //key中.之前的，mybatis 无
        String fieldParent = isPath ? StringUtils.substringBefore(key, ".") : null;
        String fieldInfo = isPath ? StringUtils.substringAfter(key, ".") : key;
        String[] arr = fieldInfo.split("[_]");
        String fieldName = arr[0];
        String oper = arr.length > 1 ? arr[1] : "eq";
        String join = arr.length > 2 ? arr[2] : "and";
        //mybatis fieldPath = fieldName
        String fieldPath = isPath ? (fieldParent + '.' + fieldName) : fieldName;
        this.setPath(fieldPath);
        this.setValue(value);
        this.setOper(oper);
        this.setJoin(join);
    }

    public Param(String path, Object value, String dataType, String oper, String join) {
        this.path = path;
        this.value = value;
        this.oper = oper;
        this.join = join;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

}