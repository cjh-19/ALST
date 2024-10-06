/**
 * CODE TREE 삼성 SW 역량테스트
 * 2024 상반기 오전 1번 문제
 * 문제 이름 : 고대 문명 유적 탐사
 * 난이도 : GOLD IV
 */

/**
 * 3시간 풀고 실패해서 답안 봄
 * 회전문제가 많아서 회전에 익숙해져야 함
 * 기존 맵 상태는 그대로 두고 BFS에 제거에 value값까지 구한 다음
 * 가장 큰 값을 가질 상태를 쓰는 게 어려웠음.
 */
package codetree.samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Artifact {
    static final int N_large = 5;
    static final int N_small = 3;

    static class Board {
        int[][] a = new int[N_large][N_large];

        public Board() {
            for (int i = 0; i < N_large; i++){
                for (int j = 0; j < N_large; j++){
                    a[i][j] = 0;
                }
            }
        }

        // 주어진 y, x가 고대 문명 격자의 범위안에 있는지 확인하는 함수 입니다.
        private boolean inRange(int y, int x) {
            return 0 <= y && y < N_large && 0 <= x && x < N_large;
        }

        public Board rotate(int sy, int sx, int cnt){
            Board result = new Board();

            for (int i = 0; i < N_large; i++){
                for (int j = 0; j < N_large; j++){
                    result.a[i][j] = this.a[i][j];
                }
            }

            for (int k = 0; k < cnt; k++) {
                // sy, sx를 좌측상단으로 하여 시계방향 90도 회전합니다.
                int tmp = result.a[sy + 0][sx + 2];
                result.a[sy + 0][sx + 2] = result.a[sy + 0][sx + 0];
                result.a[sy + 0][sx + 0] = result.a[sy + 2][sx + 0];
                result.a[sy + 2][sx + 0] = result.a[sy + 2][sx + 2];
                result.a[sy + 2][sx + 2] = tmp;
                tmp = result.a[sy + 1][sx + 2];
                result.a[sy + 1][sx + 2] = result.a[sy + 0][sx + 1];
                result.a[sy + 0][sx + 1] = result.a[sy + 1][sx + 0];
                result.a[sy + 1][sx + 0] = result.a[sy + 2][sx + 1];
                result.a[sy + 2][sx + 1] = tmp;
            }

            return result;
        }

        // 유물 획득
        public int calScore() {
            int score = 0;
            boolean[][] visited = new boolean[N_large][N_large];
            int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};

            for (int i = 0; i < N_large; i++){
                for (int j = 0; j < N_large; j++){
                    if (!visited[i][j]){

                        Queue<int[]> q = new LinkedList<>();
                        Queue<int[]> trace = new LinkedList<>();
                        q.offer(new int[]{i, j});
                        trace.offer(new int[]{i, j});
                        visited[i][j] = true;

                        while (!q.isEmpty()){
                            int[] cur = q.poll();
                            for (int k = 0; k < 4; k++){
                                int ny = cur[0] + dy[k], nx = cur[1] + dx[k];
                                if (inRange(ny, nx) && a[ny][nx] == a[cur[0]][cur[1]] && !visited[ny][nx]){
                                    q.offer(new int[]{ny, nx});
                                    trace.offer(new int[]{ny, nx});
                                    visited[ny][nx] = true;
                                }
                            }
                        }

                        if (trace.size() >= 3){
                            score += trace.size();
                            while (!trace.isEmpty()){
                                int[] t = trace.poll();
                                a[t[0]][t[1]] = 0;
                            }
                        }
                    }
                }
            }
            return score;
        }


        public void fill(Queue<Integer> que){
            for (int j = 0; j < N_large; j++){
                for (int i = N_large - 1; i >= 0 ; i--){
                    if (a[i][j] == 0 && !que.isEmpty()){
                        a[i][j] = que.poll();
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int M = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();
        Board board = new Board();

        for (int i = 0; i < N_large; i++) {
            for (int j = 0; j < N_large; j++) {
                board.a[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < M; i++) {
            q.offer(sc.nextInt());
        }

        while (K-- > 0){
            int maxScore = 0;
            Board maxScoreBoard = null;

            // 회전 목표에 맞는 결과를 maxScoreBoard에 저장합니다.
            // (1) 유물 1차 획득 가치를 최대화
            // (2) 회전한 각도가 가장 작은 방법을 선택
            // (3) 회전 중심 좌표의 열이 가장 작은 구간을, 그리고 열이 같다면 행이 가장 작은 구간을 선택\
            for (int cnt = 1; cnt <= 3; cnt++){
                for (int sx = 0; sx <= N_large - N_small; sx++){
                    for (int sy = 0; sy <= N_large - N_small; sy++){
                        Board rotated = board.rotate(sy, sx, cnt);
                        int score = rotated.calScore();
                        if (maxScore < score){
                            maxScore = score;
                            maxScoreBoard = rotated;
                        }
                    }
                }
            }

            if (maxScoreBoard == null){
                break;
            }
            board = maxScoreBoard;

            while (true){
                board.fill(q);
                int newScore = board.calScore();
                if (newScore == 0) break;
                maxScore += newScore;
            }

            System.out.print(maxScore + " ");
        }
    }
}
