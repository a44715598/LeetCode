import com.sun.org.apache.bcel.internal.generic.SWAP;
import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.net.ssl.SSLContext;
import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Arrays.*;
import java.math.BigDecimal;

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
        int hi = (position[position.length - 1] - position[0]) / (m - 1);
        int low = 1;
        int mid = 0;


        while (low < hi) {
            mid = (hi + low) / 2;
            if (check(position, mid, m - 1)) {
                low = mid + 1;
            } else
                hi = mid - 1;
        }
        return low - 1;
    }

    public boolean check(int[] position, int mid, int m) {
        int j = 0;
        int count = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] - position[j] >= mid) {
                count++;
                j = i;
            }
            if (count >= m) {
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
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] % 2 == 1 && arr[i + 1] % 2 == 1 && arr[i + 2] % 2 == 1) {
                return true;
            }
        }
        return false;
    }

    //    219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], i);
            } else {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            }
        }
        return false;
    }

    //    228
    public List<String> summaryRanges(int[] nums) {
        List<String> ls = new ArrayList<>();
        int[] nums2 = new int[nums.length + 1];
        if (nums.length == 0) {
            return ls;
        }
        for (int i = 0; i < nums.length; i++) {
            nums2[i] = nums[i];
        }
        nums2[nums2.length - 1] = nums[nums.length - 1] + 2;

        int left = 0;
        for (int i = 0; i < nums2.length - 1; i++) {
            if (nums2[i + 1] - nums2[i] != 1) {
                if (i == left) {
                    ls.add(String.valueOf(nums2[left]));
                    left++;
                } else {
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
        for (int i = 0; i < grid.length; i++) {
            count = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
                if (count == 2) {
                    true_conn_x.add(i);
                    break;
                }
            }
        }
        for (int i = 0; i < grid[0].length; i++) {
            count = 0;
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == 1) {
                    count++;
                }
                if (count == 2) {
                    true_conn_y.add(i);
                    break;
                }
            }
        }

        for (int x : true_conn_x) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[x][j] == 1) {
                    vec.add(x + " " + j);
                }
            }
        }
        for (int y : true_conn_y) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][y] == 1) {
                    vec.add(j + " " + y);
                }
            }
        }

        return vec.size();
    }

    //    1
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                return new int[]{i, map.get(nums[i])};
            } else
                map.put(target - nums[i], i);
        }
        return new int[]{};
    }

    //    26
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;
        for (; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[i + 1] = nums[j];
                i++;
            }
        }
        return i + 1;
    }

    //    11
    public int maxAreaTimeOut(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            }
        }
        return max;
    }

    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                max = Math.max(height[left] * (right - left), max);
                left++;
            } else if (height[left] > height[right]) {
                max = Math.max(height[right] * (right - left), max);
                right--;
            } else {
                max = Math.max(height[right] * (right - left), max);
                left++;
                right--;
            }
        }
        return max;
    }

    //    15
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> llInt = new ArrayList<>();
        Arrays.sort(nums);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    List<Integer> lInt = new ArrayList<>();
                    if (!set.contains(nums[i] + "" + nums[left] + "" + nums[right])) {
                        set.add(nums[i] + "" + nums[left] + "" + nums[right]);
                        lInt.add(nums[i]);
                        lInt.add(nums[left]);
                        lInt.add(nums[right]);
                        llInt.add(lInt);
                    }
                    left++;
                    right--;
                }
            }
        }
        return llInt;
    }


    //  605
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1 && flowerbed[0] == 0) {
            return true;
        }
        for (int i = 0; i < flowerbed.length - 1; i++) {
            if (flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                i++;
                n--;
            }
            if (flowerbed[i] == 1 && flowerbed[i + 1] == 0) {
                i++;
            }
        }
        if (flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) {
            n--;
        }
        if (n <= 0) {
            return true;
        }
        return false;
    }

    //    832
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] a : A) {
            int left = 0;
            int right = a.length - 1;
            while (left <= right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                a[left] = a[left] == 0 ? 1 : 0;
                a[right] = a[right] == 0 ? 1 : 0;
                if (left == right) {
                    a[right] = a[right] == 0 ? 1 : 0;
                }
                left++;
                right--;
            }
        }
        return A;
    }

    //    120
