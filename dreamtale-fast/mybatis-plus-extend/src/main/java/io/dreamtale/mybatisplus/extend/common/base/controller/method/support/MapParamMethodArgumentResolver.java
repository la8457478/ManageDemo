package io.dreamtale.mybatisplus.extend.common.base.controller.method.support;


import org.springframework.core.MethodParameter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import io.dreamtale.mybatisplus.extend.common.base.controller.method.support.annotations.MapParam;


/**
 * Created by AndyLiu on 2017/8/18.
 */
public class MapParamMethodArgumentResolver extends AbstractExtendedArgumentResolver implements HandlerMethodArgumentResolver {
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MapParam.class) && Map.class.isAssignableFrom(parameter.getParameterType());
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String name = parameter.getParameterName();
        Class paramType = parameter.getParameterType();
        Map parameterMap = webRequest.getParameterMap();
        Iterator iterator;
        Map.Entry entry;
        if (!MultiValueMap.class.isAssignableFrom(paramType)) {
            LinkedHashMap result = new LinkedHashMap(parameterMap.size());
            iterator = parameterMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                String[] value = (String[]) entry.getValue();
                if (key.startsWith(name + '.')) {
                    if (value.length > 1) {
                        result.put(key.replaceFirst(name + '.', ""), value);
                    } else if (value.length > 0) {
                        result.put(key.replaceFirst(name + '.', ""), value[0]);
                    }
                }
            }
            return result;
        } else {
            LinkedMultiValueMap result = new LinkedMultiValueMap(parameterMap.size());
            iterator = parameterMap.entrySet().iterator();

            while (iterator.hasNext()) {
                entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                if (key.startsWith(name + '.')) {
                    String[] values = (String[]) entry.getValue();
                    int len = values.length;

                    for (int i = 0; i < len; ++i) {
                        String value = values[i];
                        result.add(key.replaceFirst(name + '.', ""), value);
                    }
                }
            }
            return result;
        }
    }
}
