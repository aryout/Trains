package com.faceyee.entity;

import com.faceyee.entity.City;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 97390 on 8/15/2018.
 */
public class CityTest {
    @Test
    public void getName() throws Exception {
        char v = 'C';
        City City = new City(v);

        assertEquals('C',City.getName());
    }
}