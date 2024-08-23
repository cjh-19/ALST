/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 잃어버린 괄호
 * 문제 번호 : 1541
 * 난이도 : SILVER II
 */

package baekjoon.greedy.silver;

import java.util.Scanner;

public class BOJ1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();

        // -로 먼저 분리하고
        String[] minusSplit = expression.split("-");

        int result = 0;

        for (int i = 0; i < minusSplit.length; i++){
            String part = minusSplit[i];

            // +로 나중에 분리해서
            String[] numbers = part.split("\\+");
            int curr = 0;
            for (String num : numbers){
                curr += Integer.parseInt(num);
            }

            if (i == 0){
                result += curr;
            } else{
                result -= curr;
            }
        }

        System.out.println(result);
    }
}
