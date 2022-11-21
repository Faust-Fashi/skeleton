package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int diff = key.compareTo(p.key);
        if (diff == 0) {
            return p.value;
        } else if (diff > 0) {
            return getHelper(key, p.right);
        } else {
            return getHelper(key, p.left);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, this.root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        } else {
            int diff = key.compareTo(p.key);
            if (diff == 0) {
                p.value = value;
            } else if (diff > 0) {
                p.right = putHelper(key, value, p.right);
            } else {
                p.left = putHelper(key, value, p.left);
            }
            return p;
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        this.root = putHelper(key, value, this.root);
        this.size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> sets = new HashSet<>();
        traverse(this.root, sets);
        return sets;
    }
    private void traverse(Node node, Set<K> keys) {
        if (node == null) {
            return;
        } else {
            keys.add(node.key);
            traverse(node.left, keys);
            traverse(node.right, keys);
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        if (value == null) {
            return null;
        }
        this.root = removeHelper(key, this.root);
        this.size -= 1;
        return value;
    }
    private Node removeHelper(K key, Node node) {
        if (node == null) {
            return null;
        }
        int diff = key.compareTo(node.key);
        if (diff < 0) {
            node.left = removeHelper(key, node.left);
        } else if (diff > 0) {
            node.right = removeHelper(key, node.right);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            Node newNode = findMin(node.right);
            newNode.right = removeMin(node.right);
            newNode.left = node.left;
            node = newNode;
        }
        return node;
    }
    private Node findMin(Node node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }
    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node.right;
        } else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V ret = remove(key);
        return ret;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