//    递归
    Integer[][] memo;

    public int minimumTotal1(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);
    }

    public int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        return memo[i][j] = Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }

    //    dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    //  84
    public int largestRectangleArea1(int[] heights) {
        int max = 0;
        int width = 0;
        for (int i = 0; i < heights.length; i++) {
            width = 1;
            int j = i - 1;
            int k = i + 1;
            while (j >= 0 && heights[i] <= heights[j--])
                width++;
            while (k <= heights.length - 1 && heights[i] <= heights[k++])
                width++;
            max = Math.max(max, width * heights[i]);
        }
        return max;
    }

    public int largestRectangleArea2(int[] heights) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        if (heights.length == 1) {
            return heights[0];
        }
        if (heights.length == 0) {
            return 0;
        }
        stack.add(0);
        for (int i = 1; i < heights.length; i++) {

            while (heights[stack.getLast()] >= heights[i]) {
                int last = stack.getLast();
                stack.removeLast();
                if (stack.isEmpty()) {
                    max = Math.max(max, (i * heights[last]));
                    break;
                }

                max = Math.max(max, (i - stack.getLast() - 1) * heights[last]);
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            int right = heights.length - 1 - stack.getLast();
            int last = stack.getLast();
            stack.removeLast();
            if (stack.isEmpty()) {
                max = Math.max(max, heights.length * heights[last]);
                break;
            }
            int left = last - stack.getLast();
            max = Math.max(max, (right + left) * heights[last]);
        }
        return max;
    }

    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        if (heights.length == 1) {
            return heights[0];
        }
        if (heights.length == 0) {
            return 0;
        }
        int[] heights_cp = new int[heights.length + 2];
        heights_cp[0] = 0;
        heights_cp[heights_cp.length - 1] = 0;
        for (int i = 0; i < heights.length; i++) {
            heights_cp[i + 1] = heights[i];
        }
        for (int i = 0; i < heights_cp.length; i++) {
            if (stack.isEmpty()) {
                stack.add(i);
                continue;
            }
            while (heights_cp[stack.getLast()] > heights_cp[i]) {
                int last = stack.getLast();
                stack.removeLast();
                max = Math.max(max, (i - stack.getLast() - 1) * heights_cp[last]);
            }
            stack.add(i);

        }
        return max;
    }

    //    474
