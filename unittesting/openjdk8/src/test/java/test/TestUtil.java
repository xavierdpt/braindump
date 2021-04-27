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

    public static void serDeser(Object duration) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(duration);
        Assert.assertNotNull(new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject());
    }

    public static boolean f() {
        return System.currentTimeMillis() < 0;
    }
}
