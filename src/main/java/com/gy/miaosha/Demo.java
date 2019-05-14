/**
 * $Id: Demo.java,v 1.0 2019/3/7 10:47 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha;


import java.io.IOException;
import java.util.LinkedList;

/**
 * @Description: 测试
 * @author Wuguang
 * @version $Id: Demo.java,v 1.1 2019/3/7 10:47 G Exp $
 * Created on 2019/3/7 10:47
 */
public class Demo {
    public static void main(String[] args) throws IOException {
       //第一步行为参数化
        LinkedList list1 = new LinkedList();
        list1.add("123");
        LinkedList list = new LinkedList(list1);
        System.out.println(list.get(0));

    }

    private static String processFile(BufferedReaderProcessor brp) throws IOException{
        return null;
    }

}

class BufferedReaderProcessor{
}

