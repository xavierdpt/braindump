import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoxingTest {

  @Test
  void test() {

    int primitive = 5;
    Integer boxed = primitive;

    Assertions.assertEquals("Fint", f(primitive));
    Assertions.assertEquals("FInteger", f(boxed));

    Assertions.assertEquals("G", g(primitive));
    Assertions.assertEquals("G", g(boxed));

    Assertions.assertEquals("H", h(primitive));
    Assertions.assertEquals("H", h(boxed));
  }

  private String f(int x) {
    return "Fint";
  }

  private String f(Integer x) {
    return "FInteger";
  }

  private String g(int x) {
    return "G";
  }

  private String h(Integer x) {
    return "H";
  }
}
