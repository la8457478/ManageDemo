package io.dreamtale.mybatisplus.extend.common.base.controller.method.support.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by AndyLiu on 2017/8/17.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JSON {
    public String[] excludeProperties() default {};

    public String[] includeProperties() default {};

    public boolean ignoreHierarchy() default false;

    public boolean enumAsBean() default false;

    public boolean excludeNullProperties() default false;

}
