/**
 * CODE TREE 삼성 SW 역량테스트
 * 2017 상반기 오전 2번 문제
 * 문제 이름 : 외주 수익 최대화하기
 * 난이도 : SILVER III
 */

package codetree.samsung;

import java.util.*;
import java.io.*;
public class MakeMaxProfit {
    public static void main(String[] args) throws IOException {
        // Input start

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] s = new int[n + 1];
        int[] e = new int[n + 1];
        int[] p = new int[n + 1];


        for (int i = 1; i < n + 1; i++){
            String[] arr = br.readLine().split(" ");

            e[i] = Integer.parseInt(arr[0]);
            p[i] = Integer.parseInt(arr[1]);

            s[i] = i;   // 시작 시간
            e[i] = i + e[i] - 1;  // 끝 시간
        }
        // Input End

        // solution start
        int[] dp = new int[n + 1];

        for (int i = 1; i < n + 1; i++){

            if (e[i] > n) continue;

            for (int j = 0; j < i; j++){
                // 현재 시작 시간보다 빨리 끝나는 것만
                // 빨리 끝나는 것의 최댓값
                if (e[j] < s[i]) dp[i] = Math.max(dp[i], dp[j] + p[i]);
            }
        }
        // solution end

        // result print
        int answer = 0;
        for (int i = 0; i < n + 1; i++){
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
