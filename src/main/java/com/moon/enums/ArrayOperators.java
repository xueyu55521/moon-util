package com.moon.enums;

import com.moon.util.able.IteratorAble;
import com.moon.util.able.Stringify;
import com.moon.util.function.IntBiConsumer;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author benshaoye
 */
public interface ArrayOperators extends IteratorAble, Predicate, Stringify {

    /**
     * 获取一个迭代器
     *
     * @param o
     * @return
     */
    @Override
    Iterator iterator(Object o);

    /**
     * 字符串化对象
     *
     * @param o
     * @return
     */
    @Override
    String stringify(Object o);

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param o the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    boolean test(Object o);

    /**
     * 转成一个对象
     *
     * @param o
     * @param <T>
     * @return
     */
    <T> T to(Object o);

    /**
     * 包装类型数组转换为基本数据类型数组
     *
     * @param arr
     * @param <T>
     * @return
     */
    default <T> T toPrimitives(Object arr) {
        return (T) arr;
    }

    /**
     * 基本数据类型数组转换为包装类型数组
     *
     * @param arr
     * @param <T>
     * @return
     */
    default <T> T toObjects(Object arr) {
        return (T) arr;
    }

    /**
     * 获取空数组对象
     *
     * @param <T>
     * @return
     */
    <T> T empty();

    /**
     * 默认空数组
     *
     * @param arr
     * @param <T>
     * @return
     */
    default <T> T emptyIfNull(Object arr) {
        return arr == null ? empty() : (T) arr;
    }

    /**
     * 创建一个指定长度数组
     *
     * @param length
     * @param <T>
     * @return
     */
    <T> T create(int length);

    /**
     * 或者数组指定索引项
     *
     * @param arr
     * @param index
     * @return
     */
    default <T> T get(Object arr, int index) {
        return (T) Array.get(arr, index);
    }

    /**
     * 设置值
     *
     * @param arr
     * @param index
     * @param value
     * @return
     */
    default Object set(Object arr, int index, Object value) {
        Object old = Array.get(arr, index);
        Array.set(arr, index, value);
        return old;
    }

    /**
     * 求数组长度
     *
     * @param arr
     * @return
     */
    default int length(Object arr) {
        return arr == null ? 0 : Array.getLength(arr);
    }

    /**
     * 迭代处理数组每一项
     *
     * @param arr
     * @param consumer
     */
    default void forEach(Object arr, IntBiConsumer consumer) {
        for (int i = 0, len = length(arr); i < len; i++) {
            consumer.accept(get(arr, i), i);
        }
    }

    /**
     * 数组是否包含某一项
     *
     * @param arr
     * @param item
     * @return
     */
    default boolean contains(Object arr, Object item) {
        if (arr == null) {
            return false;
        }
        int len = length(arr);
        if (len == 0) {
            return false;
        }
        if (item == null) {
            for (int i = 0; i < len; i++) {
                if (get(arr, i) == null) {
                    return true;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            if (Objects.equals(item, get(arr, i))) {
                return true;
            }
        }
        return false;
    }
}
