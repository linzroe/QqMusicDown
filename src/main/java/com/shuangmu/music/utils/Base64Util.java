package com.shuangmu.music.utils;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Lin on 2018/4/11.
 */
public class Base64Util {


    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);

    }


}
