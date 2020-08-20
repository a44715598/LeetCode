import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.print.DocFlavor;
import java.util.*;
import java.util.regex.Pattern;

public class HelloWorld {
    //  709
    public String toLowerCase(String str) {
        StringBuilder str_bui = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            if (str_bui.charAt(i) >= 65 && str_bui.charAt(i) <= 90) {
                str_bui.setCharAt(i, (char) (str_bui.charAt(i) + 32));
            }
        }

        return str_bui.toString();
    }

    //    1177
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
//        创建字符串个数的二维数组
        int[][] num_char = new int[s.length() + 1][26];
//        预处理得到每一个点的出现次数（超时）
//        for (int i=0;i<s.length();i++){
//            for (int j=i;j<s.length();j++){
//                num_char[j+1][(int)(s.charAt(i) - 97)] ++;
//            }
//        }
        //        预处理得到每一个点的出现次数（不超时）
        for (int i = 0; i < s.length(); i++) {
            num_char[i + 1] = num_char[i].clone();
            num_char[i + 1][(int) (s.charAt(i) - 97)]++;
        }
        List<Boolean> queries_result = new ArrayList<Boolean>();


        for (int[] query : queries) {

            int[] num = new int[26];
            boolean flag = false;
            int transform_num = 0;

            for (int i = 0; i < 26; i++) {
                num[i] = num_char[query[1] + 1][i] - num_char[query[0]][i];
                if (num[i] % 2 == 1) {
                    if (flag) {
                        transform_num++;
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            }
//            for(int nu:num){
//                System.out.print(nu);
//            }
//            System.out.println();
            if (query[2] - transform_num >= 0) {
                queries_result.add(true);
            } else {
                queries_result.add(false);
            }

        }
        return queries_result;
    }

    public List<Boolean> canMakePaliQueries2(String s, int[][] queries) {
        List<Boolean> query_result = new ArrayList<Boolean>();
        int[] states = new int[s.length() + 1];
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            cnt ^= 1 << (s.charAt(i) - 'a');
            states[i + 1] = cnt;
        }
        for (int i = 0; i < queries.length; i++) {
            int ant = states[queries[i][1] + 1] ^ states[queries[i][0]];
            int num = 0;
            while (ant > 0) {
                if ((ant & 1) == 1) {
                    num++;
                }
                ant >>= 1;
            }
            query_result.add(num / 2 <= queries[i][2]);
        }

        return query_result;
    }

    //    1096
    public List<String> braceExpansionII(String expression) {

        return new ArrayList<String>();
    }

    //    151
    public String reverseWords(String s) {
        s = s.trim();
        String[] splited = s.split("\\s+");
        StringBuilder str = new StringBuilder("");
        for (int i = splited.length - 1; i >= 0; i--) {

            str.append(splited[i].toString());
            if (i != 0) {
                str.append(" ");
            }
        }
        return str.toString();
    }

    //    521
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        } else {
            return Math.max(a.length(), b.length());
        }
    }

    //    1540
    public boolean canConvertString(String s, String t, int k) {

        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[26];
        int m = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                m = (t.charAt(i) - s.charAt(i) + 26) % 26;
                cnt[m]++;
                if (m + (cnt[m] - 1) * 26 > k) {
                    return false;
                }
            }
        }
        return true;
    }

    //    415
    public String addStrings(String num1, String num2) {
        StringBuilder num3 = new StringBuilder();
        int cnt = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            num3.append((num1.charAt(i) + num2.charAt(j) + cnt - 96) % 10);
            if (num1.charAt(i) + num2.charAt(j) + cnt - 96 >= 10) {
                cnt = 1;
                continue;
            }

            cnt = 0;
        }

        for (; i >= 0; i--) {
            num3.append((num1.charAt(i) + cnt - 48) % 10);
            if (num1.charAt(i) + cnt - 48 < 10) {
                num3.append(new StringBuilder(num1.substring(0, i)).reverse());
                cnt = 0;
                break;
            }
        }
        for (; j >= 0; j--) {
            num3.append((num2.charAt(j) + cnt - 48) % 10);
            if (num2.charAt(j) + cnt - 48 < 10) {
                num3.append(new StringBuilder(num2.substring(0, j)).reverse());
                cnt = 0;
                break;
            }
        }
        if (i < 0 && j < 0 && cnt > 0) {
            num3.append("1");
            return num3.reverse().toString();
        }
        return num3.reverse().toString();
    }

    //    468
    public String validIPAddress(String IP) {

        String pattern1 = "^(([1-9]?[0-9]|1[0-9]?[0-9]|25[0-5]|2[0-4]\\d).){3}([1-9]?[0-9]|1[0-9]?[0-9]|25[0-5]|2[0-4]\\d)$";
        String pattern2 = "^(([0-9a-fA-F]){1,4}:){7}(([0-9a-fA-F]){1,4})$";
        if (Pattern.matches(pattern1, IP)) {
            return "IPv4";
        }
        if (Pattern.matches(pattern2, IP)) {
            return "IPv6";
        }
        return "Neither";
    }

    //    917
    public String reverseOnlyLetters(String S) {
        char temp;
        StringBuilder SCopy = new StringBuilder(S);
        int left = 0, right = S.length() - 1;
        while (left < right) {
            if ((S.charAt(left) > 64 && S.charAt(left) < 91) || (S.charAt(left) > 96 && S.charAt(left) < 133)) {
                if ((S.charAt(right) > 64 && S.charAt(right) < 91) || (S.charAt(right) > 96 && S.charAt(right) < 133)) {
                    SCopy.replace(left, left + 1, String.valueOf(S.charAt(right)));
                    SCopy.replace(right, right + 1, String.valueOf(S.charAt(left)));
                    right--;
                    left++;
                } else {
                    right--;
                }
            } else {
                left++;
            }
        }
        return SCopy.toString();
    }

    //    1487
    public String[] getFolderNames(String[] names) {
        String[] new_names = new String[names.length];
        HashMap<String, Integer> m = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            if (m.get(names[i]) != null) {
                int j = m.get(names[i]);
                while (m.get(names[i] + "(" + String.valueOf(j++) + ")") != null) {
                }
                m.put(names[i] + "(" + String.valueOf(j - 1) + ")", 1);
                m.put(names[i], m.get(names[i]) + 1);
                new_names[i] = names[i] + "(" + String.valueOf(j - 1) + ")";
            } else {
                m.put(names[i], 1);
                new_names[i] = names[i];
            }

        }
        return new_names;
    }

    //  22
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        String str = new String("");
        int left = 0;
        create(ans, 2 * n, str, left);
        return ans;
    }

    public void create(List<String> ans, int n, String str, int left) {

        if (left < 0){
            return;
        }
        if (n == 0) {
            if (left == 0) {
                ans.add(str.toString());
            }
            return;
        }
//        System.out.println(str);
        create(ans, n - 1, str + '(', left + 1);
        create(ans, n - 1, str + ')', left - 1);
        return;
    }

    public Boolean checkStr(String s) {
        int left = 0;
        int right = 0;
        for(int i=0;i<s.length();i++){
            if (s.charAt(i) == '('){
                left ++;
            }
            if (s.charAt(i) == ')'){
                right++;
            }
            if (left - right < 0){
                return false;
            }
        }
        if (left != right){
            return false;
        }
        return true;
    }

    //
    public int maxNumberOfBalloons(String text) {
//        balloon
        HashMap<Character, Integer> map = new HashMap<>();
        char[] balon = {'b', 'a', 'l', 'o', 'n'};
        for (char c : balon) {
            map.put(c, 0);
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'b' || text.charAt(i) == 'a' || text.charAt(i) == 'l' || text.charAt(i) == 'o' || text.charAt(i) == 'n') {
                map.compute(text.charAt(i), (k, v) -> {
                    v += 1;
                    return v;
                });
            }
        }
        map.compute('l', (k, v) -> {
            v /= 2;
            return v;
        });
        map.compute('o', (k, v) -> {
            v /= 2;
            return v;
        });
        int min = 100000;
        for (char c : balon) {
            if (map.get(c) < min) {
                min = map.get(c);
            }
        }
        System.out.println(map);
        return min;
    }

    public static void main(String[] args) {
        int n = 3;
        HelloWorld hello_world = new HelloWorld();
        List<String> ans = hello_world.generateParenthesis(n);
        System.out.println(ans);
    }
}