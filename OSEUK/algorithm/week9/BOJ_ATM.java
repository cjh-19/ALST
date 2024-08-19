/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : ATM
 * 문제 번호 : 11399
 * 난이도 : SILVER IV
 */

package baekjoon.greedy.silver;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] P = new int[N];
        for (int i = 0; i < N; i++){
            P[i] = sc.nextInt();
        }

        Arrays.sort(P);
        int answer = 0;

        for (int i = 0; i < N; i++){
            for (int j = 0; j <= i; j++){
                answer += P[j];
            }
        }

        System.out.println(answer);
    }
}
