import com.sun.org.apache.bcel.internal.generic.SWAP;
import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Arrays.*;

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

    //
    public void create(List<String> ans, int n, String str, int left) {

        if (left < 0) {
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
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            }
            if (s.charAt(i) == ')') {
                right++;
            }
            if (left - right < 0) {
                return false;
            }
        }
        if (left != right) {
            return false;
        }
        return true;
    }

    //1189
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

    //    824
    public String toGoatLatin(String S) {
        int left = 0, right = 0;
        String str = new String("maa");
        StringBuilder SB = new StringBuilder("");
        for (; right < S.length(); right++) {
            SB.append(S.charAt(right));
            if (S.charAt(right) == ' ') {

                if (S.charAt(left) != 'a' && S.charAt(left) != 'e' && S.charAt(left) != 'i' && S.charAt(left) != 'o' && S.charAt(left) != 'u' &&
                        S.charAt(left) != 'A' && S.charAt(left) != 'E' && S.charAt(left) != 'I' && S.charAt(left) != 'O' && S.charAt(left) != 'U') {
                    char temp = S.charAt(left);
                    SB.replace(left, right - 1, SB.substring(left + 1, right));
                    SB.setCharAt(right - 1, temp);

                }
                left = right + 1;
            }

        }
        if (S.charAt(left) != 'a' && S.charAt(left) != 'e' && S.charAt(left) != 'i' && S.charAt(left) != 'o' && S.charAt(left) != 'u' &&
                S.charAt(left) != 'A' && S.charAt(left) != 'E' && S.charAt(left) != 'I' && S.charAt(left) != 'O' && S.charAt(left) != 'U') {
            char temp = S.charAt(left);
            SB.replace(left, right - 1, SB.substring(left + 1, right));
            SB.setCharAt(right - 1, temp);
        }

        right = 0;
        for (; right < SB.length(); right++) {

            if (SB.charAt(right) == ' ') {
                SB.insert(right, str);
                str += "a";
                right += str.length() - 1;

            }
        }
        SB.insert(right, str);
        return SB.toString();
    }

    //    38
    public String countAndSay(int n) {
        char front = '0';
        String s = "1";
        String s1 = "";
        int count = 0;
        if (n == 1) {
            return s;
        }
        for (int i = 2; i <= n; i++) {
            int j = 0;
            for (j = 0; j < s.length(); j++) {

                if (front != s.charAt(j)) {
                    if (count != 0) {
                        s1 += Integer.toString(count);
                        s1 += front;
                    }
                    front = s.charAt(j);
                    count = 0;

                }
                count++;
            }
            s1 += Integer.toString(count);
            s1 += front;
            front = s.charAt(j - 1);
            count = 0;
            s = s1;
            s1 = "";
        }
        return s;
    }

    //    面试17.17
    public int[][] multiSearch(String big, String[] smalls) {
//        List<int []> pos_list = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        int[][] pos_array = new int[smalls.length][];
        int count = -1;
        for (String s : smalls) {
            count++;
            int left = 0;
            int right = Math.max(left + s.length(), 1);
            for (; right <= big.length(); left++, right++) {
                if (big.substring(left, right).equals(s)) {
//                    System.out.println(s);
                    pos.add(left);
                }
            }
//            pos_list.add(pos);
            int[] subpos = new int[pos.size()];
            for (int i = 0; i < pos.size(); i++) {
                subpos[i] = pos.get(i);
            }
            pos_array[count] = subpos;
//            System.out.println(pos);
            pos.clear();
        }
        return pos_array;
    }

    //    966
    public String[] spellcheckertimeout(String[] wordlist, String[] queries) {
//        aeiou
        String[] ans = new String[queries.length];
        int count = -1;
        boolean flag = false;
        boolean flag1 = false;
        for (String query : queries) {
            count++;
            flag = false;
            flag1 = false;
            String s = "";

            for (String word : wordlist) {
                if (query.equals(word)) {
                    ans[count] = word;
                    break;
                } else if (query.toLowerCase().equals(word.toLowerCase()) && !flag) {
                    flag = true;
                    ans[count] = word;
                } else if (!flag1 && !flag) {
                    if (query.length() != word.length()) {
                        ans[count] = "";
                        continue;
                    }
//                    System.out.println(word);
                    for (int i = 0; i < query.length(); i++) {
                        if (word.charAt(i) - query.charAt(i) == 32 || word.charAt(i) - query.charAt(i) == -32 || word.charAt(i) - query.charAt(i) == 0 ||
                                ((word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' ||
                                        word.charAt(i) == 'A' || word.charAt(i) == 'E' || word.charAt(i) == 'I' || word.charAt(i) == 'O' || word.charAt(i) == 'U')
                                        && (query.charAt(i) == 'a' || query.charAt(i) == 'e' || query.charAt(i) == 'i' || query.charAt(i) == 'o' || query.charAt(i) == 'u' ||
                                        query.charAt(i) == 'A' || query.charAt(i) == 'E' || query.charAt(i) == 'I' || query.charAt(i) == 'O' || query.charAt(i) == 'U'))) {
//                            System.out.println(i);
//                            System.out.println(query.length()-1);
                            if (query.length() - 1 == i) {
//                                System.out.println("hhh");
                                ans[count] = word;
                                flag1 = true;
                            }
                            continue;
                        }
//                        System.out.println("kkk");
                        ans[count] = "";
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        String[] ans = new String[queries.length];
        Set<String> word_perfect = new HashSet<>();
        Map<String, String> words_cap = new HashMap<>();
        Map<String, String> words_vow = new HashMap<>();
        for (String word : wordlist) {
            word_perfect.add(word);
            words_cap.putIfAbsent(word.toLowerCase(), word);
            words_vow.putIfAbsent(wordtostar(word), word);
        }
        System.out.println(word_perfect);
        System.out.println(words_cap);
        System.out.println(words_vow);
        for (int i = 0; i < queries.length; i++) {
            if (word_perfect.contains(queries[i])) {
                ans[i] = queries[i];
                continue;
            }
            ans[i] = "";
        }
        for (int i = 0; i < queries.length; i++) {
            if (!ans[i].equals("")) {
                continue;
            }
            if (words_cap.get(queries[i].toLowerCase()) != null) {
                ans[i] = words_cap.get(queries[i].toLowerCase());
            }
        }
        for (int i = 0; i < queries.length; i++) {
            if (!ans[i].equals("")) {
                continue;
            }
            if (words_vow.get(wordtostar(queries[i])) != null) {
                ans[i] = words_vow.get(wordtostar(queries[i]));
            }
        }
        return ans;
    }

    private String wordtostar(String word) {
        StringBuilder word_replace = new StringBuilder(word.toLowerCase());
        for (int i = 0; i < word.length(); i++) {
            if (word_replace.charAt(i) == 'a' || word_replace.charAt(i) == 'e' || word_replace.charAt(i) == 'i' || word_replace.charAt(i) == 'o' || word_replace.charAt(i) == 'u') {
                word_replace.replace(i, i + 1, "*");
            }
        }
        return new String(word_replace);
    }

    //    1370
    public String sortString(String s) {
        char[] sc = s.toCharArray();
        Arrays.sort(sc);
        char front = '0';
        int length = sc.length;
        StringBuilder ans = new StringBuilder();
        StringBuilder s1 = new StringBuilder(String.valueOf(sc));
        while (length > 0) {
            front = '0';
            for (int i = 0; i < s1.length(); i++) {
                if (front != s1.charAt(i)) {
                    ans.append(s1.charAt(i));
                    front = s1.charAt(i);
                    s1.deleteCharAt(i);
                    length--;
                    i--;
                }
            }
            front = '0';
            for (int i = s1.length() - 1; i >= 0; i--) {

                if (front != s1.charAt(i)) {
                    ans.append(s1.charAt(i));
                    front = s1.charAt(i);
                    s1.deleteCharAt(i);
                    length--;
//                    System.out.println(ans);
                }
            }

        }

        return ans.toString();
    }

    private String[] quickSort(String[] folder, int left, int right) {
        if (left == right) {
            return folder;
        }


        int l = left, r = right;
        int flag_length = folder[left].length();
        String flag_str = folder[left];
        int flag_pos = left;
        while (left < right) {

            while (folder[right].length() >= flag_length && left < right) {
                right--;
            }
            if (left < right) {
                String temp = folder[right];
                folder[right] = flag_str;
                folder[flag_pos] = temp;
                flag_pos = right;
                right--;
            }

            while (folder[left].length() <= flag_length && left < right) {
                left++;
            }
            if (left < right) {
                String temp = folder[left];
                folder[left] = flag_str;
                folder[flag_pos] = temp;
                flag_pos = left;
                left++;

            }


        }


        quickSort(folder, l, left);
        quickSort(folder, left + 1, r);
        return folder;
    }

    //    1233
    public List<String> removeSubfolders(String[] folder) {

        int left = 0;
        int right = folder.length - 1;
        quickSort(folder, left, right);
        Set<String> vec = new HashSet<>();
        List<String> ls = new ArrayList<>();

        for (int j = 0; j < folder.length; j++) {
            for (int i = 1; i < folder[j].length(); i = i + 1) {
                if (folder[j].charAt(i) == '/') {
                    if (vec.contains(folder[j].substring(0, i))) {
                        break;
                    }
                }
                if (i == folder[j].length() - 1) {
                    vec.add(folder[j]);
                    ls.add(folder[j]);
                }
            }
        }
        return ls;
    }

    //    28
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int left = 0;
        int right = needle.length();
        for (; right <= haystack.length(); left++, right++) {
            if (haystack.substring(left, right).equals(needle)) {
                return left;
            }
        }
        return -1;
    }

    //12
    public String intToRoman(int num) {
        //        I             1
        //V             5
        //X             10
        //L             50
        //C             100
        //D             500
        //M             1000
        StringBuilder ans = new StringBuilder();


        int M_num = num / 1000;
        int D_num = (num - M_num * 1000) / 500;
        int C_num = (num - M_num * 1000 - D_num * 500) / 100;
        int L_num = (num - M_num * 1000 - D_num * 500 - C_num * 100) / 50;
        int X_num = (num - M_num * 1000 - D_num * 500 - C_num * 100 - L_num * 50) / 10;
        int V_num = (num - M_num * 1000 - D_num * 500 - C_num * 100 - L_num * 50 - X_num * 10) / 5;
        int I_num = num - M_num * 1000 - D_num * 500 - C_num * 100 - L_num * 50 - X_num * 10 - V_num * 5;

        for (int i = 0; i < M_num; i++) {
            ans.append("M");
        }
        for (int i = 0; i < D_num; i++) {
            if (num - M_num * 1000 >= 900) {
                ans.append("CM");
                break;
            }
            ans.append("D");
        }
        for (int i = 0; i < C_num; i++) {
            if (num - M_num * 1000 >= 900) {
                break;
            }
            if (num - M_num * 1000 - D_num * 500 >= 400) {
                ans.append("CD");
                break;
            }
            ans.append("C");
        }
        for (int i = 0; i < L_num; i++) {
            if (num - M_num * 1000 - D_num * 500 - C_num * 100 >= 90) {
                ans.append("XC");
                break;
            }
            ans.append("L");
        }
        for (int i = 0; i < X_num; i++) {
            if (num - M_num * 1000 - D_num * 500 - C_num * 100 >= 90) {
                break;
            }
            if (num - M_num * 1000 - D_num * 500 - C_num * 100 - L_num * 50 >= 40) {
                ans.append("XL");
                break;
            }
            ans.append("X");
        }
        for (int i = 0; i < V_num; i++) {
            if (num - M_num * 1000 - D_num * 500 - C_num * 100 - L_num * 50 - X_num * 10 >= 9) {
                ans.append("IX");
                break;
            }
            ans.append("V");
        }
        for (int i = 0; i < I_num; i++) {
            if (num - M_num * 1000 - D_num * 500 - C_num * 100 - L_num * 50 - X_num * 10 >= 9) {
                break;
            }
            if (num - M_num * 1000 - D_num * 500 - C_num * 100 - L_num * 50 - X_num * 10 - V_num * 5 >= 4) {
                ans.append("IV");
                break;
            }
            ans.append("I");
        }
        return ans.toString();
    }


    //    1170
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] queries_num = fStingList(queries);
        int[] words_num = fStingList(words);
        int[] num = new int[queries_num.length];
        for (int i = 0; i < queries.length; i++) {
            int count = 0;
            for (int j = 0; j < words.length; j++) {
                if (queries_num[i] < words_num[j]) {
                    count++;
                }
            }
            num[i] = count;
        }
        return num;
    }

    public int[] fStingList(String[] array) {
        int[] array_num = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            array_num[i] = f(array[i]);
        }
        return array_num;
    }

    public int f(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        char c0 = c[0];
        int i = 0;
        for (; i < s.length(); i++) {
            if (c[i] != c0) {
                break;
            }
        }
        return i;
    }

    //    434
    public int countSegments(String s) {
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && !flag) {
                flag = true;
                count++;
                continue;
            }
            if (s.charAt(i) == ' ')
                flag = false;
        }
        return count;
    }

    //    1545
    public char findKthBit(int n, int k) {
        String s = "0";
        for (int i = 0; i < n - 1; i++) {
            StringBuilder s_invert = invertString(s).reverse();
            s += "1" + s_invert.toString();
        }

        return s.charAt(k - 1);
    }

    public StringBuilder invertString(String s) {
        StringBuilder s_invert = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                s_invert.append('1');
            } else {
                s_invert.append('0');
            }
        }
        return s_invert;
    }

    //    537
    public String complexNumberMultiply(String a, String b) {
        int a_num = 0, ai_num = 0;
        int b_num = 0, bi_num = 0;
        StringBuilder ac = new StringBuilder();
        StringBuilder bc = new StringBuilder();
        StringBuilder str = new StringBuilder();
        int count1 = 0, count2 = 0;

        if (a.charAt(count1) == '-') {
            str.append('-');
            count1++;
        }
        while (a.charAt(count1) != '+' && a.charAt(count1) != '-') {
            str.append(a.charAt(count1));
            count1++;
        }

        a_num = Integer.parseInt(str.toString());
        str.delete(0, str.length());
        while (a.charAt(count1) == '+' || a.charAt(count1) == '-') {
            ac.append(a.charAt(count1));
            count1++;
        }
        while (a.charAt(count1) != 'i') {
            str.append(a.charAt(count1));
            count1++;
        }

        ai_num = Integer.parseInt(str.toString());
        str.delete(0, str.length());

        count1 = 0;
        if (b.charAt(count1) == '-') {
            str.append('-');
            count1++;
        }
        while (b.charAt(count1) != '+' && b.charAt(count1) != '-') {
            str.append(b.charAt(count1));
            count1++;
        }

        b_num = Integer.parseInt(str.toString());
        str.delete(0, str.length());
        while (b.charAt(count1) == '+' || b.charAt(count1) == '-') {
            bc.append(b.charAt(count1));
            count1++;
        }
        while (b.charAt(count1) != 'i') {
            str.append(b.charAt(count1));
            count1++;
        }
        bi_num = Integer.parseInt(str.toString());

//        System.out.println(a+a_num+ac+ai_num);
//        System.out.println(b+b_num+bc+bi_num);
        int w = a_num * b_num;
        int wi = ai_num * bi_num;
        int w1 = a_num * bi_num;
        int w2 = b_num * ai_num;
        int first = 0;
        char second = '+';
        int third = 0;
        StringBuilder ans = new StringBuilder();
        if (ac.toString().equals("+-")) {
            ac.replace(0, ac.length(), "-");
        }
        if (bc.toString().equals("+-")) {
            bc.replace(0, bc.length(), "-");
        }
        if (ac.toString().equals("+") && bc.toString().equals("+")) {
            first = w - wi;
            second = '+';
            third = w1 + w2;
        }

        if (ac.toString().equals("-") && bc.toString().equals("-")) {
            first = w - wi;
            second = '+';
            third = -(w1 + w2);
        }
        if (ac.toString().equals("+") && bc.toString().equals("-")) {
            first = w + wi;
            second = '+';
            third = w2 - w1;
        }
        if (ac.toString().equals("-") && bc.toString().equals("+")) {
            first = w + wi;
            second = '+';

            third = w1 - w2;
        }
        ans.append(String.valueOf(first));
        ans.append(second);
        ans.append(String.valueOf(third) + 'i');

        return ans.toString();
    }

    public String complexNumberMultiply2(String a, String b) {
        String x[] = a.split("\\+|i");
        String y[] = b.split("\\+|i");

        int a_real = Integer.parseInt(x[0]);
        int a_img = Integer.parseInt(x[1]);
        int b_real = Integer.parseInt(y[0]);
        int b_img = Integer.parseInt(y[1]);
        return (a_real * b_real - a_img * b_img) + "+" + (a_real * b_img + a_img * b_real) + "i";

    }

    // 剑指offer53
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[0] != 0) {
            return 0;
        }
        if (nums.length - 1 == nums[right]) {
            return nums[right] + 1;
        }
        while (right - left != 1) {
            if (right - (right + left) / 2 < (nums[right] - nums[(left + right) / 2])) {
                left = (right + left) / 2;

            } else {
                right = (right + left) / 2;

            }
        }
        return (nums[left] + nums[right]) / 2;
    }

    //  674
    public int findLengthOfLCIS(int[] nums) {
        int min = -100000;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                if (count2 > count1) {
                    count1 = count2;
                }
                count2 = 0;
            }
            min = nums[i];
            count2++;
        }
        return Math.max(count1, count2);
    }

    //    1144
    public int movesToMakeZigzag(int[] nums) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < nums.length; i += 2) {
            if (i == 0) {
                if (nums[0] >= nums[1]) {
                    count1 += nums[0] - nums[1] + 1;
                }
                continue;
            }
            if (i == nums.length - 1) {
                if (nums[i] >= nums[i - 1]) {
                    count1 += nums[i] - nums[i - 1] + 1;
                }
                continue;
            }
            int k = 0;
            if (nums[i] >= nums[i + 1]) {
                k = nums[i] - nums[i + 1] + 1;
            }
            if (nums[i] >= nums[i - 1]) {
                k = Math.max(nums[i] - nums[i - 1] + 1, k);
            }
            count1 += k;
        }
        for (int i = 1; i < nums.length; i += 2) {
            if (i == nums.length - 1) {
                if (nums[i] >= nums[i - 1]) {
                    count2 += nums[i] - nums[i - 1] + 1;
                }
                continue;
            }
            int k = 0;
            if (nums[i] >= nums[i + 1]) {
                k = nums[i] - nums[i + 1] + 1;
            }
            if (nums[i] >= nums[i - 1]) {
                k = Math.max(nums[i] - nums[i - 1] + 1, k);
            }
            count2 += k;
        }
        return Math.min(count1, count2);
    }

    //  1333
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
//        Map<Integer,Integer> map = new HashMap();
        List<int[]> idTorating = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        for (int[] restaurant : restaurants) {
            if (veganFriendly == 1 && restaurant[2] == 0) {
                continue;
            }
            if (restaurant[3] <= maxPrice && restaurant[4] <= maxDistance) {
//                System.out.println(restaurant[0]);
                idTorating.add(new int[]{restaurant[0], restaurant[1]});
            }
        }
