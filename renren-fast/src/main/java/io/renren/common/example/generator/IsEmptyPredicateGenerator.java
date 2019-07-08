package io.renren.common.example.generator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

/**
 * 为空
 * Created by cwx183898 on 2017/12/17.
 */
public class IsEmptyPredicateGenerator<T> extends AbstractPredicateGenerator<T> {


    protected QueryWrapper getCreateMethod(QueryWrapper<T> wrapper, String column, Object[] values) {
        if (values.length == 1) {
            wrapper.eq(column,"");
        } else if (values.length > 1) {
            Function<QueryWrapper<T>,QueryWrapper<T>> o=null;
            for (Object value:
                    values ) {
                Function<QueryWrapper<T>,QueryWrapper<T>> o1 = i -> i.eq(column,"");
                o = o.andThen(o1);
            }
            wrapper.nested(o);
        }
        return wrapper;
    }

    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        return null;
    }
}
