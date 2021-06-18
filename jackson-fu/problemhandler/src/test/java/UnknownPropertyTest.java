import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import x.types.ExampleType;
import x.types.ExampleTypeI;

import java.io.IOException;

class MainTest {

  @Test
  void test() throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    om.addHandler(createHandler());
    ExampleTypeI exampleTypeI = om.readValue("{\"something\":\"Hello\"}", ExampleType.class);
    Assertions.assertEquals("Hello", exampleTypeI.getMessage());
  }

  private DeserializationProblemHandler createHandler() {

    return new DeserializationProblemHandler() {

      @Override
      public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p,
          JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
        if ("something".equals(propertyName) && beanOrClass instanceof ExampleTypeI) {
          System.out.println("Using setMessage for " + propertyName + " on " + beanOrClass.getClass().getName());
          ((ExampleTypeI)beanOrClass).setMessage(p.getValueAsString());
          return true;
        } else {
          if (beanOrClass instanceof Class) {
            System.out.println("Ignoring property " + propertyName + " on " + ((Class<?>)beanOrClass).getName());
          } else {
            System.out.println("Ignoring property " + propertyName + " on " + beanOrClass.getClass().getName());
          }
          return true;
        }
      }

      @Override
      public Object handleWeirdKey(DeserializationContext ctxt, Class<?> rawKeyType, String keyValue,
          String failureMsg) throws IOException {
        System.out.println("B");
        return super.handleWeirdKey(ctxt, rawKeyType, keyValue, failureMsg);
      }

      @Override
      public Object handleWeirdStringValue(DeserializationContext ctxt, Class<?> targetType, String valueToConvert,
          String failureMsg) throws IOException {
        System.out.println("C");
        return super.handleWeirdStringValue(ctxt, targetType, valueToConvert, failureMsg);
      }

      @Override
      public Object handleWeirdNumberValue(DeserializationContext ctxt, Class<?> targetType, Number valueToConvert,
          String failureMsg) throws IOException {
        System.out.println("D");
        return super.handleWeirdNumberValue(ctxt, targetType, valueToConvert, failureMsg);
      }

      @Override
      public Object handleWeirdNativeValue(DeserializationContext ctxt, JavaType targetType, Object valueToConvert,
          JsonParser p) throws IOException {
        System.out.println("E");
        return super.handleWeirdNativeValue(ctxt, targetType, valueToConvert, p);
      }

      @Override
      public Object handleUnexpectedToken(DeserializationContext ctxt, JavaType targetType, JsonToken t, JsonParser p,
          String failureMsg) throws IOException {
        System.out.println("F");
        return super.handleUnexpectedToken(ctxt, targetType, t, p, failureMsg);
      }

      @Override
      public Object handleUnexpectedToken(DeserializationContext ctxt, Class<?> targetType, JsonToken t, JsonParser p,
          String failureMsg) throws IOException {
        System.out.println("G");
        return super.handleUnexpectedToken(ctxt, targetType, t, p, failureMsg);
      }

      @Override
      public Object handleInstantiationProblem(DeserializationContext ctxt, Class<?> instClass, Object argument,
          Throwable t) throws IOException {
        System.out.println("H");
        return super.handleInstantiationProblem(ctxt, instClass, argument, t);
      }

      @Override
      public JavaType handleUnknownTypeId(DeserializationContext ctxt, JavaType baseType, String subTypeId,
          TypeIdResolver idResolver, String failureMsg) throws IOException {
        System.out.println("J");
        return super.handleUnknownTypeId(ctxt, baseType, subTypeId, idResolver, failureMsg);
      }

      @Override
      public JavaType handleMissingTypeId(DeserializationContext ctxt, JavaType baseType, TypeIdResolver idResolver,
          String failureMsg) throws IOException {
        System.out.println("K");
        return super.handleMissingTypeId(ctxt, baseType, idResolver, failureMsg);
      }

    };

  }

}
