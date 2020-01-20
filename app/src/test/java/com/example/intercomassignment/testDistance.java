package com.example.intercomassignment;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class testDistance {

    @Test
    public void testDistanceFrom1()
    {
        assertEquals("Distance is not correct",(Long)41L,CalculateDistance.distanceFrom("52.986375","-6.043701"));
    }

    @Test
    public void testDistanceFrom2()
    {
        assertEquals("Distance is not correct",(Long)188L,CalculateDistance.distanceFrom("52.3191841","-8.5072391"));
    }

    @Test
    public void testDistanceFrom3()
    {
        assertEquals("Distance is not correct",(Long)24L,CalculateDistance.distanceFrom("53.1229599","-6.2705202"));
    }
}
