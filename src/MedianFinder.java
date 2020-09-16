import java.util.*;
public class MedianFinder {
//    List<Integer> lint = new LinkedList<>();
//    public MedianFinder() {
//
//    }

//    public void addNum(int num) {
////        最好的方法是从后往前移动，边移动边比较
//        for (int i=0;i<lint.size();i++){
//            if (num < lint.get(i)){
//                lint.add(i, num);
//                return;
//            }
//        }
//        lint.add(num);
//    }
//
//    public double findMedian() {
//        int mid = lint.size();
//        return mid % 2 == 0?(double)((lint.get(mid/2) + lint.get(mid/2 - 1)))/2:(double)lint.get(mid/2);
//    }

    static Comparator<Integer> cmp = new Comparator<Integer>() {
        public int compare(Integer e1, Integer e2) {
            return e2 - e1;
        }
    };
    Queue<Integer> A, B;
    public MedianFinder() {
        A = new PriorityQueue<>(); // 小顶堆，保存较大的一半
        B = new PriorityQueue<>((x, y) -> (y - x)); // 大顶堆，保存较小的一半
    }
    public void addNum(int num) {
        if (A.size() != B.size()){
            A.add(num);
            B.add(A.poll());
        }else {
            B.add(num);
            A.add(B.poll());
        }
    }
    public double findMedian() {
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }


}

