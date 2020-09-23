import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.util.*;

public class JZPractice {
    public double new21Game(int N, int K, int W) {
        return 1;
    }

    //    62
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[n - 1];
    }

    //    5 dp
    public String longestPalindrom1(String s) {
        int len = s.length();
        if (len == 0)
            return s;
        boolean[][] dp = new boolean[len][len];
        char[] s_array = s.toCharArray();
        int max = 0;
        int left = 0, right = 0;
        for (int i = 0; i < len; i++)
            dp[i][i] = true;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = dp[i + 1][j - 1] && s_array[i] == s_array[j];
                if (j - i < 3)
                    dp[i][j] = s_array[i] == s_array[j];
                if (dp[i][j] && j - i + 1 > max) {
                    left = i;
                    right = j;
                    max = j - i + 1;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    //    5 暴力
    public String longestPalindrome2(String s) {
        int len = s.length();
        int max = 0;
        int left = 0, right = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                StringBuilder sb = new StringBuilder(s.substring(i, j + 1));
                if (sb.toString().equals(sb.reverse().toString()) && j - i + 1 > max) {
                    max = j - i + 1;
                    left = i;
                    right = j + 1;
                }
            }
        }
        return s.substring(left, right);
    }

    //    5 中间扩散
    public String longestPalindrome(String s) {
        int len = s.length();
        int left = 0, right = 0;
        if (len == 1)
            return s;
        String ans = "";
        int max = 0;
        for (int i = 0; i < len - 1; i++) {
            int len1 = checkPalindrome(s, i, i);
            int len2 = checkPalindrome(s, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > max) {
                max = len1;
                ans = s.substring(i - len1 / 2 + (len1 + 1) % 2, i + 1 + len1 / 2);
            }
        }
        return ans;
    }

    public int checkPalindrome(String s, int i, int j) {
        int len = s.length();
        if (s.charAt(i) != s.charAt(j))
            return 1;
        while (i >= 0 && j < len) {
            if (s.charAt(i--) != s.charAt(j++))
                return j - i - 3;
        }
        return j - i - 1;
    }


    //    剑指03
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != -1) {
                if (nums[nums[i]] == -1) {
                    return nums[i];
                }
                int temp = nums[nums[i]];
                nums[nums[i]] = -1;
                if (nums[i] != -1)
                    nums[i] = temp;
            }
        }
        return 0;
    }

    public void out_list2(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public void out_list2(String[] nums) {
        for (String num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    //    剑指04
    public boolean findNumberIn2DArray1(int[][] matrix, int target) {

        if (matrix.length == 0 || matrix[0].length == 0 || target < matrix[0][0])
            return false;
        int left = 0;
        int right = matrix[0].length - 1;
        int mid = 0;
//        二分找行位置
        while (left <= right) {
            mid = (left + right) / 2;
            if (matrix[0][mid] > target) {
                right = mid - 1;
            } else if (matrix[0][mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        int mid_pos = left == matrix[0].length ? right : left - 1;

        left = 0;
        right = matrix.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (matrix[mid][mid_pos] > target) {
                right = mid - 1;
            } else if (matrix[mid][mid_pos] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
//二分找列位置
        left = 0;
        right = matrix.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (matrix[mid][0] > target) {
                right = mid - 1;
            } else if (matrix[mid][0] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        mid_pos = left == matrix.length ? right : left - 1;

        left = 0;
        right = matrix[0].length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (matrix[mid_pos][mid] > target) {
                right = mid - 1;
            } else if (matrix[mid_pos][mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int i = 0;
        int j = matrix[0].length - 1;
        while (true) {
            if (target > matrix[i][j]) {
                i++;
            } else if (target < matrix[i][j]) {
                j--;
            } else
                return true;

            if (i == m || j < 0) {
                return false;
            }
        }
    }

    //    剑指05
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                sb.replace(i, i + 1, "%20");
            }
        }
        return sb.toString();
    }

//    剑指06

//    public int[] reversePrint(ListNode head) {
//        Stack<Integer> stack = new Stack<>();
//        List<Integer> ans= new ArrayList<>();
//        while (head.val > 0){
//            stack.push(head.val);
//            head = head.next;
//        }
//        while (!stack.isEmpty()){
//            ans.add(stack.pop());
//        }
//        int []ans2 = new int[ans.size()];
//        for (int i=0;i<ans.size();i++){
//            ans2[i] = ans.get(i);
//        }
//        return ans2;
//    }

//    剑指11

    public int minArray(int[] numbers) {
        int len = numbers.length;
        if (len == 0) {
            return 0;
        }
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (numbers[mid] > numbers[right]) {
                // [3, 4, 5, 1, 2]，mid 以及 mid 的左边一定不是最小数字
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else if (numbers[mid] == numbers[right]) {
                // 只能把 right 排除掉，下一轮搜索区间是 [left, right - 1]
                right = right - 1;
            } else {
                // 此时 numbers[mid] < numbers[right]
                // mid 的右边一定不是最小数字，mid 有可能是，下一轮搜索区间是 [left, mid]
                right = mid;
            }
        }

        // 最小数字一定在数组中，因此不用后处理
        return numbers[left];
    }

    //    剑指10
    public int numWays(int n) {
        int[] f = {1, 1, 2};
        int f1 = 1, f2 = 2, f3 = 0;
        for (int i = 3; i <= n; i++) {
            f3 = (f1 + f2) % 1000000007;
            f1 = f2;
            f2 = f3;
        }
        return n > 2 ? f3 : f[n];
    }

    public int fib(int n) {
        int[] f = {0, 1};
        int f1 = 0, f2 = 1, f3 = 1;
        for (int i = 2; i <= n; i++) {
            f3 = (f1 + f2) % 1000000007;
            f1 = f2;
            f2 = f3;
        }
        return n > 1 ? f3 : f[n];
    }

    //    剑指07
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preorder_list = new ArrayList<>();
        List<Integer> inorder_list = new ArrayList<>();
        for (int i = 0; i < inorder.length; i++) {
            preorder_list.add(preorder[i]);
            inorder_list.add(inorder[i]);
        }
        return helper(preorder_list, inorder_list);
    }

    private TreeNode helper(List<Integer> preorder_list, List<Integer> inorder_list) {
        if (inorder_list.size() == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder_list.remove(0));
        int mid = inorder_list.indexOf(root.val);
        root.left = helper(preorder_list, inorder_list.subList(0, mid));
        root.right = helper(preorder_list, inorder_list.subList(mid + 1, inorder_list.size()));

        return root;
    }

    //    剑指13
    public int movingCountBFS(int m, int n, int k) {
        int[][] nums = new int[m][n];
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        int[] cord = {0, 0};
        q.add(cord);
        int x = 0, y = 0;
        while (!q.isEmpty()) {
            cord = q.poll();
            x = cord[0];
            y = cord[1];
            nums[x][y] = 1;
            count++;
            if (x + 1 < m && nums[x + 1][y] != 1 && nums[x + 1][y] != -1) {
                if (checkValToK(x + 1, y, k))
                    q.add(new int[]{x + 1, y});
                nums[x + 1][y] = -1;
            }

            if (x - 1 > -1 && nums[x - 1][y] != 1 && nums[x - 1][y] != -1 && checkValToK(x - 1, y, k)) {
                if (checkValToK(x - 1, y, k))
                    q.add(new int[]{x - 1, y});
                nums[x - 1][y] = -1;
            }
            if (y + 1 < n && nums[x][y + 1] != 1 && nums[x][y + 1] != -1 && checkValToK(x, y + 1, k)) {
                if (checkValToK(x, y + 1, k))
                    q.add(new int[]{x, y + 1});
                nums[x][y + 1] = -1;
            }
            if (y - 1 > -1 && nums[x][y - 1] != 1 && nums[x][y - 1] != -1 && checkValToK(x, y - 1, k)) {
                if (checkValToK(x, y - 1, k))
                    q.add(new int[]{x, y - 1});
                nums[x][y - 1] = -1;
            }
        }
        return count;
    }

    public int movingCount(int m, int n, int k) {
        int[][] nums = new int[m][n];
        int count = 0;
        Stack<int[]> s = new Stack<>();
        int[] cord = {0, 0};
        s.push(cord);
        int x = 0, y = 0;
        nums[0][0] = 1;
        while (!s.isEmpty()) {
            cord = s.peek();
            x = cord[0];
            y = cord[1];
            if (x + 1 < m && nums[x + 1][y] != 1 && nums[x + 1][y] != -1) {
                if (checkValToK(x + 1, y, k)) {
                    s.push(new int[]{x + 1, y});
                    nums[x + 1][y] = 1;
                    continue;
                }
                nums[x + 1][y] = -1;
            }

            if (x - 1 > -1 && nums[x - 1][y] != 1 && nums[x - 1][y] != -1) {
                if (checkValToK(x - 1, y, k)) {
                    s.push(new int[]{x - 1, y});
                    nums[x - 1][y] = 1;
                    continue;
                }
                nums[x - 1][y] = -1;
            }
            if (y + 1 < n && nums[x][y + 1] != 1 && nums[x][y + 1] != -1) {
                if (checkValToK(x, y + 1, k)) {
                    s.push(new int[]{x, y + 1});
                    nums[x][y + 1] = 1;
                    continue;
                }
                nums[x][y + 1] = -1;
            }
            if (y - 1 > -1 && nums[x][y - 1] != 1 && nums[x][y - 1] != -1) {
                if (checkValToK(x, y - 1, k)) {
                    s.push(new int[]{x, y - 1});
                    nums[x][y - 1] = 1;
                    continue;
                }
                nums[x][y - 1] = -1;
            }
            count++;
            s.pop();
        }
        return count;
    }

    private boolean checkValToK(int x, int y, int k) {
        String sx = String.valueOf(x);
        String sy = String.valueOf(y);
        int sum = 0;
        for (int i = 0; i < sx.length(); i++) {
            sum += (sx.charAt(i) - 48);
        }
        for (int i = 0; i < sy.length(); i++) {
            sum += (sy.charAt(i) - 48);
        }
        return k >= sum;
    }

    //    剑指14-1
    public int cuttingRope2(int n) {
        int ans = 1;
        if (n < 4 && n != 2) {
            return n;
        }
        if (n == 2)
            return 1;
        while (n > 4) {
            n -= 3;
            ans *= 3;
        }
        if (n == 2) {
            return ans * 2;
        }
        if (n == 3)
            return ans * 3;
        if (n == 4)
            return ans * 4;
        return ans;
    }

    public int cuttingRope3(int n) {
        int[] dp = new int[n + 1];
        int ans = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }

    public int cuttingRope(int n) {
        int[] dp = new int[3];
        Arrays.fill(dp, 1);
        dp[0] = 0;
        for (int i = 3; i < n + 1; i++) {
            dp[i % 3] = Math.max(Math.max(Math.max(i - 1, dp[(i - 1) % 3]), 2 * Math.max(i - 2, dp[(i - 2) % 3])), 3 * Math.max(i - 3, dp[(i - 3) % 3]));
        }
        return dp[n % 3];
    }

    //    剑指12
    public boolean exist1(char[][] board, String word) {

        int m = board.length, n = board[0].length;
        int index = 0;
        int[][] check = new int[m][n];
        Stack<int[]> s = new Stack<>();
        int[] cord = {0, 0};
        int x, y;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(index)) {
                    index++;
                    cord[0] = i;
                    cord[1] = j;
                    check[i][j] = 1;
                    s.push(cord);
                    while (!s.isEmpty()) {
                        cord = s.peek();
                        x = cord[0];
                        y = cord[1];
                        System.out.println(x + " " + y);
                        if (index == word.length())
                            return true;
                        if (y + 1 < n && check[x][y + 1] != 1 && board[x][y + 1] == word.charAt(index)) {
                            s.push(new int[]{x, y + 1});
                            check[x][y + 1] = 1;
                            index++;
                            continue;
                        }
                        if (x + 1 < m && check[x + 1][y] != 1 && board[x + 1][y] == word.charAt(index)) {
                            s.push(new int[]{x + 1, y});
                            check[x + 1][y] = 1;
                            index++;
                            continue;
                        }
                        if (x - 1 > -1 && check[x - 1][y] != 1 && board[x - 1][y] == word.charAt(index)) {
                            s.push(new int[]{x - 1, y});
                            check[x - 1][y] = 1;
                            index++;
                            continue;
                        }
                        if (y - 1 > -1 && check[x][y - 1] != 1 && board[x][y - 1] == word.charAt(index)) {
                            s.push(new int[]{x, y - 1});
                            check[x][y - 1] = 1;
                            index++;
                            continue;
                        }
                        cord = s.pop();
                        index--;
                    }
                    for (int k = 0; k < m; k++) {
                        Arrays.fill(check[k], 0);
                    }
                    index = 0;
                }
            }
        }

        return false;
    }

    public boolean exist(char[][] board, String word) {

        int k = 0;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, k))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int k) {

        if (i >= board.length || i <= -1 || j >= board[0].length || j <= -1 || board[i][j] != word.charAt(k)) {
            return false;
        }
        if (k == word.length() - 1)
            return true;
        char temp = board[i][j];
        board[i][j] = '/';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) || dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = temp;
        return res;
    }

    //    剑指15
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (n >= Math.pow(2, i) && n < Math.pow(2, i + 1)) {
                n -= Math.pow(2, i);
                i = -1;
                count++;
                if (n == 0) {
                    return count;
                }
            }
        }
        return count;
    }

    //  剑指16
    public double myPow(double x, int n) {
        double sum = 1;
        int count = 1;
        if (n < 0) {
            n = -n;
            x = 1.0 / x;
        }
        while (n != 0) {
            sum = sum * ((n & 1) == 1 ? x : 1);
            x *= x;
            n >>>= 1;
        }
        return sum;
    }

    //   剑指17
    public int[] printNumbers(int n) {
        int k = (int) Math.pow(10, n);
        int[] nums = new int[k - 1];
        for (int i = 1; i < k; i++) {
            nums[i - 1] = i;
        }
        return nums;
    }

    //    剑指20 错误
    public boolean isNumber(String s) {
        try {
            Float.valueOf(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //  剑指19 dp和递归
//    判断所有条件，失败
    public boolean isMatch2(String s, String p1) {
        StringBuilder p = new StringBuilder(p1);
        int i = 0, j = 0;
        if (s.length() == 0 && (p.length() == 0 || (p.length() <= 2 && p.charAt(p.length() - 1) == '*')))
            return true;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                continue;
            }
            if (p.charAt(j) == '.') {
                i++;
                j++;
                continue;
            }
            if (p.charAt(j) == '*') {
                if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    if (j + 1 < p.length() && p.charAt(j - 1) == p.charAt(j + 1))
                        p.delete(j + 1, j + 2);
                    i++;
                    continue;
                }
                if (j + 1 < p.length() && (s.charAt(i) == p.charAt(j + 1) || p.charAt(j + 1) == '.')) {
                    i++;
                    j += 2;
                    continue;
                }
                return false;
            }
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                j++;
                continue;
            }
            return false;
        }
        if (i < s.length() || j < p.length() - 1 || (j == p.length() - 1 && p.charAt(j) != '*'))
            return false;
        return true;
    }

