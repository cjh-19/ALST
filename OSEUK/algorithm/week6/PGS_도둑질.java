package programmers.kit.dp;

import java.util.Scanner;

public class Dodukjil {
    public static int solution(int[] money) {
        int[] dp1 = new int[money.length + 1];
        int[] dp2 = new int[money.length + 1];

        dp1[0] = 0; dp1[1] = money[0];  // 첫 번째 집을 턴 경우
        dp2[0] = 0; dp2[1] = money[1];  // 첫 번째 집을 안 턴 경우

        for (int i = 2; i < money.length; i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i - 1]);   // 턴 경우는 money의 마지막 고려하지 않게 됨
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }

        return Math.max(dp1[money.length - 1], dp2[money.length - 1]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] money = new int[n];
        for (int i = 0; i < n; i++){
            money[i] = sc.nextInt();
        }

        System.out.println(solution(money));

    }
}
