import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isStatic;

public class TestUtils {

  public static final ConstructorComparator CONSTRUCTOR_COMPARATOR = new ConstructorComparator();
  public static final MethodComparator METHOD_COMPARATOR = new MethodComparator();
  public static final FieldComparator FIELD_COMPARATOR = new FieldComparator();

  public static void dumpClass(Class<?> clazz) {

    List<Constructor<?>> constructors = new ArrayList<>();
    for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
      if (isPrivate(constructor.getModifiers())) {
        continue;
      }
      constructors.add(constructor);
    }
    constructors.sort(CONSTRUCTOR_COMPARATOR);

    List<Method> methods = new ArrayList<>();
    for (Method method : clazz.getDeclaredMethods()) {
      if (isPrivate(method.getModifiers())) {
        continue;
      }
      methods.add(method);
    }
    methods.sort(METHOD_COMPARATOR);

    List<Field> fields = new ArrayList<>();
    for (Field field : clazz.getDeclaredFields()) {
      if (isPrivate(field.getModifiers())) {
        continue;
      }
      fields.add(field);
    }
    fields.sort(FIELD_COMPARATOR);

    System.out.println("--- Constructors ---");
    for (Constructor<?> constructor : constructors) {
      System.out.println(constructor.toGenericString());
    }
    System.out.println("--- Fields ---");
    for (Field field : fields) {
      System.out.println(field.toGenericString());
    }
    System.out.println("--- Methods ---");
    for (Method method : methods) {
      System.out.println(method.toGenericString());
    }
  }

  private static class ConstructorComparator implements java.util.Comparator<Constructor<?>> {
    @Override
    public int compare(Constructor<?> o1, Constructor<?> o2) {
      if (o1 == o2) {
        return 0;
      }
      int r = o1.getName().compareTo(o2.getName());
      if (r == 0) {
        return o1.toGenericString().compareTo(o2.toGenericString());
      } else {
        return r;
      }
    }
  }

  private static class MethodComparator implements java.util.Comparator<Method> {
    @Override
    public int compare(Method o1, Method o2) {
      if (o1 == o2) {
        return 0;
      }
      int staticCmp = Boolean.compare(isStatic(o1.getModifiers()), isStatic(o2.getModifiers()));
      if (staticCmp != 0) {
        return staticCmp;
      }
      int r = o1.getName().compareTo(o2.getName());
      if (r == 0) {
        return o1.toGenericString().compareTo(o2.toGenericString());
      } else {
        return r;
      }
    }
  }

  private static class FieldComparator implements java.util.Comparator<Field> {
    @Override
    public int compare(Field o1, Field o2) {
      if (o1 == o2) {
        return 0;
      }
      int staticCmp = Boolean.compare(isStatic(o1.getModifiers()), isStatic(o2.getModifiers()));
      if (staticCmp != 0) {
        return staticCmp;
      }
      int r = o1.getName().compareTo(o2.getName());
      if (r == 0) {
        return o1.toGenericString().compareTo(o2.toGenericString());
      } else {
        return r;
      }
    }
  }
}
