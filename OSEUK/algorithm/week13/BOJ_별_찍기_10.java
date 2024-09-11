/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 별찍기 - 10
 * 문제 번호 : 2447
 * 난이도 : GOLD V.
 */

package baekjoon.dnc.gold;

import java.util.Scanner;


public class BOJ2447 {

    public static void dnc(int n, int x, int y, char[][] map){
        if (n == 1){
            map[x][y] = '*';
            return;
        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (i == 1 && j == 1) continue;

                dnc(n / 3, x + i * (n / 3), y + j * (n / 3), map);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        char[][] map = new char[N][N];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                map[i][j] = ' ';
            }
        }

        dnc(N, 0, 0, map);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map[i]).append("\n");
        }
        System.out.println(sb.toString());
    }




}
