package my;

import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class HashTable<K, V> {
  private static final int INIT_BUCKET_SIZE = 128;

  class Bucket {
    List<Node> nodes = new ArrayList<Node>();

    class Node {
      K key;
      V value;

      Node(K key, V value) {
	this.key = key;
	this.value = value;
      }
    }

    void clear() {
      this.nodes.clear();
    }

    V set(K key, V value) {
      Node node = findNode(key);

      if (node != null) {
	// replace the value of the node with the new value
	
	V replacedValue = node.value;
	node.value = value;
	return replacedValue;
      } else {
	// add a new node with the key
	this.nodes.add(new Node(key, value));

	return null;
      }
    }

    V get(K key) {
      Node node = findNode(key);

      if (node != null) {
	return node.value;
      } else {
	return null;
      }
    }

    V remove(K key) {
      Node node = findNode(key);

      if (node != null) {
	// remove the node from the known nodes and return the value
	this.nodes.remove(node);

	return node.value;
      } else {
	return null;
      }
    }

    Collection<V> values() {
      List<V> values = new ArrayList<V>(this.nodes.size());

      for (Node node : this.nodes) {
	values.add(node.value);
      }

      return values;
    }

    Node findNode(K key) {
      for (Node node : this.nodes) {
	if (node.key == key) {
	  return node;
	}
      }

      return null;
    }
  }

  List< Bucket > buckets = new ArrayList< Bucket >(INIT_BUCKET_SIZE);
  int elementCount = 0;

  public HashTable() {
    // poppulate the list with nil buckets
    for (int i=0; i < INIT_BUCKET_SIZE; i++) {
      this.buckets.add(new Bucket());
    }
  }

  public void clear() {
    this.elementCount = 0;

    for (Bucket bucket : this.buckets) {
      bucket.clear();
    }
  }

  public boolean containsKey(K key) {
    return get(key) != null;
  }

  public boolean containsValue(V value) {
    return values().contains(value);
  }

  public V get(K key) {
    return getBucket(key).get(key);
  }

  public V put(K key, V value) {
    V replacedValue = getBucket(key).set(key, value);

    // bump the element count if the value was replaced
    if (replacedValue == null) {
      this.elementCount++;
    }
     
    return replacedValue;
  }

  public V remove(K key) {
    V removed = getBucket(key).remove(key);

    // need to keep track of the element count if it was removed
    if (removed != null) {
      this.elementCount--;
    }

    return removed;
  }

  public int size() {
    return this.elementCount;
  }

  public boolean isEmpty() {
    return this.size() == 0;
  }

  public Collection<V> values() {
    List<V> values = new ArrayList<V>(this.size());

    for (Bucket bucket : this.buckets) {
      values.addAll(bucket.values());
    }

    return values;
  }

  /**
   * Returns a bucket corresponding to the key.
   * If the bucket was not created yet, creates it.
   *
   * @param key The key to locate the bucket for.
   * @param init If <code>true</code>, initializes the bucket if necessary.
   */
  private Bucket getBucket(K key) {
    int bucketIndex = key.hashCode() % this.buckets.size();

    return this.buckets.get(bucketIndex);
  }
}
