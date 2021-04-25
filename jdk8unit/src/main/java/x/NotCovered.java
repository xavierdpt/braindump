package x;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class NotCovered {
    public void notCovered() {
        for (Method declaredMethod : System.class.getDeclaredMethods()) {
            for (Class<?> exceptionType : declaredMethod.getExceptionTypes()) {
                for (Annotation annotation : exceptionType.getAnnotations()) {
                    System.out.println(annotation.hashCode());
                }
            }
        }
    }
}
