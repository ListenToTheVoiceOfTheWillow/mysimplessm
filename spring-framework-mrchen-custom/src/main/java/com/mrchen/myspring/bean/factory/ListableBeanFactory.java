package com.mrchen.myspring.bean.factory;

import java.util.List;

/**
 * 可以将bean和名称进行列表化展示
 */
public interface ListableBeanFactory {

     <T> List<T> getBeansByType(Class<?> type) ;

    List<String> getBeanNamesByType(Class<?> type);

}
