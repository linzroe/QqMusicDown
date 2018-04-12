package com.shuangmu.music.utils;

import java.io.File;

/**
 * Created by Lin on 2018/4/11.
 */
public class FileUtil {

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
