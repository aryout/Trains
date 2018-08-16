package com.faceyee.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 97390 on 8/16/2018.
 */
public class RexText {
    public static int returnDig(String line){
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        int res = 0;
        while (m.find( )) {
            res = Integer.parseInt(m.group()); // 去掉可能的第一个元素，即第几个逻辑的‘index’
        }
        return res;
    }

    public static char[] returnChar(String line){
        char[] city = new char[2];
        String pattern = "\\b[A-Z]\\b";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        int i = 0;
        while (m.find()) {
            city[i++] =m.group().charAt(0);
        }
        return city;
    }

    public static String returnString(String line){
        String pattern = "\\b([A-Z](-)?)+\\b";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        if (m.find()) {
            return  m.group();
        }
        return null;
    }
}