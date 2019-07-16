package io.dreamtale.bean.entity;//package com.bianmaba.quality.bean.entity;
//
//
//import com.alibaba.fastjson.JSONObject;
//
///**
// * @author liuan
// * @Title ResultUtil.java
// * @description 响应前端信息Util
// * @time 2017年8月25日 下午4:59:00
// */
//public class ResultUtil {
//
//    private static final String RES_CODE_SUCCESS = "200";
//    private static final String RES_CODE_ERROR = "300";
//    public static final String RES_CODE_EMPTY = "400";
//    public static final String RES_CODE_REPEAT = "100";
//    public static final String RES_CODE_OTHER = "500";
//
//    /**
//     * 成功无数据返回
//     *
//     * @param message
//     * @author chenkun
//     * @date 2017年8月25日下午4:58:02
//     */
//    public static ReturnObject success(String message) {
//        return comm(RES_CODE_SUCCESS, message);
//    }
//
//    /**
//     * 成功有数据返回
//     *
//     * @param message
//     * @param data
//     * @author chenkun
//     * @date 2017年8月25日下午4:57:44
//     */
//    public static <T> ReturnObject success(String message, T data) {
//        return comm(RES_CODE_SUCCESS, message, data);
//    }
//
//    /**
//     * 错误或异常
//     *
//     * @param message
//     * @author chenkun
//     * @date 2017年8月25日下午4:57:32
//     */
//    public static <T> ReturnObject error(String message) {
//        return comm(RES_CODE_ERROR, message);
//    }
//
//    /**
//     * 参数异常
//     *
//     * @param paramKey
//     * @return
//     * @author chenkun
//     * @date 2017年8月25日下午5:08:51
//     */
//    public static <T> ReturnObject paramError(String paramKey) {
//        return comm(RES_CODE_ERROR, "参数异常," + paramKey);
//    }
//
//    /**
//     * 检验json格式
//     *
//     * @return
//     * @author chenkun
//     * @date 2017年8月30日上午9:26:11
//     */
//    public static <T> ReturnObject jsonError() {
//        return comm(RES_CODE_ERROR, "参数错误");
//    }
//
//    /**
//     * 查询为空
//     *
//     * @return
//     * @author chenkun
//     * @date 2017年8月30日上午9:26:18
//     */
//    public static <T> ReturnObject empty() {
//        return comm(RES_CODE_EMPTY, "暂无数据");
//    }
//
//    /**
//     * 查询为空,自定义message
//     *
//     * @param message
//     * @return
//     * @author chenkun
//     * @date 2017年9月1日下午3:19:48
//     */
//    public static <T> ReturnObject empty(String message) {
//        return comm(RES_CODE_EMPTY, message);
//    }
//
//    /**
//     * 自定义响应码
//     *
//     * @param code
//     * @param message
//     * @return
//     * @author chenkun
//     * @date 2017年8月30日上午9:26:26
//     */
//    public static <T> ReturnObject other(String code, String message) {
//        return comm(code, message);
//    }
//
//    /**
//     * 自定义响应码,有数据返回
//     *
//     * @param code
//     * @param message
//     * @param <T>
//     * @return
//     * @author chenkun
//     * @date 2017年8月30日上午9:26:26
//     */
//    public static <T> ReturnObject other(String code, String message, T data) {
//        return comm(code, message, data);
//    }
//
//    private static ReturnObject comm(String code, String message) {
//        ReturnObject returnObject = new ReturnObject();
//        returnObject.setCode(code);
//        returnObject.setMsg(message);
//        return returnObject;
//    }
//
//    private static <T> ReturnObject
//    comm(String code, String message, T data) {
//
//        ReturnObject returnObject = new ReturnObject();
//        returnObject.setCode(code);
//        returnObject.setData(data);
//        returnObject.setMsg(message);
//        return returnObject;
//    }
//}
