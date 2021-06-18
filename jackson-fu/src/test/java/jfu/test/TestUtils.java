package jfu.test;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.List;
import java.util.function.Predicate;

public class TestUtils {
  public static <T> T extractFirst(List<T> list, Predicate<T> predicate) {
    return list.stream()
        .filter(predicate)
        .findFirst()
        .orElse(null);
  }

  public static void dumpInfo(Object o) {
    for (Constructor<?> constructor : o.getClass().getDeclaredConstructors()) {
      int modifiers = constructor.getModifiers();
      if (Modifier.isPublic(modifiers)) {
        System.out.println(constructor.toGenericString());
      }
    }
    for (Method method : o.getClass().getDeclaredMethods()) {
      int modifiers = method.getModifiers();
      if (Modifier.isPublic(modifiers)) {
        System.out.println(method.toGenericString());
      }
    }
  }

  public static URL dummyURL() {
    URLStreamHandler x = Mockito.mock(URLStreamHandler.class);
    return new URL(null,"",x);
  }
}