//        for (int []id:idTorating){
//            System.out.println(id[0]);
//        }
        idTorating.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });
        idTorating.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[1], o1[1]);
            }
        });

        for (int[] id : idTorating) {
            idList.add(id[0]);
        }
        return idList;

    }

    //    35
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[(left + right) / 2] < target) {
                left = (left + right) / 2 + 1;
            } else if (nums[(left + right) / 2] > target) {
                right = (left + right) / 2 - 1;
            } else {
                return (left + right) / 2;
            }
        }
        if (nums[left] < target) {
            return left + 1;
        }
        return left;
    }

    //    643
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        double mean = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        mean = sum / k;
        for (int i = 1; i < nums.length - k + 1; i++) {
            sum = sum - nums[i - 1] + nums[i + k - 1];
            if (sum / k > mean) {
                mean = sum / k;
            }
        }
        return mean;
    }

    //     457
    public boolean circularArrayLoop(int[] nums) {
        int index = -1;
        int direction = 1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            index = i;
            count = 0;
            direction = nums[i] > 0 ? 1 : -1;
            while (nums[index] / direction > 0) {
                count++;
                if (index == (index + nums[index] + nums.length * 1000) % nums.length) {
                    break;
                }
                index = (index + nums[index] + nums.length * 1000) % nums.length;
                if (count == nums.length) {
                    return true;
                }
            }
        }
        return false;
    }

    //    792
    public int numMatchingSubseqTimeOut(String S, String[] words) {
        int count = 0;
        int j = 0;
        int index1 = 0;
        int index2 = 0;
        for (String word : words) {
            j = 0;
            index1 = 0;
            index2 = 0;
            for (; index1 < S.length(); index1++) {

                index2 = S.substring(index1).indexOf(word.charAt(j++));
                if (index2 == -1) {
                    break;
                }
                index1 += index2;
                if (j == word.length()) {
                    count++;
                    break;
                }

            }
        }
        return count;
    }

    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, List<String>> map = new HashMap<>();
        int count = 0;
        for (int i = 97; i < 123; i++) {
            map.put((char) (i), new ArrayList<>());
        }
        for (String word : words) {
            map.get(word.charAt(0)).add(word);
        }
        for (int i = 0; i < S.length(); i++) {
            List<String> list = new ArrayList<>(map.get(S.charAt(i)));
//            System.out.println(map);
            for (String j : list) {

                map.get(S.charAt(i)).remove(j);
                j = j.substring(1);
                if (j.equals("")) {
                    count++;
                } else {
//                    System.out.println(map.get(j.charAt(0)));

                    map.get(j.charAt(0)).add(j);
                }
            }
        }
        return count;
    }

    //    867
    public int[][] transpose(int[][] A) {
        int height = A.length;
        int width = A[0].length;
        int[][] B = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }

    //    面试17。10
    public int majorityElement(int[] nums) {
        int count = 0;
        int flag = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == flag) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                flag = nums[i];
                count = 1;
            }
        }
        int t = nums.length / 2 + 1;
        count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == flag) {
                count++;
            }
            if (t == count) {
                return flag;
            }
        }
        return -1;
    }

    //    1476
    int[][] rectangle;
