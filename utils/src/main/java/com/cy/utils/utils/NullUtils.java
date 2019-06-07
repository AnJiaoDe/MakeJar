package com.cy.utils.utils;//package com.ly.utils.utils;
//
//public final class NullUtils {
//
//    public static <T> T checkNotNull(T reference) {
//        if (reference == null) {
//            throw new NullPointerException();
//        }
//        return reference;
//    }
//
//    public static void checkAllNotNull(Object... references) {
//        for (Object reference : references) {
//            if (reference == null) {
//                throw new NullPointerException();
//            }
//        }
//    }
//
//    public static <T> T checkNotNull(T reference, String errorMessage) {
//        if (reference == null) {
//            throw new NullPointerException(errorMessage);
//        }
//        return reference;
//    }
//
//    public static void checkArgument(boolean expression) {
//        if (!expression) {
//            throw new IllegalArgumentException();
//        }
//    }
//
//    public static void checkArgument(boolean expression, String errorMessage) {
//        if (!expression) {
//            throw new IllegalArgumentException(errorMessage);
//        }
//    }
//}
