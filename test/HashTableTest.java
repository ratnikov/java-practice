import my.HashTable;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Map;

public class HashTableTest {
  // change to Mao once full Map interface is supported
  HashTable<String, Object> map;

  @Before public void setUp() {
    map = new HashTable<String, Object>();
  }

  @Test public void testTruth() {
    assertTrue("Should be true", true);
  }

  @Test public void clearingHash() {
    Object foo = new Object();

    map.put("foo", foo);

    assertEquals("Should initialize using specified values", 1, map.size());

    map.clear();

    assertEquals("Should not contain any more elements", 0, map.size());

    assertEquals("Should not locate the added elements", null, map.get("foo"));
  }

  @Test public void addElement() {
    Object foo = new Object();

    map.clear();

    map.put("foo", foo);

    assertEquals("Should bump size by one", 1, map.size());
    assertEquals("Should lookup the added element", foo, map.get("foo"));
  }

  @Test public void replacingElement() { 
    Object foo = new Object();
    Object bar = new Object();

    assertEquals("Should return null for new element addition", null, map.put("foo", foo));

    assertEquals("Should contain foo", foo, map.get("foo"));

    assertEquals("Should return old value if replacing", foo, map.put("foo", bar));

    assertEquals("Should replace foo with bar", bar, map.get("foo"));

    assertEquals("Should not bump size", 1, map.size());
  }
}
