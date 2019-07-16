package io.dreamtale.mybatisplus.extend.common.base.controller.method.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AbstractExtendedArgumentResolver implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAssignableFrom(RequestMappingHandlerAdapter.class)) {
            RequestMappingHandlerAdapter requestMappingHandlerAdapter = ((RequestMappingHandlerAdapter) bean);
            List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>(requestMappingHandlerAdapter.getArgumentResolvers());
            HandlerMethodArgumentResolver targetHandler = null;

            for (int i = resolvers.size() - 1; i >= 0; i--) {
                HandlerMethodArgumentResolver handler = resolvers.get(i);
                if (handler.getClass().isAssignableFrom(this.getClass())) {
                    targetHandler = handler;
                    break;
                }
            }
            if (targetHandler != null) {
                resolvers.remove(targetHandler);
                resolvers.add(0, targetHandler);
                requestMappingHandlerAdapter.setArgumentResolvers(resolvers); // change the jsonhandler sort
            }
        }
        return bean;
    }


    public static String replacer(String value) {
        String data = value;
        if (data != null) {
            data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            data = data.replaceAll("\\+", "%2B");
        }
        return data;
    }
}
