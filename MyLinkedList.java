import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    private class MyNode {
        T element;
        MyNode next;
        MyNode prev;
        MyNode(T element) { this.element = element; }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    @Override
    public void addFirst(T item) {
        MyNode newNode = new MyNode(item);
        if (head == null) head = tail = newNode;
        else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode newNode = new MyNode(item);
        if (tail == null) head = tail = newNode;
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(T item) { addLast(item); }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) addFirst(item);
        else if (index == size) addLast(item);
        else {
            MyNode current = getNode(index);
            MyNode newNode = new MyNode(item);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    private MyNode getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        MyNode curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr;
    }

    @Override
    public T get(int index) { return getNode(index).element; }

    @Override
    public T getFirst() { return head.element; }

    @Override
    public T getLast() { return tail.element; }

    @Override
    public void set(int index, T item) { getNode(index).element = item; }

    @Override
    public void remove(int index) {
        MyNode node = getNode(index);
        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;
        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;
        size--;
    }

    @Override
    public void removeFirst() { remove(0); }

    @Override
    public void removeLast() { remove(size - 1); }

    @Override
    public void sort() {
        for (int i = 0; i < size; i++) {
            MyNode curr = head;
            while (curr != null && curr.next != null) {
                if (curr.element.compareTo(curr.next.element) > 0) {
                    T temp = curr.element;
                    curr.element = curr.next.element;
                    curr.next.element = temp;
                }
                curr = curr.next;
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        MyNode curr = head;
        for (int i = 0; i < size; i++) {
            if (curr.element.equals(object)) return i;
            curr = curr.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        MyNode curr = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (curr.element.equals(object)) return i;
            curr = curr.prev;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) { return indexOf(object) != -1; }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        MyNode curr = head;
        for (int i = 0; i < size; i++) {
            arr[i] = curr.element;
            curr = curr.next;
        }
        return arr;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode curr = head;
            public boolean hasNext() { return curr != null; }
            public T next() {
                T val = curr.element;
                curr = curr.next;
                return val;
            }
        };
    }
}