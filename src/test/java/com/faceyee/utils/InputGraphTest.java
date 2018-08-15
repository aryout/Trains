package com.faceyee.utils;

import com.faceyee.utils.InputGraph;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by 97390 on 8/15/2018.
 */
public class InputGraphTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void exec() throws Exception {
        String inputfile = "this's not an exist file";

        InputGraph testInputGraph = new InputGraph(inputfile);

        assertNotNull(testInputGraph);

        thrown.expect(FileNotFoundException.class);
        testInputGraph.exec();
    }
}