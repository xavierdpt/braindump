package jfu;

import com.fasterxml.jackson.core.JsonProcessingException;
import jfu.types.MySimpleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

class NextThingTest {
  @Test
  void test() throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    TypeReference<List<MySimpleType>> x = new TypeReference<List<MySimpleType>>() {
    };
    List<MySimpleType> result = om.readValue("[{\"message\":\"Hello World!\"}]", x);
    Assertions.assertEquals("Hello World!", result.get(0).getMessage());
  }
}
