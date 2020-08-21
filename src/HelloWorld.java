import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.print.DocFlavor;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

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
        if (n == 1){
            return s;
        }
        for (int i = 2; i <= n; i++) {
            int j = 0;
            for ( j = 0; j < s.length(); j++) {

                if (front != s.charAt(j)) {
                    if (count !=0){
                        s1 += Integer.toString(count);
                        s1 += front;
                    }
                    front = s.charAt(j);
                    count = 0;

                }
                count ++;
            }
            s1 += Integer.toString(count);
            s1 += front;
            front = s.charAt(j-1);
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
        int [][]pos_array = new int [smalls.length][];
        int count = -1;
        for (String s:smalls){
            count ++;
            int left = 0;
            int right = Math.max(left + s.length(),1) ;
            for(;right<=big.length();left++,right++){
                if (big.substring(left, right).equals(s))
                {
//                    System.out.println(s);
                    pos.add(left);
                }
            }
//            pos_list.add(pos);
            int[] subpos = new int[pos.size()];
            for (int i = 0;i<pos.size();i++){
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
        String []ans = new String[queries.length];
        int count = -1;
        boolean flag = false;
        boolean flag1 = false;
        for (String query:queries){
            count++;
            flag = false;
            flag1 =false;
            String s = "";

            for (String word:wordlist){
                if (query.equals(word)){
                    ans[count] = word;
                    break;
                }
                else if (query.toLowerCase().equals(word.toLowerCase()) && !flag){
                    flag = true;
                    ans[count] = word;
                }
                else if (!flag1 && !flag){
                    if (query.length() != word.length()){
                        ans[count] = "";
                        continue;
                    }
//                    System.out.println(word);
                    for (int i=0;i<query.length();i++){     
                        if (word.charAt(i)-query.charAt(i) == 32 || word.charAt(i)-query.charAt(i) == -32 || word.charAt(i)-query.charAt(i) == 0 ||
                                ((word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' ||
                                        word.charAt(i) == 'A' || word.charAt(i) == 'E' || word.charAt(i) == 'I' || word.charAt(i) == 'O' || word.charAt(i) == 'U' )
                        && (query.charAt(i) == 'a' || query.charAt(i) == 'e' || query.charAt(i) == 'i' || query.charAt(i) == 'o' || query.charAt(i) == 'u' ||
                                        query.charAt(i) == 'A' || query.charAt(i) == 'E' || query.charAt(i) == 'I' || query.charAt(i) == 'O' || query.charAt(i) == 'U'))){
//                            System.out.println(i);
//                            System.out.println(query.length()-1);
                            if (query.length() - 1 == i){
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

    public String[] spellchecker(String[] wordlist, String[] queries){
        String []ans = new String[queries.length];
        Set<String> word_perfect = new HashSet<>();
        Map<String, String> words_cap = new HashMap<>();
        Map<String, String> words_vow = new HashMap<>();
        for (String word:wordlist){
            word_perfect.add(word);
            words_cap.putIfAbsent(word.toLowerCase(), word);

            words_vow.putIfAbsent(wordtostar(word), word);
        }
        System.out.println(word_perfect);
        System.out.println(words_cap);
        System.out.println(words_vow);
        for (int i =0 ;i<queries.length;i++){
            if(word_perfect.contains(queries[i])){
                ans[i] = queries[i];
                continue;
            }
            ans[i] = "";
        }
        for (int i =0 ;i<queries.length;i++){
            if (!ans[i].equals("")){
                continue;
            }
            if(words_cap.get(queries[i].toLowerCase()) != null){
                ans[i] = words_cap.get(queries[i].toLowerCase());
            }
        }
        for (int i =0 ;i<queries.length;i++){
            if (!ans[i].equals("")){
                continue;
            }
            if(words_vow.get(wordtostar(queries[i])) != null){
                ans[i] = words_vow.get(wordtostar(queries[i]));
            }
        }
        return ans;
    }
    private String wordtostar(String word){
        StringBuilder word_replace = new StringBuilder(word.toLowerCase());
        for (int i =0;i<word.length();i++){
            if(word_replace.charAt(i) == 'a' || word_replace.charAt(i) == 'e' || word_replace.charAt(i) == 'i' || word_replace.charAt(i) == 'o' || word_replace.charAt(i) == 'u'){
                word_replace.replace(i, i+1, "*");
            }
        }
        return new String(word_replace);
    }
    public static void main(String[] args) {
        String []wordlist = {"YellOw"};
        String []queries = {"yollow"};

        HelloWorld hello_world = new HelloWorld();
        String []ans = hello_world.spellchecker(wordlist, queries);
        for (String s: ans){
            System.out.print(s + " ");
        }

    }
}