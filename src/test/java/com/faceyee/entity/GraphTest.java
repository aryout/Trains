package com.faceyee.entity;

import com.faceyee.entity.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 97390 on 8/15/2018.
 */
public class GraphTest {

    Graph testGraph;
    @Before
    public void setUp() throws Exception {
        testGraph = new Graph("Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }
    @Test
    public void getcityList() throws Exception {
        assertEquals('C', testGraph.getcityList()[2].getName());
    }

    @Test
    public void hasCycle() throws Exception {
        assertTrue(testGraph.isHasCycle());
    }

    @Test
    public void getCityMat() throws Exception {
        assertEquals(5, testGraph.getCityMat()[0][1]);
    }

    @Test
    public void getN() throws Exception {
        assertEquals(5, testGraph.getN());
    }

}