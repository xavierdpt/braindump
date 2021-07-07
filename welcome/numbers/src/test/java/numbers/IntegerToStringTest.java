package numbers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IntegerToStringTest {

  public static final String TEST_PROP_EXISTS = "TEST_PROP_EXISTS";
  public static final String TEST_PROP_NOT_EXIST = "TEST_PROP_NOT_EXIST";

  @BeforeEach
  void beforeEach() {
    System.setProperty(TEST_PROP_EXISTS, "0xFF");
  }

  @AfterEach
  void afterEach() {
    System.clearProperty(TEST_PROP_EXISTS);
  }

  @Test
  void test() {

    // Integer to String

    Integer integer1 = 5;
    assertEquals("5", integer1.toString());

    // String to Integer

    Integer integer2 = Integer.valueOf("10");
    assertEquals(10, integer2);

    Integer integer3 = Integer.valueOf("10", 10);
    assertEquals(10, integer3);

    Integer integer4 = Integer.valueOf("10", 5);
    assertEquals(5, integer4);

    Integer integer5 = new Integer("5");
    assertEquals(5, integer5);

    Integer integer6 = Integer.decode("10");
    assertEquals(10, integer6);

    Integer integer7 = Integer.decode("0xFF");
    assertEquals(0xFF, integer7);

    Integer integer8 = Integer.decode("0XFF");
    assertEquals(0xFF, integer8);

    Integer integer9 = Integer.decode("#FF");
    assertEquals(0xFF, integer9);

    Integer integer10 = Integer.decode("010");
    assertEquals(8, integer10);

    Integer defaultValue11 = 0x8;
    Integer integer11 = Integer.getInteger(TEST_PROP_EXISTS, defaultValue11);
    assertEquals(0xFF, integer11);

    Integer defaultValue12 = 0x8;
    Integer integer12 = Integer.getInteger(TEST_PROP_NOT_EXIST, defaultValue12);
    assertEquals(0x8, integer12);

    int defaultValue13 = 0x8;
    Integer integer13 = Integer.getInteger(TEST_PROP_EXISTS, defaultValue13);
    assertEquals(0xFF, integer13);

    int defaultValue14 = 0x8;
    Integer integer14 = Integer.getInteger(TEST_PROP_NOT_EXIST, defaultValue14);
    assertEquals(0x8, integer14);

    Integer integer15 = Integer.getInteger(TEST_PROP_EXISTS);
    assertEquals(0xFF, integer15);

    Integer integer16 = Integer.getInteger(TEST_PROP_NOT_EXIST);
    assertNull(integer16);
  }

}
