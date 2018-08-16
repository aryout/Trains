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
    public void distanceOfSomeCity() throws Exception {
        Assert.assertEquals("9", testTrainRoad.distanceOfSomeCity("1.The distance of the route A-B-C."));
        Assert.assertEquals("5", testTrainRoad.distanceOfSomeCity("2.The distance of the route A-D."));
        Assert.assertEquals("13", testTrainRoad.distanceOfSomeCity("3.The distance of the route A-D-C."));
        Assert.assertEquals("22", testTrainRoad.distanceOfSomeCity("4.The distance of the route A-E-B-C-D."));
        Assert.assertEquals("NO SUCH ROUTE", testTrainRoad.distanceOfSomeCity("5.The distance of the route A-E-D."));
    }

    @Test
    public void numberOfDifferentRoadsWithStops() throws Exception {
        Assert.assertEquals(2, testTrainRoad.numberOfDifferentRoadsWithStops("6.The number of trips starting at C and ending at C with a maximum of 3 stops."));
        Assert.assertEquals(3, testTrainRoad.numberOfDifferentRoadsWithStops("7.The number of trips starting at A and ending at C with exactly 4 stops."));
    }

    @Test
    public void lengthOfShortestRoute() throws Exception {
        Assert.assertEquals(9, testTrainRoad.lengthOfShortestRoute("8.The length of the shortest route (in terms of distance to travel) from A to C."));
        Assert.assertEquals(9, testTrainRoad.lengthOfShortestRoute("9.The length of the shortest route (in terms of distance to travel) from B to B."));
    }

    @Test
    public void numberOfDifferentRoadsWithDistance() throws Exception {
        Assert.assertEquals(7, testTrainRoad.numberOfDifferentRoadsWithDistance("10.The number of different routes from C to C with a distance of less than 30."));
    }

}