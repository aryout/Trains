package com.faceyee.service;

import com.faceyee.service.TrainRoad;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 97390 on 8/15/2018.
 */
public class TrainRoadTest {
    TrainRoad testTrainRoad;
    @Before
    public void setUp() throws Exception {
        testTrainRoad = new TrainRoad("Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }

    @Test
    public void fun2() throws Exception {
        Assert.assertEquals(2, testTrainRoad.fun2("6.The number of trips starting at C and ending at C with a maximum of 3 stops."));
        Assert.assertEquals(3, testTrainRoad.fun2("7.The number of trips starting at A and ending at C with exactly 4 stops."));
    }

    @Test
    public void fun3() throws Exception {
        Assert.assertEquals(9, testTrainRoad.fun3("8.The length of the shortest route (in terms of distance to travel) from A to C."));
        Assert.assertEquals(9, testTrainRoad.fun3("9.The length of the shortest route (in terms of distance to travel) from B to B."));
    }

    @Test
    public void fun1() throws Exception {
        Assert.assertEquals("9", testTrainRoad.fun1("1.The distance of the route A-B-C."));
        Assert.assertEquals("5", testTrainRoad.fun1("2.The distance of the route A-D."));
        Assert.assertEquals("13", testTrainRoad.fun1("3.The distance of the route A-D-C."));
        Assert.assertEquals("22", testTrainRoad.fun1("4.The distance of the route A-E-B-C-D."));
        Assert.assertEquals("NO SUCH ROUTE", testTrainRoad.fun1("5.The distance of the route A-E-D."));
    }

    @Test
    public void fun4() throws Exception {
        Assert.assertEquals(7, testTrainRoad.fun4("10.The number of different routes from C to C with a distance of less than 30. "));
    }

}