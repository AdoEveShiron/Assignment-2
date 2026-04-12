public class MyStack<T extends Comparable<T>> {
    private MyLinkedList<T> list = new MyLinkedList<>();
    public void push(T item) { list.addFirst(item); }
    public T pop() { T val = list.getFirst(); list.removeFirst(); return val; }
    public T peek() { return list.getFirst(); }
}