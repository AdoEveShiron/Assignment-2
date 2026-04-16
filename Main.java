public class Main {
    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException("FAILED: " + message);
        } else {
            System.out.println("PASSED: " + message);
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> a = new MyArrayList<>();
        a.add(10);
        a.add(20);
        a.add(1, 15);
        check(a.size() == 3, "ArrayList size");
        check(a.get(1) == 15, "ArrayList get");

        a.set(1, 16);
        check(a.get(1) == 16, "ArrayList set");

        a.remove(0);
        check(a.size() == 2, "ArrayList remove by index");
        check(a.exists(20), "ArrayList exists");

        MyLinkedList<String> l = new MyLinkedList<>();
        l.add("A");
        l.add("C");
        l.add(1, "B");
        check(l.size() == 3, "LinkedList size");
        check("B".equals(l.get(1)), "LinkedList get");
        check(l.indexOf("C") == 2, "LinkedList indexOf");

        int indexForDelete = l.indexOf("B");
        if (indexForDelete != -1) l.remove(indexForDelete);
        check(l.size() == 2, "LinkedList remove element");

        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        check(stack.pop() == 3, "Stack pop");
        check(stack.peek() == 2, "Stack peek");

        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        check(queue.dequeue() == 5, "Queue dequeue");
        check(queue.peek() == 6, "Queue peek");

        System.out.println("All tests passed successfully!");
    }
}
