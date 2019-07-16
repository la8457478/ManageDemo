package io.dreamtale.mybatisplus.extend.common.base.controller.method.support.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@JSON
public @interface StrutsJSON {
    @AliasFor(annotation = JSON.class)
    public String[] excludeProperties() default {};

    @AliasFor(annotation = JSON.class)
    public String[] includeProperties() default {};

    @AliasFor(annotation = JSON.class)
    public boolean ignoreHierarchy() default false;

    @AliasFor(annotation = JSON.class)
    public boolean enumAsBean() default false;

    @AliasFor(annotation = JSON.class)
    public boolean excludeNullProperties() default false;
}
