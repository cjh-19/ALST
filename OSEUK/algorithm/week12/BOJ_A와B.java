/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : A와 B
 * 문제 번호 : 12904
 * 난이도 : GOLD V
 */
package baekjoon.greedy.gold;

import java.util.*;
import java.io.*;
public class BOJ12904 {
    public static void main(String[] args)  throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();


        while (S.length() != T.length()){
            if (T.charAt(T.length()-1) == 'A'){
                T = T.substring(0, T.length() - 1);
            }
            else{
                T = new StringBuilder(T.substring(0, T.length()-1)).reverse().toString();
            }
        }

        if (T.equals(S)) System.out.println(1);
        else System.out.println(0);
    }


}