//    public HelloWorld(int[][] rectangle) {
//         this.rectangle = rectangle;
//    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                this.rectangle[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return this.rectangle[row][col];
    }

    //     1535
    public int getWinner(int[] arr, int k) {
        int count = 0;
        List<Integer> arr_list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        if (arr.length <= k) {
            int max = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            return max;
        }
        while (true) {
//            System.out.println(arr_list);
            if (arr_list.get(0) > arr_list.get(1)) {
                int temp = arr_list.get(1);
                arr_list.remove(1);
                arr_list.add(temp);
                count++;
            } else {
                int temp = arr_list.get(0);
                arr_list.remove(0);
                arr_list.add(temp);
                count = 1;
            }
            if (count == k) {
                return arr_list.get(0);
            }
        }
    }

    //    977
    public void quickSortInt(int[] A, int left, int right) {
        int head = left;
        int tail = right;
        if (left >= right) {
            return;
        }
        int flag = A[left];
        int flag_pos = left;

        while (left < right) {
            while (left < right && A[right] >= flag) {
                right--;
            }
            A[flag_pos] = A[right];
            flag_pos = right;

            while (left < right && A[left] <= flag) {
                left++;
            }
            A[flag_pos] = A[left];
            flag_pos = left;
        }

        A[left] = flag;
//        for (int a : A)
//            System.out.print(a + " ");

//        System.out.println();
//        System.out.println(left);
//        System.out.println(right);
        quickSortInt(A, head, left - 1);
        quickSortInt(A, left + 1, tail);
    }

    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        int left = 0;
        int right = A.length - 1;
        quickSortInt(A, left, right);
        return A;
    }

    //    628
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }

            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            } else if (nums[i] > max3) {
                max3 = nums[i];
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
//        return ans3;
    }

    //1552
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int hi = (position[position.length - 1] - position[0]) / (m-1);
        int low = 1;
        int mid = 0;


        while (low < hi) {
            mid = (hi + low) / 2;
            if (check(position, mid, m-1)) {
                low = mid + 1;
            } else
                hi = mid - 1;
        }
        return low - 1;
    }

    public boolean check(int[] position, int mid, int m) {
        int j = 0;
        int count = 0;
        for (int i =0;i<position.length;i++){
            if (position[i] - position[j] >= mid){
                count++;
                j=i;
            }
            if (count >= m){
                return true;
            }
        }
        return false;
    }

    //  1438
    public int longestSubarrayTimeOut(int[] nums, int limit) {
        int min = nums[0];
        int max = nums[0];
        int min_pos = 0;
        int max_pos = 0;
        int max_count = 0;
        int count = 0;
//      {4,2,2,2,4,4,2,2}
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                max_pos = i;
                if (Math.abs(max - min) > limit) {
                    i = min_pos;
                    min = nums[i + 1];
                    min_pos = i + 1;
                    max = nums[i + 1];
                    max_pos = i + 1;
                    if (max_count < count)
                        max_count = count;
                    count = 0;
                    continue;
                }

            }
            if (nums[i] < min) {
                min = nums[i];
                min_pos = i;
                if (Math.abs(max - min) > limit) {
                    i = max_pos;
                    max = nums[i + 1];
                    max_pos = i + 1;
                    min = nums[i + 1];
                    min_pos = i + 1;
                    if (max_count < count)
                        max_count = count;
                    count = 0;
                    continue;
                }

            }
            count++;
        }
        return Math.max(max_count, count);
    }

    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int i = 0, j = 0;
        int ans = 0;
        for (; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else
                map.compute(nums[i], (k, v) -> {
                    return ++v;
                });
            while (map.lastKey() - map.firstKey() > limit) {
                map.compute(nums[j], (k, v) -> {
                    return --v;
                });
                if (map.get(nums[j]) == 0) {
                    map.remove(nums[j]);
                }
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }

        return ans;
    }

