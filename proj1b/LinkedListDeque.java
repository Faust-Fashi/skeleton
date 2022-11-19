import java.util.LinkedList;

/*2022/11/16 10:58
 * by IDEA
 */
public class LinkedListDeque<T> extends LinkedList<T> implements Deque<T> {
    /*Sentinel nodes*/
    private Node frontSentinel, backSentinel;
    private int size;
    public LinkedListDeque() {
        frontSentinel = new Node(null, null, null);
        backSentinel = new Node(null, null, null);
        backSentinel.prev = frontSentinel;
        frontSentinel.next = backSentinel;
        size = 0;
    }
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    public int size() {
        return size;
    }
    @Override
    public void addFirst(T elem) {
        Node firstNode = frontSentinel.next;
        Node newNode = new Node(elem, frontSentinel, firstNode);
        firstNode.prev = newNode;
        frontSentinel.next = newNode;
        size += 1;
    }
    @Override
    public void addLast(T elem) {
        Node lastNode = backSentinel.prev;
        Node newNode = new Node(elem, lastNode, backSentinel);
        lastNode.next = newNode;
        backSentinel.prev = newNode;
        size += 1;
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node firstNode = frontSentinel.next;
        T ret = firstNode.item;
        firstNode = firstNode.next;
        frontSentinel.next = firstNode;
        firstNode.prev = frontSentinel;
        size -= 1;
        return ret;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node lastNode = backSentinel.prev;
        T ret = lastNode.item;
        lastNode = lastNode.prev;
        backSentinel.prev = lastNode;
        lastNode.next = backSentinel;
        size -= 1;
        return ret;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node ptr = frontSentinel.next;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        } return ptr.item;
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(frontSentinel.next, index);
    }
    private T getRecursiveHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getRecursiveHelper(n.next, index - 1);
    }
    @Override
    public void printDeque() {
        Node ptr = frontSentinel.next;
        while (ptr != backSentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }
    private class Node {
        private T item;
        private Node prev, next;
        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }

    }

//    public static void main(String[] args) {
//        LinkedListDeque list = new LinkedListDeque<Integer>();
//        list.addFirst(00);
//        list.addFirst(01);
//        list.addLast(-11);
//        list.printDeque();
//        System.out.println();
//        System.out.println(list.removeFirst());
//        list.printDeque();
//        System.out.println();
//        System.out.println(list.get(1));
//    }
}
