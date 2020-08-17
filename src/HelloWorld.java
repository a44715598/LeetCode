import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

public class HelloWorld {
//  709
    public String toLowerCase(String str) {
        StringBuilder str_bui = new StringBuilder(str);
        for (int i=0;i<str.length();i++){
            if (str_bui.charAt(i) >= 65 && str_bui.charAt(i) <= 90){
                str_bui.setCharAt(i, (char)(str_bui.charAt(i) + 32));
            }
        }

        return str_bui.toString();
    }

//    1177
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
//        创建字符串个数的二维数组
        int [][] num_char =  new int[s.length()+1][26];
//        预处理得到每一个点的出现次数（超时）
//        for (int i=0;i<s.length();i++){
//            for (int j=i;j<s.length();j++){
//                num_char[j+1][(int)(s.charAt(i) - 97)] ++;
//            }
//        }
        //        预处理得到每一个点的出现次数（不超时）
        for (int i=0;i<s.length();i++){
            num_char[i+1] = num_char[i].clone();
            num_char[i+1][(int)(s.charAt(i) - 97)] ++;
        }
        List<Boolean> queries_result = new ArrayList<Boolean>();


        for(int []query:queries){

            int []num = new int[26];
            boolean flag = false;
            int transform_num = 0;

            for(int i=0;i<26;i++){
                num[i] = num_char[query[1]+1][i] - num_char[query[0]][i];
                if(num[i] % 2 == 1){
                    if(flag){
                        transform_num ++;
                        flag = false;
                    }
                    else {
                        flag = true;
                    }
                }
            }
//            for(int nu:num){
//                System.out.print(nu);
//            }
//            System.out.println();
            if(query[2] - transform_num >= 0){
                queries_result.add(true);
            }else{
                queries_result.add(false);
            }

        }
        return queries_result;
    }
    public List<Boolean> canMakePaliQueries2(String s, int[][] queries){
        List<Boolean> query_result = new ArrayList<Boolean>();
        int []states = new int[s.length()+1];
        int cnt = 0;
        for(int i=0;i<s.length();i++){
            cnt ^= 1 << (s.charAt(i) - 'a');
            states[i+1] = cnt;
        }
        for(int i=0;i<queries.length;i++){
            int ant = states[queries[i][1]+1] ^ states[queries[i][0]];
            int num = 0;
            while (ant > 0){
                if((ant & 1) == 1){
                    num ++;
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
        s= s.trim();
        String[] splited = s.split("\\s+");
        StringBuilder str = new StringBuilder("");
        for(int i=splited.length-1;i>=0;i--){

            str.append(splited[i].toString());
            if(i!=0){
                str.append(" ");
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        String s = "abcda";
        int [][] queries = {{3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}};
        HelloWorld hello_world = new HelloWorld();
        List<Boolean> result = hello_world.canMakePaliQueries2(s, queries);
        System.out.println(result.toString());
    }
}