package com.faceyee.service;

import com.faceyee.entity.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 97390 on 8/15/2018.
 */
public class StraightRoadTest {
    Graph testGraph;

    @Before
    public void setUp() throws Exception {
        testGraph = new Graph("Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }
    @Test
    public void getRoads() throws Exception {
        StraightRoad testStraightRoad = new StraightRoad(testGraph);
        assertNull(testStraightRoad.getRoads('A', 'A')); // 无环
        assertNull(testStraightRoad.getRoads('C', 'C')); // 有环
        assertNotNull(testStraightRoad.getRoads('A', 'C')); // 可达
        assertNull(testStraightRoad.getRoads('C', 'A')); // 不可达
    }
}