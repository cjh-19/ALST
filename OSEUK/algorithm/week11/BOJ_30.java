/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 30
 * 문제 번호 : 10610
 * 난이도 : SILVER IV
 */

package baekjoon.greedy.silver;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BOJ10610 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String num = sc.next();

        char[] digits = num.toCharArray();

        boolean hasZero = false;
        int sum = 0 ;

        for (char digit : digits){
            int d = digit - '0';
            if (d == 0) hasZero = true;
            sum += d;
        }

        if (!hasZero || sum % 3 != 0){
            System.out.println(-1);
        } else{
            Arrays.sort(digits);

            for (int i = digits.length-1; i >= 0; i--){
                System.out.print(digits[i]);
            }
        }


    }
}
