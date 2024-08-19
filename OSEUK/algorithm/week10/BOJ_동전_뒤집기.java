/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 동전 뒤집기
 * 문제 번호 : 1285
 * 난이도 : GOLD I
 */

package baekjoon.greedy.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1285 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = N  *  N;
        char[][] coins = new char[N][N];


        for (int i = 0; i < N; i++){
            String line = br.readLine();
            char[] charArray = line.toCharArray();
            for (int j = 0; j < N; j++){
                coins[i][j] = charArray[j];
            }
        }

        // 아무 행도 뒤집지 않은 공집합부터
        // 모든 행을 뒤집는 것까지 탐색
        for (int bit = 0; bit < (1  << N); bit++){
            // 이번 선택에 대해 열을 순회하며 T가 더 많은 열을 뒤집는다.
            int sumT = 0;

            // 이번 열 확인
            for (int j = 0; j < N; j++){
                int colTCount = 0;
                // 이번 열에서 T의 개수 확인
                for (int i = 0; i < N; i++){
                    char tmp = coins[i][j];

                    // 이번 선택(bit)로 뒤집어야 하는 행이면 뒤집음
                    if ((bit & (1 << i)) != 0) {
                        tmp = (tmp == 'T') ? 'H' : 'T';
                    }
                    // 뒤집고 T의 개수를 셈
                    if (tmp == 'T') colTCount++;
                }
                // T가 더 많으면 뒤집어야 하고, 아니면 안뒤집으면 되므로 둘 중 적은 개수
                sumT += Math.min(N - colTCount, colTCount);
            }
            // 정답과 비교
            answer = Math.min(answer, sumT);
        }

        System.out.println(answer);
        br.close();
    }
}
