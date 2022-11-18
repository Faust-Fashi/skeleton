/*2022/11/16 15:07
 * by IDEA
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int front, back;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 3;
        back = 4;
    }
    private void inflact(int originSize) {
        int newSize = originSize > 4 ? originSize : 4;
        T[] a = (T[]) new Object[newSize * 2 + 1];
        System.arraycopy(items, front + 1, a, newSize / 2, size);
        items = a;
        front = newSize / 2 - 1;
        back = front + size + 1;
    }
    private void deflact(int originSize) {
        T[] a = (T[]) new Object[originSize + 2];
        System.arraycopy(items, front + 1, a, 1, size);
        items = a;
        front = 0;
        back = front + size + 1;
    }
    public void addFirst(T elem) {
        /*Out of Boundary*/
        if (size == items.length || front <= 0) {
            inflact(size);
        }
        items[front] = elem;
        front -= 1;
        size += 1;
    }
    public void addLast(T elem) {
        /*Out of Boundary*/
        if (size == items.length || back >= items.length - 1) {
            inflact(size);
        }
        items[back] = elem;
        back += 1;
        size += 1;
    }

    public T removeFirst() {
        /*Out of Boundary*/
        if (isEmpty()) {
            return null;
        }
        front += 1;
        T ret = items[front];
        items[front] = null;
        size -= 1;
        /*Too sparse*/
        if (size != 0 && items.length > 8 && 1.0 * size / items.length < 0.5) {
            deflact(size);
        }
        return ret;
    }
    public T removeLast() {
        /*Out of Boundary*/
        if (isEmpty()) {
            return null;
        }
        back -= 1;
        T ret = items[back];
        items[back] = null;
        size -= 1;
        /*Too sparse*/
        if (size != 0 && items.length > 8 && 1.0 * size / items.length < 0.5) {
            deflact(size);
        }
        return ret;
    }
    public T get(int index) {
        return items[front + index + 1];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        // d003 add/remove/isEmpty
//        list.addFirst(0);
//        System.out.println(list.removeLast());
//        list.addFirst(2);
//        System.out.println(list.removeLast());
//        list.addFirst(4);
//        list.addFirst(5);
        list.addFirst(0);
        list.get(0);
        list.removeLast();
        list.addLast(3);
        list.addLast(4);
        list.addFirst(5);
        list.removeLast();
        list.removeLast();
        list.get(0);
        list.get(0);
        list.addFirst(10);
        list.removeFirst();
        list.removeLast();
        list.addFirst(13);
        list.addFirst(14);
    }
}
