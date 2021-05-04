package test;

import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;

public class TestUtil {
    public static Error notImplemented() {
        return new NoSuchMethodError("Not implemented");
    }

    public static <T> void serDeser(T input) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(input);
        T output = (T) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
        Assert.assertEquals(input, output);
    }

    public static boolean f() {
        return System.currentTimeMillis() < 0;
    }

    public static boolean verbose() {
        String trueString = Boolean.TRUE.toString();
        return trueString.equals(System.getProperty("VERBOSE_TESTS", trueString));
    }
}