//    尝试递归

    public boolean isMatch(String s, String p) {
        if (s.length() == 0) {
            if (p.length() % 2 == 1)
                return false;
            for (int i = 1; i < p.length(); i += 2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (p.length() == 0) {
            return false;
        }
        char c1 = s.charAt(0), c2 = p.charAt(0), c3 = 'a';
        if (p.length() >= 2) {
            c3 = p.charAt(1);
        }
        if (c3 != '*') {
            if (c1 == c2 || c2 == '.') {
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        } else {
            if (c1 == c2 || c2 == '.') {
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            }
            return isMatch(s, p.substring(2));
        }
    }

    public boolean matchGo(String s, String p, int i, int j) {
        return true;
    }

    //    剑14
    public int cuttingRope4(int n) {
        long sum = 1;
        while (n > 4) {
            n -= 3;
            sum = (sum * 3) % 1000000007;
        }
        if (n < 4)
            return sum == 1 ? n - 1 : Integer.parseInt(String.valueOf((sum * n) % 1000000007));
        return Integer.parseInt(String.valueOf((sum * 4) % 1000000007));
    }

    public int cuttingRope5(int n) {
        long sum = 1;
        long a = n / 3;
        if (n == 3 || n == 2)
            return n - 1;
        if (n % 3 == 2) {
            sum = 2;
        }
        if (n % 3 == 1) {
            a = n / 3 - 1;
            sum = 4;
        }
        long x = 3;
        while (a > 0) {
            if (a % 2 == 1)
                sum = (sum * x) % 1000000007;
            x = (x * x) % 1000000007;
            a = a / 2;
        }
        return Integer.parseInt(String.valueOf((sum) % 1000000007));
    }

    //    剑指21
    public int[] exchange2(int[] nums) {
        int i = 0, j = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[i] % 2 == 1) {
                i++;
                continue;
            }
            j = Math.max(i, j);
            if (nums[j] % 2 == 1) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            j++;
        }
        return nums;
    }

    public int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] % 2 == 1) {
                i++;
                continue;
            }
            if (nums[j] % 2 == 0) {
                j--;
                continue;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    //    剑指24
    public ListNode reverseList2(ListNode head) {
        if (head == null)
            return head;
        ListNode tail = head.next;
        ListNode temp = tail;
        head.next = null;
        while (tail != null) {
            temp = tail.next;
            tail.next = head;
            head = tail;
            tail = temp;
        }
        return head;
    }

    //剑指25
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode l3 = new ListNode(-1);
        ListNode head = l3;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        if (l1 == null) {
            l3.next = l2;
        } else
            l3.next = l1;
        return head.next;
    }

    //    剑指26
    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        preOrderList(A, listA);
        preOrderList(B, listB);
        System.out.println(listA);
        System.out.println(listB);
        int i = 0, j = 0;
        while (i < listA.size() && j < listB.size()) {
            if (listA.get(i) == listB.get(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == listB.size() && j > 0)
            return true;
        return false;
    }

    public void preOrderList(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        preOrderList(root.left, list);
        preOrderList(root.right, list);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public boolean recur(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null)
            return false;
        if (A.val != B.val)
            return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    //    剑指27
    public TreeNode mirrorTree(TreeNode root) {
        TreeNode temp;
        if (root == null)
            return root;
        if (root.left != null || root.right != null) {
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            mirrorTree(root.left);
            mirrorTree(root.right);
        }
        return root;
    }

    //    kmp算法
    public int kmp(String s, String p) {
        int i = 0, j = 0;
        int[] next = next(p);
        while (i < s.length() && j < p.length()) {
//            这种写法也是可以的
//            if (s.charAt(i) == p.charAt(j)){
//                i++;j++;
//            }
//            else if (j>0){
//                j = next[j];
//            }else {
//                i++;
//            }
//            j=-1这种写法的目的是什么呢？就是说如果i和j的0号位的匹配不上，那么我们只能把i后移一位再比较，用什么代替后移i呢，就是将j=-1，进入第一个if语句，后移i。
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else
                j = next[j];
        }
        if (j == p.length())
            return i - j;
        return -1;
    }

    public int[] next(String p) {
        int[] next = new int[p.length() + 1];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < p.length()) {
//            这个j = -1也是为了后移i而准备的，但是如果匹配上了，那么next[i]就可以不停的+1了
            if (j == -1 || p.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else
                j = next[j]; //这个地方太难了，我们发现这里是干什么呢，这里是把j移动到[0，j-1]字符串那个最长前缀后面一个位置p，
            // 因为i前面的几个字符后j和p前面的字符相同，这样就可以接着比较，同时j也可以接着增大
        }
        return next;
    }

    public int kmp2(String s, String p) {
        int i = 0;
        int j = 0;
        int[] pmt = pmt(p);
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) {
                j = pmt[j - 1];
            } else {
                i++;
            }
        }
        if (j == p.length())
            return i - j;
        return -1;
    }

    public int[] pmt(String p) {
        int[] pmt = new int[p.length() + 1];
        pmt[0] = 0;
        pmt[1] = 0;
        int i = 1;
        int j = 0;
        while (i < p.length()) {
            new HelloWorld().out_list2(pmt);
            if (p.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                pmt[i] = j;
            } else {
                j = pmt[j];
                i++;
            }

        }
        return pmt;
    }

    public void midOrderList(TreeNode root, List<Integer> midList) {
        if (root == null)
            return;
        midOrderList(root.left, midList);
        midList.add(root.val);
        midOrderList(root.right, midList);
    }

    //    剑指28
    public boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return true;
        List<Integer> preList1 = new ArrayList<>();
        List<Integer> midList1 = new ArrayList<>();
        List<Integer> preList2 = new ArrayList<>();
        List<Integer> midList2 = new ArrayList<>();
        preOrderList(root, preList1);
        midOrderList(root, midList1);
        root = mirrorTree(root);
        preOrderList(root, preList2);
        midOrderList(root, midList2);
        if (preList1.size() != preList2.size() || midList1.size() != midList2.size()) {
            return false;
        }
        for (int i = 0; i < preList1.size(); i++) {
            if (preList1.get(i) != preList2.get(i)) {
                return false;
            }
        }
        for (int i = 0; i < midList1.size(); i++) {
            if (midList1.get(i) != midList2.get(i)) {
                return false;
            }
        }

        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return helpSymmetric(root.left, root.right);
    }

    public boolean helpSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left != null && right == null)
            return false;
        if (left == null)
            return false;
        if (left.val != right.val)
            return false;
        return helpSymmetric(left.left, right.right) && helpSymmetric(left.right, right.left);
    }

    //    剑指29
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return new int[]{};
        int col1 = 0, col2 = matrix.length - 1;
        int row1 = 0, row2 = matrix[0].length - 1;
        int[] ans = new int[matrix.length * matrix[0].length];
        int count = 0, i = 0;
        while (true) {
//            new HelloWorld().out_list2(ans);
//            System.out.println(row1+" "+row2+" "+col1+" "+col2);
            for (i = row1; i <= row2; i++) {
                ans[count++] = matrix[col1][i];
            }
            col1 += 1;
            if (col1 > col2)
                break;
            for (i = col1; i <= col2; i++) {
                ans[count++] = matrix[i][row2];
            }
            row2 -= 1;
            if (row1 > row2)
                break;

            for (i = row2; i >= row1; i--) {
                ans[count++] = matrix[col2][i];
            }
            col2 -= 1;
            if (col1 > col2) {
                break;
            }
            for (i = col2; i >= col1; i--) {
                ans[count++] = matrix[i][row1];
            }
            row1 += 1;
            if (row1 > row2)
                break;
        }
        return ans;
    }

    //    剑指31
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0)
            return true;
        int i = 0;
        int j = 0;
        Stack<Integer> s = new Stack<>();
        while (i < pushed.length) {
            while (s.isEmpty() || (i < pushed.length && s.peek() != popped[j])) {
                s.push(pushed[i++]);
            }
            while (!s.isEmpty() && j < popped.length && s.peek() == popped[j]) {
                s.pop();
                j++;
            }
        }
        return j == popped.length;
    }

    //    剑指32-1
    public int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[]{};
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> treeList = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t = q.poll();
            treeList.add(t.val);
            if (t.left != null)
                q.add(t.left);
            if (t.right != null)
                q.add(t.right);
        }
        int[] ans = new int[treeList.size()];
        for (int i = 0; i < treeList.size(); i++)
            ans[i] = treeList.get(i);
        return ans;
    }

    //    剑指32-2
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> llint = new ArrayList<>();
        if (root == null) {
            return llint;
        }
        List<Integer> lint = new ArrayList<>();
        lint.add(root.val);
        llint.add(lint);
        recur(root.left, llint, 2);
        recur(root.right, llint, 2);
        return llint;
    }

    public void recur(TreeNode root, List<List<Integer>> llint, int depth) {
        if (root == null)
            return;
        if (llint.size() < depth) {
            List<Integer> lint = new ArrayList<>();
            lint.add(root.val);
            llint.add(lint);
        } else
            llint.get(depth - 1).add(root.val);
        recur(root.left, llint, depth + 1);
        recur(root.right, llint, depth + 1);
    }

    public List<List<Integer>> levelOrder2_2(TreeNode root) {
        List<List<Integer>> llint = new ArrayList<>();
        if (root == null)
            return llint;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> lint = new ArrayList<>();
            int len = q.size();
            for (int i = 0; i < len; i++) {
                TreeNode t = q.poll();
                lint.add(t.val);
                if (t.left != null)
                    q.add(t.left);
                if (t.right != null)
                    q.add(t.right);
            }
            llint.add(lint);
        }
        return llint;
    }

    //    剑指32-3
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> llint = levelOrder2(root);
        int depth = llint.size();
        for (int i = 0; i < depth; i++) {
            if (i % 2 == 1) {
                Collections.reverse(llint.get(i));
            }
        }
        return llint;
    }


    //    94
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> lint = new ArrayList<>();
        if (root == null)
            return lint;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        TreeNode t = root;
        while (!s.isEmpty()) {
            t = s.pop();
            while (t.left != null) {
                s.push(t);
                t = t.left;
            }
            lint.add(t.val);
            if (t.right != null) {
                s.push(t.right);
                continue;
            }
            while (!s.isEmpty() && s.peek().right == null) {
                lint.add(s.pop().val);
            }
            if (s.isEmpty())
                return lint;
            lint.add(s.peek().val);
            s.push(s.pop().right);
        }
        return lint;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lint = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode t = root;
        while (t != null || !s.isEmpty()) {
            while (t != null) {
                s.push(t);
                t = t.left;
            }
            t = s.pop();
            lint.add(t.val);
            t = t.right;
        }
        return lint;
    }

    //剑指32
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> llint = new ArrayList<>();
        if (root == null || root.val > sum) {
            return llint;
        }
        List<Integer> lint = new ArrayList<>();
        if (root.val == sum && root.left == null && root.right == null) {
            lint.add(root.val);
            llint.add(lint);
            return llint;
        }
        lint.add(root.val);
        recur(root.left, root.val, sum, lint, llint);
        recur(root.right, root.val, sum, lint, llint);
        return llint;
    }

    public void recur(TreeNode root, int ans, int sum, List<Integer> lint, List<List<Integer>> llint) {
        if (root == null || (root.val + ans == sum && (root.left != null || root.right != null))) {
            return;
        }
        List<Integer> lint1 = new ArrayList<>(lint);
        if (root.val + ans == sum && root.left == null && root.right == null) {
            lint1.add(root.val);
            llint.add(lint1);
            return;
        }
        lint1.add(root.val);
        recur(root.left, root.val + ans, sum, lint1, llint);
        recur(root.right, root.val + ans, sum, lint1, llint);
    }

    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> llint = new ArrayList<>();
        if (root == null)
            return llint;
        TreeNode t = root;
        Stack<TreeNode> s = new Stack<>();
        List<Integer> lint = new ArrayList<>();
        int ans = 0;
        while (t != null || !s.isEmpty()) {
            while (t != null) {
                s.push(t);
                ans += t.val;
                lint.add(t.val);
                t = t.left;
            }
            t = s.pop();
            if (ans == sum && t == null && t.left == null)
                llint.add(new ArrayList<>(lint));
            ans -= t.val;
            lint.remove(llint.size() - 1);
            t = t.right;
        }
        return llint;
    }

    //    剑指33
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0 || postorder.length == 1)
            return true;
        return dfs(postorder, 0, postorder.length - 1);

    }

    public boolean dfs(int[] postorder, int left, int right) {
        if (left >= right)
            return true;
        int root = postorder[right];
        int i = left;
        while (postorder[i] < root)
            i++;
        int m = i;
        for (; i < right; i++) {
            if (postorder[i] < root)
                return false;
        }
        return dfs(postorder, left, m - 1) && dfs(postorder, m, right - 1);
    }

    //    剑指35
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Node res = head;
        while (res != null) {
            Node cp = new Node(res.val);
            Node res_next = res.next;
            res.next = cp;
            cp.next = res_next;
            res = res_next;
        }
