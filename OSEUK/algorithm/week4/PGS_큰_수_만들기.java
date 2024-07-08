package programmers.kit.greedy;

import java.util.Scanner;

public class MakeBigNum {
    public static String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int len = number.length();
        int newLen = len - k;
        int index = 0;

        for (int i = 0; i < newLen; i++){
            char max = '0';
            for (int j = index; j <= k + i; j++){
                if (number.charAt(j) > max){
                    max = number.charAt(j);
                    index = j + 1;
                }
            }
            answer.append(max);
        }
        return answer.toString();
    }
//    dfs 완전탐색 --> 시간초과
//    private static void dfs(String number, int k, int removeCount){
//        if (removeCount == k){
//            if (number.compareTo(answer) > 0){
//                answer = number;
//                return;
//            }
//        }
//
//        String s = "0";
//        for (int i = 0; i < number.length(); i++){
//            String str = number.substring(0, i) + number.substring(i+1);
//            if (str.compareTo(s) > 0) s = str;
//        }
//
//        dfs(s, k, removeCount + 1);
//    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.next();
        int k = sc.nextInt();

        System.out.println(solution(number, k));
    }
}
