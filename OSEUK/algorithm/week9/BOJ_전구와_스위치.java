/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 전구와 스위치
 * 문제 번호 : 2138
 * 난이도 : GOLD IV
 */

package baekjoon.greedy.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ2138 {
    static int N;
    public static int solution(int[] start, int[] end){
        int[] copy = Arrays.copyOf(start, N);
        int Answer = 0;
        for (int i = 1 ; i < N; i++){
            if (start[i-1] != end[i-1]) {
                push(start, i);
                Answer++;
            }
        }

        if (check(start, end)) return Answer;
        Answer = 1;
        push(copy, 0);

        for (int i = 1 ; i < N; i++){
            if (copy[i-1] != end[i-1]) {
                push(copy, i);
                Answer++;
            }
        }
        if (check(copy, end)) return Answer;

        return -1;
    }
    public static boolean check(int[] arr, int[] end){
        for (int i = 0; i < N; i++){
            if (arr[i] != end[i]) return false;
        }

        return true;
    }

    public static void push(int[] arr, int idx){
        for (int i = idx - 1; i <= idx + 1; i++){
            if (i < 0 || i >= N) continue;
            arr[i] = 1 - arr[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[] origin = br.readLine().toCharArray();

        char[] goal = br.readLine().toCharArray();

        int[] A = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++){
            A[i] = origin[i] - '0';
            B[i] = goal[i] - '0';
        }

        System.out.println(solution(A, B));

    }
}
