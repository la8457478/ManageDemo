package io.dreamtale.mybatisplus.extend.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

import io.dreamtale.mybatisplus.extend.param.Param;
import io.dreamtale.mybatisplus.extend.param.converter.ConverterManager;

/**
 * LIKE
 * Created by AndyLiu on 2017/12/17.
 */
public class LikePredicateGenerator<T> extends AbstractPredicateGenerator<T> {

    protected String before;
    protected String after;

    public LikePredicateGenerator(String before, String after) {
        this.before = before;
        this.after = after;
    }

    @Override
    protected Function createCriteria(Function o, Param param, Class paramType) {
        Object[] datas = ConverterManager.get(paramType).transform(param.getValue());

        String column = param.getPath();
        if (datas == null || datas.length == 0) {
            o = this.getCreateMethod(o, column, null);
        } else {
//            for (Object data : datas) {
                o = this.getCreateMethod(o, column, datas);
//            }
        }
        return o;
    }

    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            String likeValue = (before != null ? before : "") + values[0].toString() + (after != null ? after : "");
            Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.like(column, likeValue);
            o = o==null?o1:o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value :
                values) {
                String likeValue = (before != null ? before : "") + values.toString() + (after != null ? after : "");
                Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.or().like(column, likeValue);
                o = o==null?o1:o.andThen(o1);
            }
        }
        return o;
    }

}