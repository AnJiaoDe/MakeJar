package com.cy.utils.utils;

public class StringUtils {
    public static String subFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf(""));
    }

    public static String getStrOnly(String str) {
        if (str==null)return "";
        str = str.replaceAll("\n", "");
        str = str.replaceAll("\t", "");
        str = str.replaceAll("\r", "");
        return str.trim();
    }
    public static String getStrJson(String str) {
        if (str==null)return "";
        str = str.replaceAll("\n", "");
        str = str.replaceAll("\t", "");
        str = str.replaceAll("\r", "");
        return str;
    }
}
