package io.renren.common.example.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

import io.renren.common.example.param.Param;
import io.renren.common.example.param.converter.ConverterManager;


/**
 * Created by AndyLiu on 2017/12/17.
 */
public class NotLikePredicateGenerator<T> extends LikePredicateGenerator<T>{


    public NotLikePredicateGenerator(String before, String after) {
        super(before, after);
    }


    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            String likeValue = (before != null ? before : "") + values[0].toString() + (after != null ? after : "");

            Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.notLike(column, likeValue);
            o = o==null?o1:o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value :
                values) {
                String likeValue = (before != null ? before : "") + values.toString() + (after != null ? after : "");
                Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.or().notLike(column, likeValue);
                o = o==null?o1:o.andThen(o1);
            }
        }
        return o;
    }

}