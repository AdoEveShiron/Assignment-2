public class MyStack<T> {
    private final MyLinkedList<T> list;

    public MyStack() {
        this.list = new MyLinkedList<>();
    }

    public void push(T element) {
        list.add(element);
    }

    public T pop() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return list.remove(list.size() - 1);
    }

    public T peek() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return list.get(list.size() - 1);
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
