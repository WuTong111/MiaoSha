/**
 * $Id: Demo.java,v 1.0 2019/3/7 10:47 G Exp $
 * <p>
 * Copyright 2018 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.gy.miaosha;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @Description: 测试
 * @author Wuguang
 * @version $Id: Demo.java,v 1.1 2019/3/7 10:47 G Exp $
 * Created on 2019/3/7 10:47
 */
public class Demo {
    public static void main(String[] args) throws IOException {
       //第一步行为参数化
        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }

    private static String processFile(BufferedReaderProcessor brp) throws IOException{
        return null;
    }

}

@FunctionalInterface
interface BufferedReaderProcessor{
    String process(BufferedReader b) throws Exception;
}

