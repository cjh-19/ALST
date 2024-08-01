/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 구슬 탈출 2
 * 문제 번호 : 13460
 * 난이도 : GOLD I
 */
package baekjoon.bruthforce.gold;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ13460 {
    public static int solution(int N, int M, char[][] board){
        boolean[][][][] visited = new boolean[N][M][N][M];
        Marble blue = null, red = null;

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (board[i][j] == 'B'){
                    blue = new Marble(0, 0, i , j, 0);
                    continue;
                }
                if (board[i][j] == 'R'){
                    red = new Marble(i, j, 0, 0, 0);
                }
            }
        }

        return bfs(board,red, blue, visited);
    }

    public static int bfs(char[][] board,Marble red, Marble blue, boolean[][][][] visited){
        // 상 우 하 좌
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Marble> q =  new LinkedList<>();
        q.add(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;

        while (!q.isEmpty()){
            Marble marble = q.poll();

            int curRx = marble.rx;
            int curRy = marble.ry;
            int curBx = marble.bx;
            int curBy = marble.by;
            int curCnt = marble.cnt;

            if (curCnt > 10){
                return -1;
            }

            for (int i = 0; i < 4; i++){
                int nrx = curRx;
                int nry = curRy;
                int nbx = curBx;
                int nby = curBy;


                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬 이동
                while (board[nrx + dx[i]][nry + dy[i]] != '#'){
                    nrx += dx[i];
                    nry += dy[i];

                    if (board[nrx][nry] == 'O'){
                        isRedHole = true;
                        break;
                    }
                }

                // 파란 구슬 이동
                while (board[nbx + dx[i]][nby + dy[i]] != '#'){
                    nbx += dx[i];
                    nby += dy[i];

                    if (board[nbx][nby] == 'O'){
                        isBlueHole = true;
                        break;
                    }
                }

                // 실패지만 다른 경우의 수 봐야함
                if (isBlueHole) continue;

                if (isRedHole && !isBlueHole) return curCnt;

                if (nrx == nbx && nry == nby){
                    int rDist = Math.abs(nrx - curRx) + Math.abs(nry - curRy);
                    int bDist = Math.abs(nbx - curBx) + Math.abs(nby - curBy);

                    if (rDist > bDist){
                        nrx -= dx[i];
                        nry -= dy[i];
                    }
                    else{
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]){
                    visited[nrx][nry][nbx][nby] = true;
                    q.add(new Marble(nrx, nry, nbx, nby, curCnt + 1));
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++){
            board[i] = sc.nextLine().toCharArray();
        }

        System.out.println(solution(N, M, board));
    }
}

class Marble {
    int rx;
    int ry;
    int bx;
    int by;
    int cnt;

    Marble(int rx, int ry, int bx, int by, int cnt) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.cnt = cnt;
    }
}