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
    private void resize(int origin_size){
        T[] a = (T[]) new Object[origin_size * 2];
        System.arraycopy(items, front + 1, a, origin_size / 2, size);
        items = a;
        front = origin_size / 2 - 1;
        back = front + size + 1;
    }
    public void addFirst(T elem) {
        /*Out of Boundary*/
        if (size == items.length || front == 0) {
            resize(size);
        }
        items[front] = elem;
        front -= 1;
        size += 1;
    }
    public void addLast(T elem) {
        /*Out of Boundary*/
        if (size == items.length || back == items.length - 1) {
            resize(size);
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
        T ret = items[front];
        items[front] = null;
        front += 1;
        size -= 1;
        /*Too sparse*/
        if (size != 0 && 1.0 * size / items.length < 0.5) {
            resize(size);
        }
        return ret;
    }
    public T removeLast() {
        /*Out of Boundary*/
        if (isEmpty()) {
            return null;
        }
        T ret = items[back];
        items[back] = null;
        back -= 1;
        size -= 1;
        /*Too sparse*/
        if (size != 0 && 1.0 * size / items.length < 0.5) {
            resize(size);
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
    public void printDeque(){
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> list = new ArrayDeque<>();
//        list.addFirst(0);
//        list.addFirst(-1);
//        list.addLast(1);
//        list.addFirst(-2);
//        list.addLast(2);
//        list.printDeque();
//        for (int i = 0; i < 4; i++) {
//            list.addFirst(-3 - i);
//            list.addLast(3 + i);
//        } list.printDeque();
//        list.removeFirst();
//        list.removeFirst();
//        list.removeLast();
//        list.printDeque();
//        list.addFirst(0);
//        list.removeFirst();
//        System.out.println(list.isEmpty());
//        list.addFirst(3);
//        list.printDeque();
//
//        list = new ArrayDeque<Integer>();
//        list.addFirst(0);
//        list.addFirst(1);
//        list.removeLast();
//        System.out.println(list.get(0));
//    }
}
