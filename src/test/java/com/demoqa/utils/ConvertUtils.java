package com.demoqa.utils;

import java.util.Arrays;

public class ConvertUtils {

    public static String arrayToStringWithoutBrackets(String [] array) {
        return Arrays.toString(array)
                .replaceAll("\\[", "").replaceAll("\\]","");
    }
}
