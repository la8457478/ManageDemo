package io.renren.common.example.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

import io.renren.common.example.param.Param;
import io.renren.common.example.param.converter.ConverterManager;

/**
 * Created by cwx183898 on 2017/12/17.
 */
public abstract class AbstractPredicateGenerator<T> {
    private static final String[] DATE_FORMATERS = new String[] {
        "yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-ddTHH:mm", "yyyy-MM-ddTHH:mm:ss",
        "yyyy/MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss",
        "yyyy/MM/ddTHH:mm", "yyyy/MM/ddTHH:mm:ss"};

    public Function toPredicate(Param param) {
        //        Class paramType = path.getJavaType();//取得参数类型（对应实体的字段类型）
        Function<QueryWrapper<T>,QueryWrapper<T>> o = null;

        o = createCriteria(o, param);
        return o;
    }


    protected Function createCriteria(Function o,Param param) {
        Object[] datas = ConverterManager.get(param.getValue().getClass()).transform(param.getValue());
        String column = param.getPath();
        if (datas == null || datas.length == 0) {
            o = getCreateMethod(o, column, null);
        } else {
            o = getCreateMethod(o, column, datas);
        }
        return o;
    }

    protected abstract Function getCreateMethod(Function o, String column, Object[] values) ;
}