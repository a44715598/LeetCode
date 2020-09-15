import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    LinkedList<List<Integer>> llint = new LinkedList<>();
    LinkedList<Integer> lint = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum){
        if (root == null)
            return llint;
        recur(root, sum);
        return llint;
    }

    private void recur(TreeNode root, int sum) {
        if (root == null)
            return;
        sum -= root.val;
        lint.add(root.val);
        if (sum == 0 && root.left == null && root.right == null){
            llint.add(new ArrayList<>(lint));
            return;
        }
        recur(root.left, sum);
        recur(root.right, sum);
        lint.removeLast();
    }
}

