package com.gree.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 实体转换工具类
 * @createTime 2019 -05-31 15:26:50
 */
public class BeanUtil {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @description 系统转换过的实体记录（方便复用）
     * @createTime 2019 -05-31 15:26:51
     * @version 1.0
     */
    private final Map<Class<?>, Map<String, Field>> META_DATA = new HashMap<>();
    /**
     * @description 读写时加锁，防止读写异常
     * @createTime 2019 -05-31 15:26:51
     * @version 1.0
     */
    private WriteLock lock = new ReentrantReadWriteLock().writeLock();

    /**
     * @description 实例对象
     * @createTime 2019 -05-31 15:30:13
     * @version 1.0
     */
    private static  BeanUtil beanUtil = null;

    /**
     * @return 当前对象用例
     * @description 单例模式创建实例
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -05-31 15:30:13
     */
    public static BeanUtil createBeanUtil() {
        if (beanUtil == null){
            beanUtil = new BeanUtil();
        }
        return beanUtil;
    }

    /**
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @description 属性复制属性枚举
     * @createTime 2019 -05-31 15:26:50
     */
    enum FieldType {
        /**
         * @description 全部属性
         * @createTime 2019 -05-31 15:26:51
         * @version 1.0
         */
        ALL,
        /**
         * @description 静态属性
         * @createTime 2019 -05-31 15:26:51
         * @version 1.0
         */
        STATIC,
        /**
         * @description 非静态属性
         * @createTime 2019 -05-31 15:26:51
         * @version 1.0
         */
        NOT_STATIC
    }

    /**
     * @param sources 资源集合
     * @param targetClass 目标集合的类类型
     * @return 目标集合
     * @description 转换当前集合到目标集合
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -05-31 15:26:50
     */
    public <T>List<T> convert (Collection<?> sources, Class<T> targetClass) {
        List<T> targets = new ArrayList<>();
        if(sources != null && sources.size() > 0) {
            for (Object object :sources) {
                targets.add(convert(object,targetClass));
            }
        }
        return targets;
    }

    /**
     * @param source 资源类
     * @param targetClass 目标类
     * @return 目标类
     * @description 两个类的转换
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -05-31 15:26:50
     */
    public <T> T convert (Object source, Class<T> targetClass) {
        T target = newInstance(targetClass);
        copyProperties(source, target, true);
        return target;
    }

    /**
     * @param source 资源类
     * @param target 目标类
     * @param copyNullProperty 是否忽略空值属性
     * @description 对资源类的属性值复制到目标类属性
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -05-31 15:26:50
     */
    private void copyProperties(Object source, Object target, Boolean copyNullProperty) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        Map<String, Field> sourceFields = getFieldsMap(sourceClass, FieldType.NOT_STATIC);
        Map<String, Field> targetFields = getFieldsMap(targetClass, FieldType.NOT_STATIC);
        for (String name: targetFields.keySet()) {
            if (sourceFields.containsKey(name)) {
                Field sourceField = sourceFields.get(name);
                Field targetField = targetFields.get(name);
                if (sourceField.getType() == targetField.getType()) {
                    Object value = getPropertyValue(source, sourceField);
                    if (value == null && !copyNullProperty) {
                        continue;
                    }
                    setPropertyValue(target, targetField, value);
                }
            }
        }
    }

    /**
     * Gets fields map.
     *
     * @param beanClass 类资源
     * @param fieldType 过滤类型
     * @return 资源类的所有属性集合
     * @description 获取资源类的所有 key属性名称 value属性，并且根据过滤属性进行过滤获取
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @createTime 2019-05-31 15:26:50
     */
    private Map<String, Field> getFieldsMap (Class<?> beanClass, FieldType fieldType) {
        Map<String, Field> target = new HashMap<>();
        Map<String, Field> map = getReferableFieldMap(beanClass);
        if (map != null && !map.isEmpty()) {
            for (String name : map.keySet()) {
                Field field = map.get(name);
                switch (fieldType) {
                    case ALL:
                        target.put(name, field);
                        break;
                    case STATIC:
                        if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
                            target.put(name, field);
                        }
                        break;
                    case NOT_STATIC:
                        if ((field.getModifiers() & Modifier.STATIC) != Modifier.STATIC) {
                            target.put(name, field);
                        }
                        break;
                }
            }
        }
        return target;
    }

    /**
     * Gets referable field map.
     *
     * @param beanClass 资源类
     * @return 资源类的所有 key属性名称 value属性
     * @description 获取资源类的所有 key属性名称 value属性 （首先从META_DATA中获取，获取不到再去解析并存到当前META_DATA）TODO 解决一个异常，参数变更问题
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @createTime 2019-05-31 15:26:50
     */
    private Map<String, Field> getReferableFieldMap (Class<?> beanClass) {
        Class<?> clazz = beanClass;
        if (!META_DATA.containsKey(beanClass)) {
            try {
                lock.lock();
                if (!META_DATA.containsKey(beanClass)) {
                    Map<String, Field> map = new HashMap<>();
                    while (beanClass != null) {
                        map.putAll(getDeclaredFieldsMap(beanClass));
                        beanClass = beanClass.getSuperclass();
                    }
                    META_DATA.put(clazz, map);
                }
            }finally {
                lock.unlock();
            }
        }
        return META_DATA.get(clazz);
    }

    /**
     * Gets declared fields map.
     *
     * @param beanClass 资源类
     * @return 资源类的所有 key属性名称 value属性
     * @description 获取资源类的所有 key属性名称 value属性
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @createTime 2019-05-31 15:26:51
     */
    private Map<String, Field> getDeclaredFieldsMap (Class<?> beanClass) {
        Field[] fields = beanClass.getDeclaredFields();
        Map<String, Field> map = new HashMap<>();
        if(fields != null && fields.length > 0) {
            for (Field field: fields) {
                field.setAccessible(true);
                map.put(field.getName(), field);
            }
        }
        return map;
    }

    /**
     * Gets property value.
     *
     * @param bean 资源类
     * @param property 属性名称
     * @return 属性值
     * @description 根据属性名称获取属性值
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @createTime 2019-05-31 15:26:51
     */
    private <T> T getPropertyValue (Object bean, String property) {
        if (bean != null) {
            Class<?> beanClass = null;
            if (bean instanceof Class) {
                beanClass = (Class<?>)bean;
            } else {
                beanClass = bean.getClass();
            }
            Field field = getFieldsMap(beanClass, FieldType.ALL).get(property);
            if (field != null){
                return getPropertyValue(bean, field);
            }
        }
        return null;
    }

    /**
     * Sets property value.
     *
     * @param bean 资源类
     * @param field 属性
     * @param propertyValue 属性值
     * @description 设置属性值
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @createTime 2019 -05-31 15:26:51
     */
    private void setPropertyValue (Object bean, Field field,Object propertyValue) {
        try {
            field.set(bean,propertyValue);
        } catch (Throwable e) {
            throw new RuntimeException(bean.getClass().getName() + "" +field.getName() + "" + propertyValue, e);
        }
    }

    /**
     * Gets property value.
     *
     * @param bean 资源类
     * @param field 属性
     * @return 属性值
     * @description 获取属性值
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @createTime 2019-05-31 15:26:51
     */
    @SuppressWarnings("unchecked")
    private <T> T getPropertyValue (Object bean, Field field) {
        try {
            return (T)field.get(bean);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param beanClass 资源类
     * @return 实例化后的类
     * @description 实例化一个资源类
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     * @createTime 2019 -05-31 15:26:51
     */
    private <T>T newInstance (Class<T> beanClass) {
        try {
            return beanClass.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
