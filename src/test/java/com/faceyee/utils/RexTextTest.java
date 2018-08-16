package com.faceyee.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by 97390 on 8/16/2018.
 */
public class RexTextTest {
    @Test
    public void returnDig() throws Exception {
        String line = "The number of trips starting at A and ending at C with exactly 4 stops.";

        Assert.assertEquals(4, RexText.returnDig(line));
    }

    @Test
    public void returnChar() throws Exception {
        String line = "The length of the shortest route (in terms of distance to travel) from A to C.";

        Assert.assertEquals('A', RexText.returnChar(line)[0]);
        Assert.assertEquals('C', RexText.returnChar(line)[1]);
    }

    @Test
    public void returnString() throws Exception {
        String line = "The distance of the route A-B-C.";

        Assert.assertEquals("A-B-C", RexText.returnString(line));
    }

}