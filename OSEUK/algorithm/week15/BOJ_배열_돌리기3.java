package baekjoon.simulation.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16935 {
    static int N, M, R;
    static int[][] arr;

    private static void turn_array(int method) {
        switch (method){
            case 1:
                flip_vertical();
                break;
            case 2:
                flip_horizonal();
                break;
            case 3:
                rotate_90_right();
                break;
            case 4:
                rotate_90_left();
                break;
            case 5:
                part_move();
                break;
            case 6:
                part_move_reverse();
                break;
        }
    }

    // 상하 반전
    private static void flip_vertical() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                temp[i][j] = arr[N-i-1][j];
            }
        }

        arr = temp;
    }

    // 좌우 반전
    private static void flip_horizonal() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                temp[i][j] = arr[i][M-j-1];
            }
        }

        arr = temp;
    }

    // 시계방향 90도 회전
    private static void rotate_90_right() {
        int[][] temp = new int[M][N];

        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                temp[i][j] = arr[N-j-1][i];
            }
        }

        // N , M 이 바뀌는 점 명심!
        arr = temp;
        int num = N;
        N = M;
        M = num;
    }

    // 반시계방향 90도 회전
    private static void rotate_90_left() {
        int[][] temp = new int[M][N];

        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                temp[i][j] = arr[j][M-i-1];
            }
        }

        arr = temp;
        int num = N;
        N = M;
        M = num;
    }

    // 부분 변환
    private static void part_move() {
        int[][] temp = new int[N][M];

        int halfRow = N/2;
        int halfCol = M/2;

        // 1번 -> 2번 (왼쪽 위 -> 오른쪽 위)
        for(int i = 0; i < halfRow; i++) {
            for(int j = 0; j < halfCol; j++) {
                temp[i][j + halfCol] = arr[i][j];
            }
        }

        // 2번 -> 3번 (오른쪽 위 -> 오른쪽 아래)
        for(int i = 0; i < halfRow; i++) {
            for(int j = halfCol; j < M; j++) {
                temp[i + halfRow][j] = arr[i][j];
            }
        }

        // 3번 -> 4번 (오른쪽 아래 -> 왼쪽 아래)
        for(int i = halfRow; i < N; i++) {
            for(int j = halfCol; j < M; j++) {
                temp[i][j - halfCol] = arr[i][j];
            }
        }

        // 4번 -> 1번 (왼쪽 아래 -> 왼쪽 위)
        for(int i = halfRow; i < N; i++) {
            for(int j = 0; j < halfCol; j++) {
                temp[i - halfRow][j] = arr[i][j];
            }
        }

        // 임시 배열을 원본으로 복사
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    // 부분 변환 반대로
    private static void part_move_reverse() {
        int[][] temp = new int[N][M];  // 전체 크기의 임시 배열 사용

        int halfRow = N/2;
        int halfCol = M/2;

        // 1번 그룹 -> 4번 위치로
        for(int i = 0; i < halfRow; i++) {
            for(int j = 0; j < halfCol; j++) {
                temp[i + halfRow][j] = arr[i][j];
            }
        }

        // 4번 그룹 -> 3번 위치로
        for(int i = halfRow; i < N; i++) {
            for(int j = 0; j < halfCol; j++) {
                temp[i][j + halfCol] = arr[i][j];
            }
        }

        // 3번 그룹 -> 2번 위치로
        for(int i = halfRow; i < N; i++) {
            for(int j = halfCol; j < M; j++) {
                temp[i - halfRow][j] = arr[i][j];
            }
        }

        // 2번 그룹 -> 1번 위치로
        for(int i = 0; i < halfRow; i++) {
            for(int j = halfCol; j < M; j++) {
                temp[i][j - halfCol] = arr[i][j];
            }
        }

        // 임시 배열을 원본으로 복사
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++){
            int method = Integer.parseInt(st.nextToken());
            turn_array(method);
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


}
