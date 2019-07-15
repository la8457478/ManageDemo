package io.dreamtale.mybatisplus.extend.generator;


import com.google.common.base.CaseFormat;

import java.util.function.Function;

import io.dreamtale.mybatisplus.extend.param.Param;
import io.dreamtale.mybatisplus.extend.param.converter.ConverterManager;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by AndyLiu on 2017/12/17.
 */
@Slf4j
public abstract class AbstractPredicateGenerator<T> {

    private static final String[] DATE_FORMATERS = new String[] {
        "yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-ddTHH:mm", "yyyy-MM-ddTHH:mm:ss",
        "yyyy/MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss",
        "yyyy/MM/ddTHH:mm", "yyyy/MM/ddTHH:mm:ss"};
    private Class<?> clazz = null;

    public Function toPredicate(Param param, Function o, Class paramType) {
        return createCriteria(o, param, paramType);
    }

    protected Function createCriteria(Function o, Param param, Class paramType) {

        Object[] datas = ConverterManager.get(paramType).transform(param.getValue());
        if (null == paramType||datas.length==0) {
            return o;
        }
        String column =
            CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, param.getPath());
        if (datas == null || datas.length == 0) {
            o = getCreateMethod(o, column, null);
        } else {
            o = getCreateMethod(o, column, datas);
        }

        return o;
    }

    protected abstract Function getCreateMethod(Function o, String column, Object[] values);
}