//        然后接上随机的结点
        res = head;
        while (res != null) {
            res.next.random = res.random != null ? res.random.next : null;
            res = res.next.next;
        }
//        然后再把原来的链表拆开，注意哦不用管random指针
        res = head;
        Node head1 = res.next;
        Node cp = res.next;
        while (res != null) {
            res.next = res.next.next;
            if (cp.next == null)
                break;
            cp.next = cp.next.next;
            res = res.next;
            cp = cp.next;
        }
        return head1;
    }

//    36二叉搜索树与双向链表,两种节点噢
//    public Node treeToDoublyList(Node root) {
//        if (root == null)
//            return null;
//        Stack<Node> s = new Stack<>();
//        Node t = root;
//        Node front = null;
//        Node tail = null;
//        Node head = null;
//        while (t!=null || !s.isEmpty()){
//            while (t != null){
//                s.push(t);
//                t = t.left;
//            }
//            t = s.pop();
//            if (front == null){
//                front = t;
//                head = t;
//                t = t.right;
//                continue;
//            }
//            if (tail == null){
//                tail = t;
//                front.right = tail;
//                tail.left = front;
//                t = t.right;
//                continue;
//            }
//            front = tail;
//            tail = t;
//            front.right = tail;
//            tail.left = front;
//            t = t.right;
//        }
//        if (tail == null){
//            head.right = head;
//            head.left = head;
//            return head;
//        }
//        head.left = tail;
//        tail.right = head;
//        return head;
//    }

