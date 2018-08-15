package com.faceyee;

import org.junit.Assume;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by 97390 on 8/15/2018.
 */
public class AppTest {

    @Test
    public void main() throws IOException {
        String[] args = new String[1];
        Assume.assumeFalse(args.length < 0);
        args[0] = "C:/Users/97390/IdeaProjects/train/src/main/java/com/faceyee/graph.txt";
        Assume.assumeTrue(args.length > 0);
        // App.main(args);
    }
}