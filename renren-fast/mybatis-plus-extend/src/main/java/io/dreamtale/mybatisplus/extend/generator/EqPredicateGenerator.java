package io.dreamtale.mybatisplus.extend.generator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;


/**
 * 等于
 * Created by AndyLiu on 2017/12/17.
 */
public class EqPredicateGenerator<T> extends AbstractPredicateGenerator<T> {


    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            Function<QueryWrapper<T>,QueryWrapper<T>> o1= i -> i.eq(column,values[0]);
            o = o==null?o1:o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value:
                values ) {
                Function<QueryWrapper<T>,QueryWrapper<T>> o1 = i -> i.or().eq(column,value);
                o = o==null?o1:o.andThen(o1);
            }
        }

        return o;
    }
}
