package com.michael.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @ClassName SpringUtils
 * @Description spring工具类 方便在非spring管理环境中获取bean
 * @Author Michael.Ng
 * @Date 2021/2/21 11:52
 **/
//@Configuration
public class SpringUtils implements BeanFactoryPostProcessor {

        /** Spring应用上下文环境 */
        private static ConfigurableListableBeanFactory beanFactory;

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                SpringUtils.beanFactory = beanFactory;
        }

        public static <T> T getBean(Class<T> clz) throws BeansException {
                return (T) beanFactory.getBean(clz);
        }

        @SuppressWarnings("unchecked")
        public static <T> T getBean(String name) throws BeansException {
                return (T) beanFactory.getBean(name);
        }
}