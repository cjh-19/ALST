/**
 * CODE TREE 삼성 SW 역량테스트
 * 2023 하반기 오전 1번 문제
 * 문제 이름 : 왕실의 기사 대결
 * 난이도 : GOLD III
 */

package codetree.samsung;

import java.util.*;
import java.io.*;

public class KnightFight {
    private static final int MAX_L = 40;
    private static final int MAX_KNIGHT = 30;

    private static final int BOMB = 1;
    private static final int WALL = 2;

    // 위, 오른쪽, 아래, 왼쪽
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    static int L, N, Q;
    static int[][] board = new int[MAX_L + 1][MAX_L + 1];   // 전체 보드 선언
    static int[][] knightBoard = new int[MAX_L + 1][MAX_L + 1]; // 어떤 기사가 어디에있는지 보드
    static boolean[][] visited = new boolean[MAX_L + 1][MAX_L + 1]; // 방문 배열
    static Knight[] knights = new Knight[MAX_KNIGHT + 1];   // 기사들

    static int totalDamaged = 0;   //결과값

    static class Pair{
        int x;
        int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Knight{
        int id; // id

        int r;  // 행
        int c;  // 열
        int h;  // 높이 (행)
        int w;  // 너비 (열)
        int k;  // 목숨

        boolean isAlive = true; // 살아있는지?
        int damaged = 0;    // 받은 데미지

        public Knight(int id, int r, int c, int h, int w, int k){
            this.id = id;
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;

            this.setKnightBoard(this.id);   // 보드에 자기 위치 선언
        }

        public void setKnightBoard(int num){
            for (int i = r; i < r + h; i++){
                for (int j = c; j < c + w; j++){
                    knightBoard[i][j] = num;
                }
            }
        }

        // 이동
        public void move(int dir){
            this.r = this.r + dx[dir];
            this.c = this.c + dy[dir];
        }

        // 데미지 계산
        public void calcDamage(){
            for (int i = r; i < r + h; i++){
                for (int j = c; j < c + w; j++){
                    if (board[i][j] == BOMB){
                        this.k--;
                        this.damaged++;
                        if (k == 0){
                            this.isAlive = false;
                            this.setKnightBoard(0);
                            return;
                        }
                    }
                }
            }
        }
    }

    // 범위
    public static boolean inRange(int x, int y){
        return x >= 1 && x <= L && y >= 1 && y <= L;
    }

    // BFS
    // 이동하고자 하는 좌표에서 시작하여 해당 방향으로 움직일 기사들을 반환
    public static Set<Knight> canGo(Knight knight, int dir){
        Queue<Pair> q = new LinkedList<>();
        Set<Knight> moveKnights = new HashSet<>();
        visited = new boolean[MAX_L + 1][MAX_L + 1];

        boolean canMove = true;
        int x = knight.r;
        int y = knight.c;
        q.offer(new Pair(x, y));
        moveKnights.add(knight);
        visited[x][y] = true;

        while (!q.isEmpty()){
            Pair p = q.poll();

            //System.out.println("visited pos " + p.x + " " + p.y);
            if (!canMove) break;

            for (int i = 0; i < 4; i++){
                // 진행하고자하는 반대방향은 탐색 x
                if (i == (dir + 2) % 4){
                    continue;
                }

                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // dir 방향이면, 기사 달라도 이동.
                if (i == dir){

                    // 근데 벽이나 범위 넘어가면 바로 실패
                    if (!inRange(nx, ny) || board[nx][ny] == WALL){
                        canMove = false;
                        continue;
                    }

                    // 이미 방문했던 곳 패스,
                    // 기사가 없다면 여기선 더이상 밑으로 탐색 X
                    if (visited[nx][ny] || knightBoard[nx][ny] == 0){
                        continue;
                    }
                    // 다음 칸이 다른 기사이면, 추가
                    if (knightBoard[p.x][p.y] != knightBoard[nx][ny]){
                        moveKnights.add(knights[knightBoard[nx][ny]]);
                    }

                    q.offer(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }

                // 양 옆 방향으로는 기사 자신의 위치로만 이동..
                // dir 방향으로만 가장 깊게.
                else {

                    if (inRange(nx, ny) && !visited[nx][ny] && knightBoard[nx][ny] == knightBoard[p.x][p.y]){
                        q.offer(new Pair(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        if (!canMove) return null;
        else return moveKnights;
    }
    public static void simulate(int id, int dir){
        // 이동할 수 있다면
        Set<Knight> moveKnights = canGo(knights[id], dir);

        //System.out.println();
        // 움직일 수 없다는 뜻.
        if (moveKnights == null){
            return;
        }

        // 움직일 수 있다면 일단 전체 초기화
        for (int i = 1; i <= L; i++){
            for (int j = 1; j <= L; j++){
                knightBoard[i][j] = 0;
            }
        }

        // Set에 들어있는 기사의 위치를 갱신한 뒤 데미지 계산
        // 각 기사별로 위치 다시 세팅
        // 죽었다면 하지않음.
        // 어차피 겹치지 않음.
        for (int i = 1; i <= N; i++){

            Knight knight = knights[i];

            if (!knight.isAlive) continue;

            if (moveKnights.contains(knight)){
                knight.move(dir);
                if (knight.id != id)
                    knight.calcDamage();
            }

            knight.setKnightBoard(knight.id);

        }


    }
    public static void main(String[] args) throws IOException{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());   // 체스판의 크기
        N = Integer.parseInt(st.nextToken());   // 기사의 수
        Q = Integer.parseInt(st.nextToken());   // 명령의 수

        for (int i = 1; i <= L; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= L; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r, c, h, w, k;
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken());   // 행
            c = Integer.parseInt(st.nextToken());   // 열
            h = Integer.parseInt(st.nextToken());   // 길이
            w = Integer.parseInt(st.nextToken());   // 너비
            k = Integer.parseInt(st.nextToken());   // 목숨

            knights[i] = new Knight(i, r, c, h, w, k);
        }

        int id, dir;
        while (Q-- > 0){
            st = new StringTokenizer(br.readLine());

            id = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());

            if (!knights[id].isAlive) continue;

            simulate(id, dir);
        }

        for (int i = 1; i <= N; i++){
            if (knights[i].isAlive){
                //System.out.println(knights[i].id + " 의 데미지는 " + knights[i].damaged);
                totalDamaged += knights[i].damaged;
            }
        }

        System.out.println(totalDamaged);
    }
}