//    剑指37
// Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//
//    }

    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//
//    }

    //    剑指38
    List<String> res = new ArrayList<>();
    char[] c;

    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    public void dfs(int x) {
        if (x == c.length - 1)
            res.add(String.valueOf(c));
        Set<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i]))
                continue;
            set.add(c[i]);
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    public void swap(int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    //  37
    public void solveSudoku(char[][] board) {
//        BitSet bs = new BitSet();
    }

    //    剑指39
    public int majorityElement(int[] nums) {
        int count = 1;
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ans)
                count++;
            else {
                count--;
                if (count == 0) {
                    ans = nums[i];
                    count++;
                }
            }
        }
        return ans;
    }

    //    剑指40
    public int[] getLeastNumbers2(int[] arr, int k) {
        List<Integer> lint = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            lint.add(Integer.MAX_VALUE);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < k; j++) {
                if (arr[i] < lint.get(j)) {
                    lint.add(j, arr[i]);
                    lint.remove(k);
                    break;
                }
            }
        }
        int[] ans = new int[k];
        for (int j = 0; j < k; j++) {
            ans[j] = lint.get(j);
        }
        return ans;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k == 0) {
            return new int[]{};
        }
        quickSort(arr, 0, arr.length - 1, k - 1);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public void quickSort(int[] arr, int left, int right, int k) {
        int j = partition(arr, left, right);
        if (k == j)
            return;
        if (j > k)
            quickSort(arr, left, j - 1, k);
        else
            quickSort(arr, j + 1, right, k);
    }

    public int partition(int[] arr, int left, int right) {
        int flag = arr[left];
        int flag_pos = left;
        while (left < right) {
            while (left < right && flag <= arr[right])
                right--;
            if (left < right) {
                arr[flag_pos] = arr[right];
                flag_pos = right;
            }
            while (left < right && flag >= arr[left])
                left++;
            if (left < right) {
                arr[flag_pos] = arr[left];
                flag_pos = left;
            }
        }
        arr[left] = flag;
        return flag_pos;
    }

    //    226 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.right = left;
        root.left = right;
        return root;
    }

    //    剑指42
    public int maxSubArray(int[] nums) {
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            dp = dp > 0 ? dp + nums[i] : nums[i];
            max = Math.max(dp, max);
        }
        return max;
    }

    //    剑指43  好好斟酌
    public int countDigitOne(int n) {
        int high = n / 10; //高位数
        int cur = n % 10;     //当前位值
        int low = 0;    //低位数
        int digit = 1;  //什么指数级别的位数
        int count = 0;
        while (high != 0 || cur != 0) {
//            System.out.print(high+" "+cur+" "+low+" "+digit + " ");
            if (cur == 0)
                count += high * (int) Math.pow(10, digit - 1);
            else if (cur == 1)
                count += high * (int) Math.pow(10, digit - 1) + low + 1;
            else
                count += (high + 1) * (int) Math.pow(10, digit - 1);
//            System.out.println(count);
            digit++;
            high = high / 10;
            cur = n % (int) Math.pow(10, digit) / (int) Math.pow(10, digit - 1);
            low = n % (int) Math.pow(10, digit - 1);
        }
        return count;
    }

    public int findNthDigit(int n) {
        if (n < 10)
            return n;
        int num1 = 0;
        int num2 = 1;
        double sum = 1;
        while (n - sum > 0) {
            sum = sum + 9 * Math.pow(10, num1++) * (num2++);
        }
        num1 = num1 - 1;
        num2 = num2 - 1;
        sum -= 9 * Math.pow(10, num1) * num2;
        int num_pos = (n - (int) sum) / (num2);
        int pos = (n - (int) sum) % (num2);
        System.out.println(num1 + " " + num2 + " " + sum + " " + num_pos + " " + pos);
        int ans = String.valueOf((int) Math.pow(10, num1) + num_pos).charAt(pos) - 48;

        return ans;
    }

    //    剑指50
    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.putIfAbsent(s.charAt(i), 0);
            map.compute(s.charAt(i), (x, y) -> (y + 1));
        }
        for (char c : map.keySet()) {
            if (map.get(c) == 1)
                return c;
        }
        return ' ';
    }

    //  剑指57
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] > target)
                j--;
            else if (nums[i] + nums[j] < target)
                i++;
            else
                return new int[]{nums[i], nums[j]};
        }
        return new int[]{nums[i], nums[j]};
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null)
                return new int[]{nums[i], target - nums[i]};
            map.put(target - nums[i], nums[i]);
        }
        return new int[]{2, 2};
    }

    //    剑指51
    public int reversePairsTimeOut(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] > nums[j])
                    count++;
            }
        }
        return count;
    }

    int count = 0;

    public int reversePairs(int[] nums) {
        if (nums.length < 2)
            return 0;
        mergeSortCount(nums, 0, nums.length - 1);
        return count;
    }

    public void mergeSortCount(int[] nums, int left, int right) {
        if (right - left + 1 <= 1)
            return;
        int mid = (left + right) / 2;
        mergeSortCount(nums, left, mid);
        mergeSortCount(nums, mid + 1, right);
        int len1 = mid - left + 1;
        int len2 = right - mid;
        int[] nums1 = new int[len1];
        int[] nums2 = new int[len2];
        int i = left, j = 0, k = left;
        for (; i <= mid; i++, j++)
            nums1[j] = nums[i];
        i = mid + 1;
        j = 0;
        for (; i <= right; i++, j++)
            nums2[j] = nums[i];
        i = 0;
        j = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] <= nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
                count += len1 - i;
            }
        }
        while (i < len1)
            nums[k++] = nums1[i++];
        while (j < len2)
            nums[k++] = nums2[j++];
    }

    //    剑指58-1
    public String reverseWords1(String s) {
        s = s.trim();
        if (s.length() == 0)
            return "";
        int i = 0;
        int j = 0;
        String sc = "";
        Stack<String> stack = new Stack<>();
        for (; j < s.length(); j++) {
            if (s.charAt(j) == ' ') {
                System.out.println(s.substring(i, j));
                stack.push(s.substring(i, j));
                while (j < s.length() && s.charAt(j++) == ' ') {
                }
                i = --j;
            }

        }
        if (s.charAt(s.length() - 1) != ' ') {
            stack.push(s.substring(i, j));
        }
        while (!stack.isEmpty()) {
            sc += stack.pop() + ' ';
        }
        if (sc.length() == 0)
            return sc;
        return sc.substring(0, sc.length() - 1);
    }

    //剑指58
    public String reverseWords(String s) {
        s = s.trim();
        int i = s.length() - 1;
        int j = i;
        StringBuilder sb = new StringBuilder();
        for (; i >= 0; i--) {
            if (s.charAt(i) == ' ')
                sb.append(s.substring(i + 1, j + 1) + " ");
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
                j = i;
            }
        }
        sb.append(s.substring(i + 1, j + 1) + " ");
        return sb.toString().trim();
    }

    //    剑指58-2
    public String reverseLeftWords(String s, int n) {
        String scp = "";
        scp += s.substring(n);
        scp += s.substring(0, n);
        return scp;
    }

    //    剑指59-1
    //      暴力
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0)
            return nums;
        int i = 0, j;
        int[] maxList = new int[nums.length - k + 1];
        Arrays.fill(maxList, Integer.MIN_VALUE);
        int count = 0;
        for (; i <= nums.length - k; i++) {
            j = i + k;
            for (; i < j; i++) {
                maxList[count] = Math.max(maxList[count], nums[i]);
            }
            i = j - k;
            count++;
        }
        return maxList;
    }
    //  双端队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0)
            return new int[0];
        int i=0;
        Deque<Integer> deque = new LinkedList<>();
        int []ans = new int[nums.length - k + 1];
        for (;i<k;i++){
            while (!deque.isEmpty() && deque.getLast() < nums[i])
                deque.removeLast();
            deque.add(nums[i]);
        }
        ans[0] = deque.getFirst();
        for (i=k;i<nums.length;i++){
            if (deque.getFirst() == nums[i-k]){
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.getLast() < nums[i])
                deque.removeLast();
            deque.add(nums[i]);
            ans[i - k +1] = deque.getFirst();
        }
        return ans;
    }
    //    47
    List<List<Integer>> llint = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int x = 0;
        if (nums.length == 0) {
            return llint;
        }
        permutedfs(x, nums);
        return llint;
    }

    public void permutedfs(int x, int[] nums) {
        if (x == nums.length - 1) {
            List<Integer> lint = new ArrayList<>();
            for (int k : nums)
                lint.add(k);
            llint.add(lint);
            return;
        }
        System.out.println(x);
        Set<Integer> set = new HashSet<>();
        for (int i = x; i < nums.length; i++) {
            if (set.contains(nums[i]))
                continue;
            set.add(nums[i]);
            swap(x, i, nums);
            permutedfs(x + 1, nums);
            swap(x, i, nums);
        }
    }

    public void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //    剑指57-2 双指针
    public int[][] findContinuousSequence(int target) {
        List<int []> llint = new ArrayList<>();
        int sum = 0;
        int i = 1;
        int j = 2;
        sum = i + j;
        while (j <= target / 2 + target % 2) {
            while (sum < target)
                sum += ++j;
            if (sum == target) {
                int []heihei = new int[j-i+1];
                for (int k = i; k <= j; k++)
                    heihei[k-i] = k;
                llint.add(heihei);
                sum += ++j;
            }
            while (sum > target)
                sum -= i++;
        }

        return llint.toArray(new int[llint.size()][]);
    }
