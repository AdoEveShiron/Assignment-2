public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> list = new MyArrayList<>();

    public void add(T item) {
        if (item == null) throw new IllegalArgumentException("Null not allowed");
        list.addLast(item);
        siftUp(list.size() - 1);
    }

    public T peek() {
        if (list.size() == 0) return null;
        return list.get(0);
    }

    public T poll() {
        if (list.size() == 0) return null;
        T root = list.get(0);
        T lastItem = list.get(list.size() - 1);
        list.removeLast();

        if (list.size() > 0) {
            list.set(0, lastItem);
            siftDown(0);
        }
        return root;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (list.get(index).compareTo(list.get(parent)) >= 0) break;
            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < list.size() && list.get(left).compareTo(list.get(smallest)) < 0) smallest = left;
            if (right < list.size() && list.get(right).compareTo(list.get(smallest)) < 0) smallest = right;

            if (smallest == index) break;
            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public int size() {
        return list.size();
    }
}