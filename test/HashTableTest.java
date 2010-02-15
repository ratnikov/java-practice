import my.HashTable;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Map;

public class HashTableTest {
  class CustomKey {
    int hashCode;
    String name;

    CustomKey(String name, int hashCode) {
      this.name = name;
      this.hashCode = hashCode;
    }

    public int hashCode() { 
      return this.hashCode;
    }

    public String toString() { return this.name; }
  }

  @Test public void testTruth() {
    assertTrue("Should be true", true);
  }

  @Test public void clearingHash() {
    HashTable<String, Object> map = new HashTable<String, Object>();

    Object foo = new Object();

    map.put("foo", foo);

    assertEquals("Should initialize using specified values", 1, map.size());

    map.clear();

    assertEquals("Should not contain any more elements", 0, map.size());

    assertEquals("Should not locate the added elements", null, map.get("foo"));
  }

  @Test public void addElements() {
    HashTable<String, Object> map = new HashTable<String, Object>();

    Object foo = new Object();
    Object bar = new Object();

    map.clear();

    assertEquals(null, map.put("foo", foo));
    assertEquals(null, map.put("bar", bar));

    assertEquals("Should bump size again", 2, map.size());

    assertEquals("Should lookup the added element", foo, map.get("foo"));

    assertEquals("Should lookup bar as well", bar, map.get("bar"));
  }

  @Test public void replacingElement() { 
    HashTable<String, Object> map = new HashTable<String, Object>();

    Object foo = new Object();
    Object bar = new Object();

    assertEquals("Should return null for new element addition", null, map.put("foo", foo));

    assertEquals("Should contain foo", foo, map.get("foo"));

    assertEquals("Should return old value if replacing", foo, map.put("foo", bar));

    assertEquals("Should replace foo with bar", bar, map.get("foo"));

    assertEquals("Should not bump size", 1, map.size());
  }

  @Test public void addElementsSharingHashCode() {
    HashTable<CustomKey, Object> map = new HashTable<CustomKey, Object>();

    Object foo = new Object();
    Object bar = new Object();
    Object baz = new Object();

    CustomKey foo_key = new CustomKey("foo", 1024);
    CustomKey bar_key = new CustomKey("bar", 1025);
    CustomKey baz_key = new CustomKey("baz", 1025);

    map.put(foo_key, foo);
    map.put(bar_key, bar);
    map.put(baz_key, baz);

    assertEquals(3, map.size());

    assertEquals(foo, map.get(foo_key));
    assertEquals(bar, map.get(bar_key));
    assertEquals(baz, map.get(baz_key));
  }
}