//    剑指46
    public int translateNum2(int num) {
//        dp[i] = dp[i-1]+1+dp[i-2]+1(要先判断是否再0-25区间内);
        String s = String.valueOf(num);
        if (s.length() == 1)
            return 1;
        int []dp = new int[s.length()];
        dp[0] = 1;
        dp[1] = 1;
        if (Integer.parseInt(s.substring(0, 2))<26)
            dp[1] = 2;
        for (int i=2;i<s.length();i++){
            dp[i] = dp[i-1];
            if (Integer.parseInt(s.substring(i-1, i+1)) < 26 && s.charAt(i-1) != 48)
                dp[i] += dp[i-2];
        }
        return dp[s.length()-1];
    }

    public int translateNum3(int num) {
//        dp[i] = dp[i-1]+1+dp[i-2]+1(要先判断是否再0-25区间内);
        String s = String.valueOf(num);
        if (s.length() == 1)
            return 1;
        int dp0 = 1;
        int dp1 = 1;
        int dp2 = 0;
        if (Integer.parseInt(s.substring(0, 2))<26)
            dp1 = 2;
        for (int i=2;i<s.length();i++){
            dp2 = dp1;
            if (Integer.parseInt(s.substring(i-1, i+1)) < 26 && s.charAt(i-1) != 48)
                dp2 += dp0;
            dp0 = dp1;
            dp1 = dp2;
            dp2 = 0;
        }
        return dp1;
    }

    public int translateNum(int num) {
//        dp[i] = dp[i-1]+1+dp[i-2]+1(要先判断是否再0-25区间内);
        if (num / 10 == 0)
            return 1;
        int dp0 = 1;
        int dp1 = 1;
        int dp2;
        int p=0;
        while ((long)Math.pow(10, ++p) <= num){}
        if (num / (int)Math.pow(10, p-2) < 26)
            dp1 = 2;
        p -= 2;
        while (p > 0){
            dp2 = dp1;
            if (num / (int)Math.pow(10,p-1) % 100 < 26 && num / (int)Math.pow(10,p) % 10 != 0)
                dp2 += dp0;
            dp0 = dp1;
            dp1 = dp2;
            p--;
        }
        return dp1;
    }
