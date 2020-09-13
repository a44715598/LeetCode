import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
        Stack<int []> s = new Stack<>();
        int []cord = {0,0};
        s.push(cord);
        int x = 0, y = 0;
        nums[0][0] = 1;
        while (!s.isEmpty()){
            cord = s.peek();
            x = cord[0];y = cord[1];
            if (x + 1 < m && nums[x + 1][y] != 1 && nums[x + 1][y] != -1) {
                if (checkValToK(x + 1, y, k)){
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
                if (checkValToK(x, y + 1, k)){
                    s.push(new int[]{x, y + 1});
                    nums[x][y + 1] = 1;
                    continue;
                }
                nums[x][y + 1] = -1;
            }
            if (y - 1 > -1 && nums[x][y - 1] != 1 && nums[x][y - 1] != -1) {
                if (checkValToK(x, y - 1, k)){
                    s.push(new int[]{x, y - 1});
                    nums[x][y - 1] = 1;
                    continue;
                }
                nums[x][y - 1] = -1;
            }
            count ++;
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
        int []dp = new int[n+1];
        int ans = 1;
        dp[2] = 1;
        for (int i = 3;i<=n;i++){
            for (int j=1;j<i;j++){
                dp[i] = Math.max(dp[i], Math.max((i-j)*j, dp[i-j] * j));
            }
        }
        return dp[n];
    }

    public int cuttingRope(int n) {
        int []dp = new int[3];
        Arrays.fill(dp, 1);
        dp[0] = 0;
        for (int i=3;i<n+1;i++){
            dp[i%3]  = Math.max(Math.max(Math.max(i-1, dp[(i-1)%3]), 2*Math.max(i-2, dp[(i-2)%3])), 3*Math.max(i-3, dp[(i-3)%3]));
        }
        return dp[n%3];
    }

    //    剑指12
    public boolean exist1(char[][] board, String word) {

        int m=board.length,n=board[0].length;
        int index = 0;
        int [][]check = new int[m][n];
        Stack<int[]> s = new Stack<>();
        int []cord = {0,0};
        int x,y;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (board[i][j] == word.charAt(index)) {
                    index++;
                    cord[0] = i;cord[1]=j;
                    check[i][j] = 1;
                    s.push(cord);
                    while (!s.isEmpty()) {
                        cord = s.peek();
                        x = cord[0];
                        y = cord[1];
                        System.out.println(x+" "+y);
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
                    for (int k=0;k<m;k++){
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
        int m=board.length,n=board[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (dfs(board, word, i, j, k))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int k) {

        if (i >= board.length || i<= -1 || j >= board[0].length || j<= -1 || board[i][j] != word.charAt(k)){
            return false;
        }
        if (k == word.length()-1)
            return true;
        char temp = board[i][j];
        board[i][j] = '/';
        boolean res = dfs(board, word, i+1, j, k+1) || dfs(board, word, i-1, j, k+1) || dfs(board, word, i, j+1, k+1) ||dfs(board, word, i, j-1, k+1);
        board[i][j] = temp;
        return res;
    }
    //    剑指15
    public int hammingWeight(int n) {
        int count = 0;
        for (int i=0;i<100;i++){
            if (n>=Math.pow(2,i) && n<Math.pow(2,i+1)){
                n -= Math.pow(2,i);
                i = -1;
                count ++;
                if (n == 0){
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
        if (n<0){
            n = -n;
            x = 1.0 / x;
        }
        while (n != 0){
            sum = sum * ((n & 1) == 1?x:1);
            x *= x;
            n >>>= 1;
        }
        return sum;
    }
    //   剑指17
    public int[] printNumbers(int n) {
        int k = (int)Math.pow(10, n);
        int []nums = new int[k-1];
        for (int i=1;i<k;i++){
            nums[i-1] = i;
        }
        return nums;
    }

    //    剑指20 错误
    public boolean isNumber(String s) {
        try {
            Float.valueOf(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    //  剑指19 dp和递归
//    判断所有条件，失败
    public boolean isMatch2(String s, String p1) {
        StringBuilder p = new StringBuilder(p1);
        int i=0, j=0;
        if (s.length() == 0 && (p.length() == 0 || (p.length() <= 2 && p.charAt(p.length()-1) == '*')))
            return true;
        while (i<s.length()&&j<p.length()){
            if (s.charAt(i) == p.charAt(j)){
                i++;j++;
                continue;
            }
            if (p.charAt(j) == '.'){
                i++;j++;
                continue;
            }
            if (p.charAt(j) == '*'){
                if (s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    if (j+1 < p.length() && p.charAt(j-1) == p.charAt(j+1))
                        p.delete(j+1,j+2);
                    i++;
                    continue;
                }
                if (j+1 < p.length() && (s.charAt(i) == p.charAt(j+1) || p.charAt(j+1) == '.')){
                    i++;j+=2;
                    continue;
                }
                return false;
            }
            if (j+1 < p.length() && p.charAt(j+1) == '*'){
                j++;
                continue;
            }
            return false;
        }
        if (i < s.length() || j<p.length()-1 ||(j == p.length()-1 && p.charAt(j) != '*'))
            return false;
        return true;
    }

//    尝试递归

    public boolean isMatch(String s, String p) {
        if (s.length() == 0){
            if (p.length() % 2 == 1)
                return false;
            for (int i=1;i<p.length();i+=2){
                if (p.charAt(i) != '*'){
                    return false;
                }
            }
            return true;
        }
        if (p.length() == 0){
            return false;
        }
        char c1 = s.charAt(0),c2 = p.charAt(0),c3='a';
        if (p.length() >= 2){
            c3 = p.charAt(1);
        }
        if (c3 != '*'){
            if (c1 == c2 || c2 == '.'){
                return isMatch(s.substring(1),p.substring(1));
            }
            return false;
        }
        else {
            if (c1 == c2 || c2 == '.'){
                return isMatch(s.substring(1),p) || isMatch(s,p.substring(2));
            }
            return isMatch(s,p.substring(2));
        }
    }
    public boolean matchGo(String s,String p,int i,int j){
        return true;
    }
    //    剑14
    public int cuttingRope4(int n) {
        long sum = 1;
        while (n > 4){
            n-=3;
            sum = (sum * 3) % 1000000007;
        }
        if (n < 4)
            return sum == 1? n-1:Integer.parseInt(String.valueOf((sum*n)%1000000007));
        return Integer.parseInt(String.valueOf((sum * 4) % 1000000007));
    }
    public int cuttingRope5(int n) {
        long sum = 1;
        long a = n/3;
        if (n == 3 || n==2)
            return n-1;
        if (n%3 == 2){
            sum=2;
        }
        if (n%3 == 1){
            a = n/3 - 1;
            sum = 4;
        }
        long x = 3;
        while (a > 0){
            if (a % 2 == 1)
                sum = (sum * x) % 1000000007;
            x= (x*x) % 1000000007;
            a = a/2;
        }
        return Integer.parseInt(String.valueOf((sum) % 1000000007));
    }

    //    剑指21
    public int[] exchange2(int[] nums) {
        int i = 0,j = 0;
        while (i<nums.length && j<nums.length){
            if (nums[i] % 2 == 1){
                i++;continue;
            }
            j = Math.max(i, j);
            if (nums[j] % 2 == 1){
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
        int i = 0,j = nums.length-1;
        while (i<j){
            if (nums[i] % 2 == 1){
                i++;
                continue;
            }
            if (nums[j] % 2 == 0){
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
        while (tail != null){
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
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                l3.next = l1;
                l1 = l1.next;
            }else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        if (l1 == null){
            l3.next = l2;
        }else
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
        int i=0,j=0;
        while (i<listA.size() && j<listB.size()){
            if (listA.get(i) == listB.get(j)){
                i++;j++;
            }else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == listB.size() && j > 0)
            return true;
        return false;
    }
    public void preOrderList(TreeNode root, List<Integer> list){
        if (root == null)
            return;
        list.add(root.val);
        preOrderList(root.left,list);
        preOrderList(root.right,list);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A!=null && B!=null) && (recur(A, B) || isSubStructure(A.left,B) || isSubStructure(A.right,B));
    }
    public boolean recur(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null)
            return false;
        if (A.val != B.val)
            return false;
        return recur(A.left,B.left) && recur(A.right, B.right);
    }
//    剑指27
    public TreeNode mirrorTree(TreeNode root) {
        TreeNode temp;
        if(root == null)
            return root;
        if (root.left != null || root.right!=null){
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            mirrorTree(root.left);
            mirrorTree(root.right);
        }
        return root;
    }

//    kmp算法
    public int kmp(String s, String p){
        int i=0,j=0;
        int []next = next(p);
        while (i<s.length() && j<p.length()){
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
            if (j==-1 || s.charAt(i) == p.charAt(j)){
                i++;j++;
            }
            else
                j = next[j];
        }
        if (j == p.length())
            return i-j;
        return -1;
    }
    public int[]next(String p){
        int []next = new int[p.length() + 1];
        next[0] = -1;
        int i=0,j=-1;
        while (i<p.length()){
//            这个j = -1也是为了后移i而准备的，但是如果匹配上了，那么next[i]就可以不停的+1了
            if (j == -1||p.charAt(i) == p.charAt(j)){
                i++;j++;
                next[i] = j;
            }
            else
                j = next[j]; //这个地方太难了，我们发现这里是干什么呢，这里是把j移动到[0，j-1]字符串那个最长前缀后面一个位置p，
                                // 因为i前面的几个字符后j和p前面的字符相同，这样就可以接着比较，同时j也可以接着增大
        }
        return next;
    }

    public int kmp2(String s, String p){
        int i=0;
        int j=0;
        int []pmt = pmt(p);
        while (i<s.length() && j<p.length()){
            if (s.charAt(i) == p.charAt(j))
            {
                i++;j++;
            }
            else if(j > 0){
                j = pmt[j-1];
            }else {
                i++;
            }
        }
        if (j == p.length())
            return i-j;
        return -1;
    }
    public int[]pmt(String p){
        int []pmt = new int[p.length()+1];
        pmt[0] = 0;
        pmt[1] = 0;
        int i=1;
        int j=0;
        while (i<p.length()){
            new HelloWorld().out_list2(pmt);
            if (p.charAt(i) == p.charAt(j)){
                i++;j++;
                pmt[i] = j;
            }
            else{
                j = pmt[j];
                i++;
            }

        }
        return pmt;
    }

    public void midOrderList(TreeNode root, List<Integer> midList){
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
        if (preList1.size() != preList2.size() || midList1.size() != midList2.size()){
            return false;
        }
        for (int i=0;i<preList1.size();i++){
            if (preList1.get(i) != preList2.get(i)){
                return false;
            }
        }
        for (int i=0;i<midList1.size();i++){
            if (midList1.get(i) != midList2.get(i)){
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
        int col1 = 0, col2 = matrix.length-1;
        int row1 = 0, row2 = matrix[0].length-1;
        int []ans = new int[matrix.length * matrix[0].length];
        int count = 0,i = 0;
        while (true){
//            new HelloWorld().out_list2(ans);
//            System.out.println(row1+" "+row2+" "+col1+" "+col2);
            for (i=row1;i<=row2;i++){
                ans[count++] = matrix[col1][i];
            }
            col1 += 1;
            if (col1 > col2)
                break;
            for (i=col1;i<=col2;i++){
                ans[count++] = matrix[i][row2];
            }
            row2 -= 1;
            if (row1 > row2)
                break;

            for (i=row2;i>=row1;i--){
                ans[count++] = matrix[col2][i];
            }
            col2 -= 1;
            if (col1 > col2){
                break;
            }
            for (i=col2;i>=col1;i--){
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
        int i=0;
        int j=0;
        Stack<Integer> s = new Stack<>();
        while (i < pushed.length){
            while (s.isEmpty() || (i < pushed.length && s.peek() != popped[j])){
                s.push(pushed[i++]);
            }
            while (!s.isEmpty() && j < popped.length && s.peek() == popped[j]){
                s.pop();
                j++;
            }
        }
        return j == popped.length;
    }
    public static void main(String[] args) {
        int []pushed = {1,2,3,4,5};
        int []popped = {4,5,3,2,1};
        JZPractice jzp = new JZPractice();
        boolean ans = jzp.validateStackSequences(pushed, popped);
//        new HelloWorld().out_list2(ans);
        System.out.println(ans);
    }
}
