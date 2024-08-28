/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 대회 or 인턴
 * 문제 번호 : 2875
 * 난이도 : BRONZE III
 */
package baekjoon.greedy.bronze;

import java.util.Scanner;

public class BOJ2875 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N, M, K;
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        int maxTeams = Math.min(N / 2, M);

        while (N + M - (3 * maxTeams) < K){
            maxTeams--;
        }

        System.out.println(maxTeams);
    }

}
