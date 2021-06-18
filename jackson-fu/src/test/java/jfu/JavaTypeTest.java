package jfu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.ResolvedRecursiveType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBase;
import com.fasterxml.jackson.databind.type.TypeFactory;

import jfu.types.MySimpleType;

public class NextThingTest {

  public static final Class<?>[] JSON_TYPES = new Class[] {
      ResolvedType.class, // base of JavaType (and what else ? TODO)
      JavaType.class, // abstract
      TypeBase.class,
      CollectionLikeType.class,
      PlaceholderForType.class,
      ArrayType.class,
      SimpleType.class, // Done
      ReferenceType.class,
      MapLikeType.class,
      MapType.class,
      ResolvedRecursiveType.class
  };

  @Test
  void testSimpleType() throws JsonProcessingException {

    ObjectMapper om = new ObjectMapper();

    String input = "{\"message\":\"Hello World!\"}";

    JavaType javaType = om.constructType(MySimpleType.class);
    assertEquals(javaType.getClass(), SimpleType.class);

    MySimpleType result = om.readValue(input, javaType);
    assertEquals("Hello World!", result.getMessage());

  }

  @Test
  void testReferenceType() throws JsonProcessingException {

    // by default, only works for AtomicReference
    ObjectMapper om = new ObjectMapper();
    TypeFactory typeFactory = om.getTypeFactory();

    String input = "{\"message\":\"Hello World!\"}";

    JavaType javaType = typeFactory.constructType(MySimpleType.class);
    assertEquals(javaType.getClass(), SimpleType.class);

    JavaType referenceType = typeFactory
        .constructReferenceType(AtomicReference.class, javaType);

    assertEquals(referenceType.getClass(), ReferenceType.class);

    AtomicReference<MySimpleType> result = om.readValue(input, referenceType);
    assertEquals("Hello World!", result.get().getMessage());

  }

  @Test
  void testArray() throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();

    String input = "[{\"message\":\"Hello World!\"}]";
    JavaType javaType = om.constructType(MySimpleType[].class);
    assertEquals(javaType.getClass(), ArrayType.class);

    MySimpleType[] result = om.readValue(input, javaType);
    assertEquals(1, result.length);
    assertEquals("Hello World!", result[0].getMessage());
  }

  @Test
  void testCollection() throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    TypeFactory typeFactory = om.getTypeFactory();

    String input = "[{\"message\":\"Hello World!\"}]";
    JavaType javaType = typeFactory.constructCollectionType(ArrayList.class, MySimpleType.class);
    assertEquals(javaType.getClass(), CollectionType.class);

    List<MySimpleType> result = om.readValue(input, javaType);
    assertEquals(1, result.size());
    assertEquals("Hello World!", result.get(0).getMessage());
  }

  @Test
  void testCollectionInterface() throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    TypeFactory typeFactory = om.getTypeFactory();

    String input = "[{\"message\":\"Hello World!\"}]";
    JavaType javaType = typeFactory.constructCollectionType(List.class, MySimpleType.class);
    assertEquals(javaType.getClass(), CollectionType.class);

    List<MySimpleType> result = om.readValue(input, javaType);
    assertEquals(ArrayList.class, result.getClass());
    assertEquals(1, result.size());
    assertEquals("Hello World!", result.get(0).getMessage());
  }

  @Test
  void testMapInterface() throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    TypeFactory typeFactory = om.getTypeFactory();

    String input = "{\"hw\":{\"message\":\"Hello World!\"}}";
    JavaType javaType = typeFactory.constructMapType(Map.class, String.class, MySimpleType.class);
    assertEquals(javaType.getClass(), MapType.class);

    Map<String, MySimpleType> result = om.readValue(input, javaType);
    assertEquals(LinkedHashMap.class, result.getClass());
    assertEquals(1, result.size());
    assertEquals("Hello World!", result.get("hw").getMessage());
  }
}
