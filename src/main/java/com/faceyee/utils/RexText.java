package com.faceyee.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util to handle execute line， return some value
 *
 */
public class RexText {

    /**
     * Return the number parameter
     *@return
     */
    public static int returnDig(String line){
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        int res = 0;
        while (m.find( )) {
            res = Integer.parseInt(m.group());
        }
        return res;
    }

    /**
     * Return the startCity and endCity
     *@return
     */
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

    /**
     * Return the route of given
     *@return
     */
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