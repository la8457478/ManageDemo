package io.renren.common.base.controller.method.support;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import io.renren.common.base.controller.method.support.annotations.EntityParam;

/**
 * Created by AndyLiu on 2017/8/17.
 */
public class EntityParamMethodArgumentResolver extends AbstractExtendedArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(EntityParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String name = parameter.getParameterName();
        // 查找是否已有名为name的参数值的映射，如果没有则创建一个
        Object attribute = (mavContainer.containsAttribute(name) ? mavContainer.getModel().get(name) :
                createAttribute(name, parameter, binderFactory, webRequest));

        if (binderFactory != null) {
            WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);

            if (binder.getTarget() != null) {
                // 进行参数绑定
                this.bindRequestParameters(binder, webRequest, name);
            }

            // 将参数转到预期类型，第一个参数为解析后的值，第二个参数为绑定Controller参数的类型，第三个参数为绑定的Controller参数
            Object target = binder.getTarget();
            Class clazz = parameter.getParameterType();
            attribute = binder.convertIfNecessary(target, clazz, parameter);
        }

        return attribute;
    }

    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request, String pn) throws UnsupportedEncodingException {
        // 将key-value封装为map，传给bind方法进行参数值绑定
        Map<String, String> map = new HashMap<String, String>(0);
        Map<String, String[]> params = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String name = entry.getKey();
            if (name.startsWith(pn + '.')) {
                // 执行urldecode
                String value = entry.getValue()[0];
                value = replacer(value);
                value = URLDecoder.decode(value, "UTF-8");
                map.put(name.replaceFirst(pn + '.', ""), value);
            }
        }


        PropertyValues propertyValues = new MutablePropertyValues(map);

        // 将K-V绑定到binder.target属性上
        binder.bind(propertyValues);
    }

    protected Object createAttribute(String attributeName, MethodParameter methodParam,
                                     WebDataBinderFactory binderFactory, NativeWebRequest request) throws Exception {

        return BeanUtils.instantiateClass(methodParam.getParameterType());
    }

    public static String getNameForParameter(MethodParameter parameter) {
        ModelAttribute ann = parameter.getParameterAnnotation(ModelAttribute.class);
        String name = (ann != null ? ann.value() : null);
        return (StringUtils.hasText(name) ? name : Conventions.getVariableNameForParameter(parameter));
    }

}
