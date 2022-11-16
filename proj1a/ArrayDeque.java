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
        front = back = 4;
    }
    private void resize(int origin_size){
        T[] a = (T[]) new Object[origin_size * 2];
        System.arraycopy(items, front, a, origin_size / 2, size);
        items = a;
        front = origin_size / 2;
        back = front + size - 1;
    }
    public void addFirst(T elem) {
        /*Out of Boundary*/
        if (size == items.length || front == 0) {
            resize(size);
        }
        front -= 1;
        items[front] = elem;
        size += 1;
    }
    public void addLast(T elem) {
        /*Out of Boundary*/
        if (size == items.length || back == items.length - 1) {
            resize(size);
        }
        if(items[back] == null){
            items[back] = elem;
            size += 1;
        } else {
            back += 1;
            items[back] = elem;
            size += 1;
        }
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
        return items[front + index];
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

    public static void main(String[] args) {
        ArrayDeque<Integer> list = new ArrayDeque<>();
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
        list.addFirst(0);
        list.removeFirst();
        list.addFirst(1);
        list.printDeque();
    }
}
