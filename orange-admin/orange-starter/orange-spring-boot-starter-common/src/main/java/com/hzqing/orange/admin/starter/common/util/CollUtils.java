package com.hzqing.orange.admin.starter.common.util;

import cn.hutool.core.collection.CollUtil;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 程序员橙子
 */
public class CollUtils {

    private CollUtils() {
    }

    public static <T, U> List<U> convertList(Collection<T> collection, Function<T, U> func) {
        if (CollUtil.isEmpty(collection)) {
            return List.of();
        }
        return collection.stream().map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }

}