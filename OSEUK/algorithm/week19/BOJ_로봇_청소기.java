/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 로봇 청소기
 * 문제 번호 : 14503
 * 난이도 : GOLD V
 */

package baekjoon.simulation.gold;

import java.util.Scanner;

public class BOJ14503 {
    private static final int MAX_N = 50;
    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, 1, 0, -1};
    private static final int DSIZE = 4;

    static int N, M;
    static int[][] map = new int[MAX_N][MAX_N];
    static boolean[][] clean = new boolean[MAX_N][MAX_N];
    static class Robot{
        int x, y;   // 좌표
        int d;      // 바라보는 방향

        public Robot(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }

        // 로봇 청소기의 작동
        public void run(){
            while (true){
                // 현재 칸 청소
                this.clean();

                // 청소되지 않은 빈 칸이 없는 경우
                if (checkClean()){
                    int nx = x - DX[d];
                    int ny = y - DY[d];

                    if (map[nx][ny] == 1) break;

                    this.x = nx;
                    this.y = ny;

                    continue;
                }

                // 청소되지 않은 빈 칸이 없는 경우
                d = (d + 3) % 4;    // 반시계 회전

                int nx = x + DX[d];
                int ny = y + DY[d];

                // 앞 쪽이 청소되지 않은 빈 칸인 경우 한 칸 전진
                if(map[nx][ny] == 0 && !clean[nx][ny]){
                    this.x = nx;
                    this.y = ny;
                }
            }
        }
        // 범위 검사
        private boolean inRange(int x, int y){
            return 0 <= x && x < N && 0 <= y && y < M;
        }

        // 주변에 청소되지 않은 빈 칸이 없는 경우 true 반환
        private boolean checkClean(){
            for (int i = 0; i < DSIZE; i++){
                int nx = x + DX[i];
                int ny = y + DY[i];

                if (!inRange(nx, ny) || map[nx][ny] == 1) continue;

                if (!clean[nx][ny]) return false;
            }

            return true;
        }

        // 해당 칸을 청소한다.
        public void clean(){
            if (!clean[x][y]) clean[x][y] = true;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        Robot robot = new Robot(r, c, d);   // 로봇 생성

        // 맵 설정
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        robot.run();    // 로봇 작동

        // 청소된 칸 개수 출력
        int answer = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (clean[i][j]) answer++;
            }
        }

        System.out.println(answer);
    }
}
