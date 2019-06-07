package com.cy.http.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static String getName(String path) {
        int index = path.lastIndexOf("/");
        if (index < 0) return path;
        return path.substring(index+1, path.length());
    }
    /**
     * 根据路径 创建文件
     *
     * @param pathFile
     * @return
     * @throws IOException
     */
    public static File createFile(String pathFile) throws IOException {
        File fileDir = new File(pathFile.substring(0, pathFile.lastIndexOf(File.separator)));
        File file = new File(pathFile);
        if (!fileDir.exists()) fileDir.mkdirs();
        if (!file.exists()) file.createNewFile();
        return file;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @return
     */
    public static boolean deleteFolder(String filePath) {
        File dirFile = new File(filePath);
        if (!dirFile.exists()) return false;
        if (dirFile.isDirectory()) {
            // 如果下面还有文件
            for (File file : dirFile.listFiles()) {
                deleteFolder(file.getAbsolutePath());
            }
        }
        return dirFile.delete();
    }


}