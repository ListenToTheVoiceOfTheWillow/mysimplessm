package com.mrchen.mybatis.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @program: mybatis-demo
 * @description:
 * @author: mrchen
 * @create: 2020-04-29 21:57
 */
public class DocumentUtils {
    public static  Document  readDocument(InputStream inputStream){
        SAXReader saxReader=new SAXReader();
        try {
            Document document=saxReader.read(inputStream);
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
