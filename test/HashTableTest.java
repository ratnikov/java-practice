import my.HashTable;

import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.Map;
import java.util.Collection;

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

  @Test public void testSize() {
    HashTable<String, Object> map = new HashTable<String, Object>();

    assertTrue(map.isEmpty());
    assertEquals(0, map.size());

    map.put("foo", new Object());

    assertFalse(map.isEmpty());
    assertEquals(1, map.size());
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

  @Test public void containElements() {
    HashTable<String, Object> map = new HashTable<String, Object>();

    Object foo = new Object();

    map.put("foo", foo);

    assertTrue("Should contain added element key", map.containsKey("foo"));
    assertTrue("Should contain added element value", map.containsValue(foo));

    assertFalse("Should not contain un-added key", map.containsKey("bar"));
    assertFalse("Should not contain un-added value", map.containsValue(new Object()));
  }
  
  @Test public void removeElements() {
    HashTable<String, Object> map = new HashTable<String, Object>();

    Object foo = new Object();

    map.put("foo", foo);

    assertEquals(foo, map.get("foo"));

    assertEquals("Should return null if removing a non-existing item", null, map.remove("bar"));
    assertEquals("Should not change size", 1, map.size());

    assertEquals("Should return the removed item", foo, map.remove("foo"));

    assertFalse("Should not contain the key anymore", map.containsKey("foo"));
    assertEquals("Should decrement the size", 0, map.size());
  }

  @Test public void returnsValues() {
    HashTable<String, Object> map = new HashTable<String, Object>();

    Object foo = new Object();
    Object bar = new Object();

    map.put("foo", foo);
    map.put("bar", bar);

    Collection<Object> values = map.values();

    assertThat(values, hasItem(foo));
    assertThat(values, hasItem(bar));
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
