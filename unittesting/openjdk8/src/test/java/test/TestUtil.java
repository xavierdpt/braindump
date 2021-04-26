package test;

public class TestUtil {
    public static Error notImplemented() {
        return new NoSuchMethodError("Not implemented");
    }
}
