package programmers.kit.dp;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExpressN {
    public static int solution(int N, int number) {
        /* N 개수마다 dp에 저장됨 */
        Set<Integer>[] dp = new Set[9];

        for (int i = 1; i <= 8; i++){
            dp[i] = new HashSet<>();
        }

        /* NNN.. 각 개수마다 저장 */
        int base = N;
        for (int i = 1; i <= 8; i++){
            dp[i].add(base);
            base = base * 10 + N;
        }

        /* 모든 경우의 수 계산 */
        for (int i = 1; i <= 8; i++){
            for (int j = 1; j < i; j++){
                for (int a : dp[j]){
                    for (int b : dp[i - j]){
                        dp[i].add(a + b);
                        if (a > b) dp[i].add(a - b);
                        dp[i].add(a * b);
                        if (b != 0) dp[i].add(a / b);
                    }
                }
            }

            if (dp[i].contains(number)) return i;
        }

        return -1;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int number = sc.nextInt();

        System.out.println(solution(N, number));
    }
}
