/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 행렬
 * 문제 번호 : 1080
 * 난이도 : SILVER I
 */

package baekjoon.greedy.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ1080 {
    static int N, M;
    public static int solution(int[][] A, int[][] B){
        int Answer = 0;

        if (N < 3 || M < 3){
            if (isSame(A, B)) return 0;
            return -1;
        }

        for (int i = 0; i <= N-3; i++){
            for (int j = 0; j <= M-3; j++){
                if (A[i][j] != B[i][j]) {
                    reserve(A, i, j);
                    Answer++;
                }

            }
        }

        if (isSame(A, B)) return Answer;
        return -1;
    }
    public static void reserve(int A[][], int x, int y){
        for (int i = x; i < x + 3; i++){
            for (int j = y; j < y + 3; j++){
                A[i][j] = 1 - A[i][j];
            }
        }
    }
    public static boolean isSame(int[][] A, int[][] B){
        for (int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if (A[i][j] != B[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        char[][] chars = new char[N][M];
        int[][] A = new int[N][M];
        int[][] B = new int[N][M];
        for (int i = 0; i < N; i++){
            chars[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                A[i][j] = chars[i][j] - '0';
            }
        }

        for (int i = 0; i < N; i++){
            chars[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                B[i][j] = chars[i][j] - '0';
            }
        }

        System.out.println(solution(A, B));
    }
}
