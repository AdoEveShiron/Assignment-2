public class MyMinHeap<T extends Comparable<T>> {
    private final MyArrayList<T> list;

    public MyMinHeap() {
        this.list = new MyArrayList<>();
    }

    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (list.get(index).compareTo(list.get(parent)) >= 0) {
                break;
            }

            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < list.size() && list.get(left).compareTo(list.get(smallest)) < 0) {
                smallest = left;
            }

            if (right < list.size() && list.get(right).compareTo(list.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Heap does not support null");
        }

        list.add(element);
        siftUp(list.size() - 1);
    }

    public T peek() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return list.get(0);
    }

    public T poll() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        T root = list.get(0);
        T last = list.remove(list.size() - 1);

        if (!list.isEmpty()) {
            list.set(0, last);
            siftDown(0);
        }

        return root;
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