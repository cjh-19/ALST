package programmers.kit.dp;

import java.util.Arrays;

public class IntTriangle {
    public static int solution(int[][] triangle) {
        int[][] dp = new int[500][501];
        dp[0][0] = triangle[0][0];

        /* 위에서 아래로 */
        for (int i = 1; i < triangle.length; i++){
            int last = triangle[i].length - 1;  // 해당 층의 마지막 칸

            /* 해당 층의 양쪽 끝은 하나에서만 옴 */
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            dp[i][last] = dp[i-1][last-1] + triangle[i][last];

            /* 위의 칸에서 최댓값으로 저장 */
            for (int j = 1; j < triangle[i].length - 1; j++){
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
        }

        /* dp 마지막 배열의 최댓값 */
        return Arrays.stream(dp[triangle.length-1]).max().getAsInt();
    }
    public static void main(String[] args) {
        int[][] triangle = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };

        System.out.println(solution(triangle));
    }
}
