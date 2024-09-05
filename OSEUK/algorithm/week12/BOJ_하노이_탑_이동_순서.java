/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 하노이 탑 이동 순서
 * 문제 번호 : 11729
 * 난이도 : GOLD V
 */

package baekjoon.dnc.gold;

import java.io.*;

public class BOJ11729 {
    public static void hanoi(int n, int start, int end, int via){ // 시작기둥, 끝기둥, 중간기둥
        if (n == 1) {
            System.out.println(start + " " + end);
            return;
        }

        // n-1개의 원판을 start에서 via로 옮김
        hanoi(n - 1, start, via, end);
        // 가장 큰 원판을 end로 옮김
        System.out.println(start + " " + end);
        // n-1개의 원판을 via에서 end로 옮김
        hanoi(n - 1, via, end, start);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        System.out.println((int) (Math.pow(2, N) - 1));

        hanoi(N, 1, 3, 2);
    }
}
