package com.mrchen.myspring.bean.xml;

import java.io.InputStream;

/**
 * 资源管理的接口
 */
public interface Resource {
    /**
     * 得到 输入的流
     * @return
     */
     InputStream getResource();
}
