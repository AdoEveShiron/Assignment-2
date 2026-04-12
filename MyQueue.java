public class MyQueue<T extends Comparable<T>> {
    private MyLinkedList<T> list = new MyLinkedList<>();
    public void enqueue(T item) { list.addLast(item); }
    public T dequeue() { T val = list.getFirst(); list.removeFirst(); return val; }
    public T peek() { return list.getFirst(); }
}