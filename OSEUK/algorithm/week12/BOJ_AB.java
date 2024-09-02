/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : AB
 * 문제 번호 : 12970
 * 난이도 : GOLD IV
 */

package baekjoon.greedy.gold;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ12970 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int a = 0, b = 0;

        for (int i = 0; i <= K; i++){
            a = i;
            b = N - i;

            if (a * b >= K){
                break;
            }

            if (i == K) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        char[] answer = new char[N];
        Arrays.fill(answer, 'B');

        if (K == 0  ){
            for (char c : answer){
                System.out.print(c);
            }
            System.exit(0);
        }
        for (int i = 0; i < a - 1; i++){
            answer[i] = 'A';
        }

        int currentTwins = (a - 1) * b;
        int haveMoveA = K - currentTwins;
        answer[N - 1 - haveMoveA] = 'A';

        for (char c : answer){
            System.out.print(c);
        }


    }
}
