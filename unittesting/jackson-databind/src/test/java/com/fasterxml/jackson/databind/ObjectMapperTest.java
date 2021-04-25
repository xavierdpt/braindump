package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ObjectMapperTest {

    @Test
    public void test() {
        assertNotNull(new ObjectMapper());
    }

}
