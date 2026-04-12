public class MyQueue<T> {
    private final MyLinkedList<T> list;

    public MyQueue() {
        this.list = new MyLinkedList<>();
    }

    public void enqueue(T element) {
        list.add(element);
    }

    public T dequeue() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        return list.remove(0);
    }

    public T peek() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }
}
