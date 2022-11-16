/*2022/11/16 10:58
 * by IDEA
 */
public class LinkedListDeque <TYPE>{
    /*Sentinel nodes*/
    private Node frontSentinel, backSentinel;
    private int size;
    public LinkedListDeque(){
        frontSentinel = new Node(null, null, null);
        backSentinel = new Node(null, null, null);
        backSentinel.prev = frontSentinel;
        frontSentinel.next = backSentinel;
        size = 0;
    }
    public boolean isEmpty(){
        return (size == 0);
    }
    public int size(){
        return size;
    }
    public void addFirst(TYPE elem){
        Node firstNode = frontSentinel.next;
        Node newNode = new Node(elem, frontSentinel, firstNode);
        firstNode.prev = newNode;
        frontSentinel.next = newNode;
        size += 1;
    }
    public void addLast(TYPE elem){
        Node lastNode = backSentinel.prev;
        Node newNode = new Node(elem, lastNode, backSentinel);
        lastNode.next = newNode;
        backSentinel.prev = newNode;
        size += 1;
    }
    public TYPE removeFirst(){
        if(isEmpty())
            return null;
        Node firstNode = frontSentinel.next;
        TYPE ret = firstNode.item;
        firstNode = firstNode.next;
        firstNode.prev = frontSentinel;
        size -= 1;
        return ret;
    }
    public TYPE removeLast(){
        if(isEmpty())
            return null;
        Node lastNode = backSentinel.prev;
        TYPE ret = lastNode.item;
        lastNode = lastNode.prev;
        lastNode.next = backSentinel;
        size -= 1;
        return ret;
    }
    public TYPE get(int index){
        if(index >= size)
            return null;
        Node ptr = frontSentinel;
        for (int i = 0; i < size; i++) {
            ptr = ptr.next;
        }return ptr.item;
    }
    public TYPE getRecursive(int index){
        if(index >= size)
            return null;
        Node ptr = frontSentinel;
        while(ptr != backSentinel){
            ptr = ptr.next;
        }return ptr.item;
    }
    public void printDeque(){
        Node ptr = frontSentinel.next;
        while(ptr != backSentinel){
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }
    private class Node{
        public TYPE item;
        public Node prev, next;
        private Node(TYPE i, Node p, Node n){
            item = i;
            prev = p;
            next = n;
        }

    }

    public static void main(String[] args) {
        LinkedListDeque list = new LinkedListDeque<Integer>();
        list.addFirst(00);
        list.addFirst(01);
        list.addLast(-11);
        list.printDeque();
    }
}