//    5519
    public String reorderSpaces(String text) {
        int space_num = 0;
        int word_num = 0;
        for (int i=0;i<text.length();i++){
            if (text.charAt(i) == ' ')
                space_num++;
        }
        String tCp = text.trim();
        for (int i=0;i<tCp.length();i++) {
            if (tCp.charAt(i) == ' ')
                word_num++;
            while (tCp.charAt(i) == ' ')
                i++;
        }
        word_num ++;
        String ans = "";
        if (word_num == 1 || word_num == 0){
            ans = tCp;
            for (int i = 0;i<space_num;i++){
                ans += ' ';
            }
            return ans;
        }
        int word_space_num = space_num / (word_num-1);
        int tt = space_num % (word_num-1);
        String s_space = "";
        while (word_space_num-->0)
            s_space += ' ';
        int j=0;
        for (int i=0;i<tCp.length();i++) {
            if (tCp.charAt(i) == ' '){
                ans += tCp.substring(j, i) + s_space;
                j = i;
            }
            while (tCp.charAt(i) == ' '){
                i++;
                j=i;
            }
        }
        ans += tCp.substring(j, tCp.length());
        while (tt>0){
            ans += ' ';
            tt--;
        }
        return ans;
    }

//    5520
    public int maxUniqueSplit(String s) {
        if (s.length() == 1)
            return 1;
        Set<String> set = new HashSet<>();
        int []dp = new int[s.length()];
        dp[0] = 1;
        set.add(s.substring(0, 1));
        int i=1;
        for (int j=1;j<s.length();j++){
            dp[j] = dp[j-1];
            String sub = s.substring(i, j+1);
            if (!set.contains(sub)){
                set.add(sub);
                dp[j] = dp[j-1] + 1;
                i = j + 1;
            }
        }
        return dp[s.length()-1];
    }
    long sum = Integer.MIN_VALUE;
    public int maxProductPathTimeOut(int[][] grid) {
        int i=0,j=0;
        long k = 1;
        dfs(grid, i, j, k);
        if (sum < 0)
            return -1;
        return (int)(sum%1000000007);
    }
    public void dfs(int[][] grid,int i, int j, long k){
        if (i==grid.length || j==grid[0].length){
            return;
        }
        k *= grid[i][j];
        if (i==grid.length-1 && j == grid[0].length-1){
            sum = Math.max(sum, k);
            return;
        }
        dfs(grid, i+1, j, k);
        dfs(grid, i, j+1, k);
    }

    public int maxProductPath3(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        long[][] mindp = new long[n][m];
        long[][] maxdp = new long[n][m];
        long inf = (long) 1000000;
        for (int i = 0; i < n; i++) {
            Arrays.fill(mindp[i], inf);
            Arrays.fill(maxdp[i], -inf);
        }
        mindp[0][0] = maxdp[0][0] = grid[0][0];
        int[][] dirs = new int[][]{
                {1, 0},
                {0, 1}
        };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int[] d : dirs) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (x >= n || y >= m) {
                        continue;
                    }
                    long l = mindp[i][j] * grid[x][y];
                    long r = maxdp[i][j] * grid[x][y];
                    if (grid[x][y] < 0) {
                        long tmp = l;
                        l = r;
                        r = tmp;
                    }
                    mindp[x][y] = Math.min(l, mindp[x][y]);
                    maxdp[x][y] = Math.max(r, maxdp[x][y]);
                }
            }
        }

        int mod = (int) (1e9 + 7);
        int ans = maxdp[n - 1][m - 1] < 0 ? -1 : (int) (maxdp[n - 1][m - 1] % mod);
        return ans;
    }

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long [][]maxdp = new long[m][n];
        long [][]mindp = new long[m][n];
        for (int i=0;i<m;i++){
            Arrays.fill(maxdp[i], Integer.MIN_VALUE);
            Arrays.fill(mindp[i], Integer.MAX_VALUE);
        }
        int [][]twoDirection = new int[][]{
                {0, 1},
                {1, 0}
        };
        maxdp[0][0] = grid[0][0];
        mindp[0][0] = grid[0][0];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                for (int []direction:twoDirection){
                    int x = i + direction[0];
                    int y = j + direction[1];
                    if (x >= m || y >= n)
                        continue;
                    long l = mindp[i][j] * grid[x][y];
                    long r = maxdp[i][j] * grid[x][y];
                    if (grid[x][y] < 0){
                        long temp = l;
                        l = r;
                        r = temp;
                    }
                    mindp[x][y] = Math.min(mindp[x][y], l);
                    maxdp[x][y] = Math.max(maxdp[x][y], r);
                }

            }
        }
        return maxdp[m-1][n-1] < 0?-1:(int)(maxdp[m-1][n-1]%(1e9+7));
    }

