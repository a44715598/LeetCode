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
        String s = "  a good   example";
        HelloWorld hello_world = new HelloWorld();
        String u_s = hello_world.reverseWords(s);
        System.out.println(u_s);
    }
}