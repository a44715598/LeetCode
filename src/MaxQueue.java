import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class MaxQueue {

    private Deque<Integer> deque = new LinkedList<>();
    private Queue<Integer> queue = new LinkedList<>();
    public MaxQueue() {

    }

    public int max_value() {
        if (queue.isEmpty())
            return -1;
        return deque.getFirst();
    }

    public void push_back(int value) {
        queue.add(value);
        while (!deque.isEmpty() && deque.getLast() < value){
            deque.removeLast();
        }
        deque.add(value);
    }

    public int pop_front() {
        if (queue.isEmpty())
            return -1;
        if (queue.peek().equals(deque.getFirst()))
            deque.removeFirst();
        return queue.poll();
    }
}