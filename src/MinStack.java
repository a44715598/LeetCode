import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class MinStack {

    Stack<Integer> sa = new Stack<>();
    Stack<Integer> sb = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        sa.push(x);
        if (sb.isEmpty())
            sb.push(x);
        else{
            if (x <= sb.peek())
                sb.push(x);
        }
    }

    public void pop() {
//        只能equals才能比较value
        if (sa.pop().equals(sb.peek()))
            sb.pop();
    }

    public int top() {
        return sa.peek();
    }

    public int min() {
        return sb.peek();
    }
}