//    1550
    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i=0;i<arr.length-2;i++){
            if (arr[i] % 2 == 1 && arr[i+1] % 2 == 1 && arr[i+2] % 2 == 1){
                return true;
            }
        }
        return false;
    }

//    219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.get(nums[i]) == null){
                map.put(nums[i], i);
            }else {
                if (i - map.get(nums[i]) <= k){
                    return true;
                }else{
                    map.put(nums[i], i);
                }
            }
        }
        return false;
    }

//    228
    public List<String> summaryRanges(int[] nums) {
        List<String> ls = new ArrayList<>();
        int []nums2 = new int[nums.length+1];
        if (nums.length == 0){
            return ls;
        }
        for (int i =0;i<nums.length;i++)
        {
            nums2[i] = nums[i];
        }
        nums2[nums2.length-1] = nums[nums.length-1]+2;

        int left = 0;
        for (int i =0;i<nums2.length-1;i++){
            if (nums2[i+1] - nums2[i] != 1){
                if (i == left){
                    ls.add(String.valueOf(nums2[left]));
                    left ++;
                }else {
                    ls.add(nums2[left] + "->" + nums2[i]);
                    left = i + 1;
                }

            }
        }
        return ls;
    }

//    1267
    public int countServers(int[][] grid) {
        HashSet<String> vec = new HashSet<>();
        List<Integer> true_conn_x = new ArrayList<>();
        List<Integer> true_conn_y = new ArrayList<>();
        int count = 0;
        for (int i=0;i<grid.length;i++){
            count = 0;
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == 1){
                    count++;
                }
                if (count == 2){
                    true_conn_x.add(i);
                    break;
                }
            }
        }
        for (int i=0;i<grid[0].length;i++){
            count = 0;
            for (int j=0;j<grid.length;j++){
                if (grid[j][i] == 1){
                    count++;
                }
                if (count == 2){
                    true_conn_y.add(i);
                    break;
                }
            }
        }

        for (int x:true_conn_x){
            for (int j=0;j<grid[0].length;j++){
                if (grid[x][j] == 1){
                    vec.add(x+" "+j);
                }
            }
        }
        for (int y:true_conn_y){
            for (int j=0;j<grid.length;j++){
                if (grid[j][y] == 1){
                    vec.add(j+" "+y);
                }
            }
        }

        return vec.size();
    }
    public static void main(String[] args) {

        int [][]grid = {{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
        HelloWorld hello_world = new HelloWorld();
        int ans = hello_world.countServers(grid);
        System.out.println(ans);

    }
}