//    剑指47
    public int maxValue2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int [][]maxdp = new int[m][n];
        int [][]twoDir = new int[][]{
                {1,0},
                {0,1}
        };
        maxdp[0][0] = grid[0][0];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                for (int []dir:twoDir){
                    int x = i+dir[0];
                    int y = j+dir[1];
                    if (x == m || y == n){
                        continue;
                    }
                    int max = maxdp[i][j] + grid[x][y];
                    maxdp[x][y] = Math.max(maxdp[x][y], max);
                }
            }
        }
        return maxdp[m-1][n-1];
    }
    public int maxValue(int[][] grid) {

        maxDfs(grid, 0, 0, 0);
        return (int)sum;
    }

    private void maxDfs(int[][] grid, int i, int j, int k) {
        if (i==grid.length || j==grid[0].length)
        {
            return;
        }
        k = k + grid[i][j];
        if (i==grid.length-1 && j==grid[0].length-1)
            sum = Math.max(sum, k);
        maxDfs(grid, i, j+1, k);
        maxDfs(grid, i+1, j, k);
    }

    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }
    public void quickSort(String []strs, int i,int j){
        if (i >= j)
            return;
        int left = i;
        int right = j;
        String flag = strs[i];
        out_list2(strs);
        while (left < right){
            while (left<right && (flag + strs[right]).compareTo(strs[right] + flag) <= 0){right--;}
            while (left<right && (flag + strs[left]).compareTo(strs[left] + flag) >= 0){left++;}
            String temp = strs[left];
            strs[left] = strs[right];
            strs[right] = temp;
        }
        strs[i] = strs[left];
        strs[left] = flag;
        quickSort(strs, i, left-1);
        quickSort(strs, left+1, j);
    }
