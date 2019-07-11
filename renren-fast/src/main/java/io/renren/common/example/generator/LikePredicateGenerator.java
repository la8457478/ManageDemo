package io.renren.common.example.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.example.param.Param;
import io.renren.common.example.param.converter.ConverterManager;

import java.util.function.Function;

/**
 * LIKE
 * Created by cwx183898 on 2017/12/17.
 */
public class LikePredicateGenerator<T> extends AbstractPredicateGenerator<T> {

    protected String before;
    protected String after;

    public LikePredicateGenerator(String before, String after) {
        this.before = before;
        this.after = after;
    }

    @Override
    protected Function createCriteria(Function o, Param param,Class paramType) {
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
            o = o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value :
                values) {
                String likeValue = (before != null ? before : "") + values.toString() + (after != null ? after : "");
                Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.or().like(column, likeValue);
                o = o.andThen(o1);
            }
        }
        return o;
    }

}