//    1。递归
    Integer[][][] memo2;

    public int findMaxForm2(String[] strs, int m, int n) {
        memo2 = new Integer[strs.length][m + 1][n + 1];
        return dfs2(strs, m, n, 0);
    }

    public int dfs2(String[] strs, int m, int n, int i) {
        if (i == strs.length) {
            return 0;
        }
        if (memo2[i][m][n] != null) {
            return memo2[i][m][n];
        }
        int num0 = countNum(strs[i], '0');
        int num1 = countNum(strs[i], '1');
        if (m - num0 < 0 || n - num1 < 0) {
            return dfs2(strs, m, n, i + 1);
        }
        return memo2[i][m][n] = Math.max(dfs2(strs, m, n, i + 1), dfs2(strs, m - num0, n - num1, i + 1) + 1);
    }

    public int countNum(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int num0, num1;
        for (int i = 0; i < strs.length; i++) {
            num0 = countNum(strs[i], '0');
            num1 = countNum(strs[i], '1');
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j - num0 < 0 || k - num1 < 0) {
                        continue;
                    }
                    dp[j][k] = Math.max(dp[j][k], dp[j - num0][k - num1] + 1);
                }
            }
            for (int a = 0; a <= m; a++) {
                for (int b = 0; b <= n; b++) {
                    System.out.print(dp[a][b] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        return dp[m][n];
    }

    //        300
    int max = 0;

    public int lengthOfLIS1(int[] nums) {
        dfs3(nums, 0, new ArrayList<>());
        return max;
    }

    public void dfs3(int[] nums, int i, List<Integer> now_int) {
        if (i == nums.length) {
            checkLengthOfLIS(now_int);
            return;
        }

        checkLengthOfLIS(now_int);
        List<Integer> l1 = new ArrayList<>(now_int);
        List<Integer> l2 = new ArrayList<>(now_int);
        l1.add(nums[i]);

        dfs3(nums, i + 1, l1);
        dfs3(nums, i + 1, l2);
    }

    public void checkLengthOfLIS(List<Integer> now_int) {
        int a = Integer.MIN_VALUE;
        int count = 0;
        for (int b : now_int) {
            if (a >= b) {
                return;
            } else {
                a = b;
                count++;
                max = Math.max(max, count);
            }
        }
    }

    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        if (nums.length == 1) {
            return 1;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }

    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        Arrays.fill(tails, Integer.MIN_VALUE);
        if (nums.length == 1) {
            return 1;
        }
        int index = 0;
        for (int num : nums) {
            int i = 0;
            int j = index - 1;
            int mid = 0;
            while (i <= j) {
                mid = (i + j) / 2;
                if (tails[mid] > num) {
                    j = mid - 1;
                } else if (tails[mid] < num) {
                    i = mid + 1;
                } else {
                    i = mid;
                    break;
                }
            }
            tails[i] = num;
            if (i > index - 1) {
                index++;
            }
        }
        return index;
    }

    //    978
    public int maxTurbulenceSize(int[] A) {
        int[] dp1 = new int[A.length];
        int[] dp2 = new int[A.length];
        if (A.length == 0) {
            return 0;
        }
        int max = 1;
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
//        奇数大于偶数
        for (int i = 1; i < A.length; i++) {
            if (i % 2 == 1) {
                if (A[i - 1] < A[i]) {
                    dp1[i] = dp1[i - 1] + 1;
                }
            }
            if (i % 2 == 0) {
                if (A[i] < A[i - 1]) {
                    dp1[i] = dp1[i - 1] + 1;
                }
            }
            max = Math.max(dp1[i], max);
        }

        for (int i = 1; i < A.length; i++) {
            if (i % 2 == 1) {
                if (A[i - 1] > A[i]) {
                    dp2[i] = dp2[i - 1] + 1;
                }
            }
            if (i % 2 == 0) {
                if (A[i] > A[i - 1]) {
                    dp2[i] = dp2[i - 1] + 1;
                }
            }
            max = Math.max(dp2[i], max);
        }

        return max;
    }

    //    730
    public int countPalindromicSubsequences(String S) {
        int[] dp = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {

        }
        return 0;
    }

    //    787
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K + 1][n];
        for (int[] d : dp)
            Arrays.fill(d, 10000000);
        dp[0][src] = 0;

        for (int[] flight : flights) {
            if (flight[0] == src)
                dp[0][flight[1]] = flight[2];
        }
        for (int i = 1; i <= K; i++) {
            for (int[] flight : flights) {
                dp[i][flight[1]] = Math.min(Math.min(dp[i - 1][flight[1]], dp[i - 1][flight[0]] + flight[2]), dp[i][flight[1]]);
            }
        }
        return dp[K][dst] == 10000000 ? -1 : dp[K][dst];
    }

    public void out_list(int[][] flight_dst) {
        for (int i = 0; i < flight_dst.length; i++) {
            for (int j = 0; j < flight_dst[i].length; j++) {
                System.out.print(flight_dst[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void out_list(long[][] flight_dst) {
        for (int i = 0; i < flight_dst.length; i++) {
            for (int j = 0; j < flight_dst[i].length; j++) {
                System.out.print(flight_dst[i][j] + " ");
            }
            System.out.println();
        }
    }

    //    837
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
//    665
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i=0;i<nums.length-1;i++){
            if (nums[i] > nums[i+1]){
//                System.out.println(i);
                if (i==0){
                    count++;
                    nums[i] = nums[i+1];
                    continue;
                }
                if (nums[i-1] > nums[i+1]){
                    nums[i+1] = nums[i];
                }else
                    nums[i] = nums[i+1];
                count++;
                if (count == 2)
                    return false;
            }

        }
        return true;
    }
    public static void main(String[] args) {
        int []nums = {-1,4,2,3};
        HelloWorld hello_world = new HelloWorld();
        boolean ans = hello_world.checkPossibility(nums);
//        hello_world.out_list2(ans);
        System.out.println(ans);
    }
}