//    剑指48 abba
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        int i=-1,j=0,k=0;
        int max = 1;
        Map<Character, Integer> map = new HashMap<>();
        for (;j<s.length();j++){
            k = map.get(s.charAt(j)) == null ? -1:map.get(s.charAt(j));
            if (k == -1)
                max = Math.max(j - i, max);
            else{
                i = Math.max(i, k);
                max = Math.max(j - i, max);

            }
            map.put(s.charAt(j), j);
//            System.out.println(max);
//            System.out.println(map);
        }
        return max;
    }
//  剑指53-1
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int count = 0;
        while (left <= right){
            int mid = (left + right) /2;
            if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid -1;
            else {
                count = 1;
                int i=mid - 1;
                int j = mid + 1;
                while (i>-1 && nums[i--] == target){count++;}
                while (j<nums.length &&nums[j++] == target){count++;}
                break;
            }
        }
        return count;
    }
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0)
            return 0;
        int []dp = new int[s.length()];
        int max = 1;
        int j = 0;
        dp[0] = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0),0);
        for (int i=1;i<s.length();i++){
            j = map.get(s.charAt(i)) == null ? -1:map.get(s.charAt(i));
            if (dp[i-1] < i - j)
                dp[i] = dp[i-1] + 1;
            else
                dp[i] = i - j;
            map.put(s.charAt(i), i);
            max = Math.max(dp[i], max);
        }
        return max;
    }
//    剑指52  思路将两个链表倒序，然后数第一个非公共节点
//    while (front != null){
//        next = front.next;
//        front.next = headB;
//        headB = front;
//        front = next;
//    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != node2){
            node1 = node1.next;
            node2 = node2.next;
            if (node1 == null && node2!=null)
                node1 = headB;
            if (node2 == null && node1!=null)
                node2 = headA;
        }
        return node1;
    }

//    剑指49
    public int nthUglyNumber(int n) {
        int []dp = new int[n];
        int a=0,b=0,c=0;
        dp[0] = 1;
        for (int i=1;i<n;i++){
            int n1 = dp[a] * 2;
            int n2 = dp[b] * 3;
            int n3 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n1, n2), n3);
            if (dp[i] == n1)
                a++;
            if (dp[i] == n2)
                b++;
            if (dp[i] == n3)
                c++;
        }
        return dp[n-1];
    }

//    剑指54 反中序
    public int kthLargest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode t = root;
        while (t!=null || !s.isEmpty()){
            while (t!=null){
                s.push(t);
                t = t.right;
            }
            t = s.pop();
            if (--k == 0)
                return t.val;
            t = t.left;
        }
        return t.val;
    }
//    剑指55-1
    int maxDepth;
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 1;
        dfs(root.left, depth);
        dfs(root.right, depth);
        return maxDepth;
    }
    public void dfs(TreeNode root, int depth){
        if (root == null){
            maxDepth = Math.max(maxDepth, depth);
            return;
        }
        depth++;
        dfs(root.left, depth);
        dfs(root.right, depth);
    }

//    617合并二叉树
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null || t2==null) {
            return t1==null? t2 : t1;
        }
        TreeNode t3 = new TreeNode(t1.val+t2.val);
        t3.left = mergeTrees(t1.left, t2.left);
        t3.right = mergeTrees(t1.right, t2.right);
        return t3;
    }

//   平衡二叉树
    public boolean isBalanced2(TreeNode root) {
        if (root == null)
            return true;
        if(Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1){
            return false;
        }
        return isBalanced2(root.left) && isBalanced2(root.right);
    }
    public boolean isBalanced(TreeNode root) {

        return recur(root) != -1;
    }

    public int recur(TreeNode root){
        if (root == null)
            return 0;
        int left_depth = recur(root.left);
        int right_depth = recur(root.right);
        if (left_depth == -1 || right_depth == -1)
            return -1;
        return Math.abs(left_depth - right_depth) > 1?-1:Math.max(left_depth, right_depth) + 1;
    }
//    位运算
    public int singleNumber(int[] nums) {
        int sum = 0;
        for(int num:nums){
            sum ^= num;
        }
        return sum;
    }
//    剑指56-1 同为0，异为1 这套路无敌
    public int[] singleNumbers(int[] nums) {
        int sum = 0;
        for (int num:nums){
            sum ^= num;
        }
        int mask = 1;
        while ((sum & mask) == 0){
            mask <<= 1;
        }
        int a = 0;
        int b = 0;
        for (int num:nums){
            if ((mask & num) == 0)
                a ^= num;
            else
                b ^= num;
        }
        return new int[]{a, b};
    }

//    剑指56-2 再思考一下
//    public int singleNumber(int[] nums) {
//
//    }
    public static void main(String[] args) {
        int []nums = {1};
        int target = 1;
        JZPractice jzp = new JZPractice();
        int ans = jzp.search(nums, target);
//        new HelloWorld().out_list(ans);
//        new HelloWorld().out_list2(ans);
        System.out.println(ans);
    }
}
