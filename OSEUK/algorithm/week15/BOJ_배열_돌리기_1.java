/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 배열 돌리기 1
 * 문제 번호 : 16926
 * 난이도 : GOLD V
 */

package baekjoon.simulation.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N과 M 둘 중 하나는 짝수임
public class BOJ16926 {
    static int N, M, R;
    static int[][] arr;

    public static void rotate(){
        int cycle = Math.min(N, M) / 2;

        for (int i = 0; i < cycle; i++){
            int rowLen = N - 2 * i - 1;
            int colLen = M - 2 * i - 1;
            int total = 2 * (rowLen + colLen);

            // R이 커질 수 있으므로, total로 나눈 나머지만큼만 돌리면 됨.
            int actualRotate = R % total;

            for (int j = 0; j < actualRotate; j++){
                int temp = arr[i][i];

                // 위쪽
                for (int k = i; k < M - 1 - i; k++){
                    arr[i][k] = arr[i][k+1];
                }

                // 오른쪽
                for (int k = i ; k < N - 1 - i; k++){
                    arr[k][M-1-i] = arr[k+1][M-1-i];
                }

                // 아래쪽
                for (int k = M-1-i; k > i; k--){
                    arr[N-1-i][k] = arr[N-1-i][k-1];
                }

                // 왼쪽
                for (int k = N-1-i; k > i; k--){
                    arr[k][i] = arr[k-1][i];
                }

                arr[i + 1][i] = temp;
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
        for (int i = 0;  i < N; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotate();

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
