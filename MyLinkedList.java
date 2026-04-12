import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private class MyNode {
        T element;
        MyNode next;
        MyNode prev;

        MyNode(T element) {
            this.element = element;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private MyNode nodeAt(int index) {
        checkIndex(index);

        if (index < size / 2) {
            MyNode current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            MyNode current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    private MyNode findNode(T element) {
        MyNode current = head;
        while (current != null) {
            if (element == null) {
                if (current.element == null) {
                    return current;
                }
            } else {
                if (element.equals(current.element)) {
                    return current;
                }
            }
            current = current.next;
        }
        return null;
    }

    private T unlink(MyNode node) {
        MyNode prev = node.prev;
        MyNode next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

        T value = node.element;

        node.prev = null;
        node.next = null;
        node.element = null;

        size--;
        return value;
    }

    private void addFirst(T element) {
        MyNode newNode = new MyNode(element);

        if (size == 0) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    private void addLast(T element) {
        MyNode newNode = new MyNode(element);

        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    @Override
    public void add(T element) {
        addLast(element);
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }

        MyNode current = nodeAt(index);
        MyNode previous = current.prev;
        MyNode newNode = new MyNode(element);

        newNode.prev = previous;
        newNode.next = current;
        previous.next = newNode;
        current.prev = newNode;

        size++;
    }

    @Override
    public T get(int index) {
        return nodeAt(index).element;
    }

    @Override
    public T set(int index, T element) {
        MyNode current = nodeAt(index);
        T old = current.element;
        current.element = element;
        return old;
    }

    @Override
    public T remove(int index) {
        MyNode current = nodeAt(index);
        return unlink(current);
    }

    @Override
    public boolean remove(T element) {
        MyNode node = findNode(element);
        if (node == null) {
            return false;
        }
        unlink(node);
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        MyNode current = head;
        while (current != null) {
            MyNode next = current.next;
            current.prev = null;
            current.next = null;
            current.element = null;
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(T element) {
        int index = 0;
        MyNode current = head;

        while (current != null) {
            if (element == null) {
                if (current.element == null) {
                    return index;
                }
            } else {
                if (element.equals(current.element)) {
                    return index;
                }
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("No more elements");
                }
                T value = current.element;
                current = current.next;
                return value;
            }
        };
    }
}
