
/**
 * CODE TREE 삼성 SW 역량테스트
 * 2022 하반기 오후 1번 문제
 * 문제 이름 : 코드트리 빵
 * 난이도 : GOLD II
 */
package codetree.samsung;

import java.util.*;
import java.io.*;

public class CodetreeBread {
    private static final int MAX_N = 15;
    private static final int MAX_M = 30;
    // 상 좌 우 하0
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int n, m;
    static int[][] board = new int[MAX_N + 1][MAX_N + 1];
    static Human[] humans = new Human[MAX_M + 1];
    static ArrayList<Pair> basecamps = new ArrayList<>();

    static class Pair implements Comparable<Pair>{
        int x;
        int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair p) {
            if (this.x != p.x){
                return this.x - p.x;
            } else{
                return this.y - p.y;
            }
        }

    }
    static class Human{
        int x = 0;
        int y = 0;
        int gx;
        int gy;

        boolean isMoving = false;

        public Human(int gx, int gy){
            this.gx = gx;
            this.gy = gy;
        }
    }


    public static boolean inRange(int x, int y){
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    // 1번 작업
    public static void move(Human h){
        Queue<Pair> q = new LinkedList<>();
        Pair[][] visited = new Pair[MAX_N+1][MAX_N+1];

        Pair hp = new Pair(h.x, h.y);
        q.offer(hp);
        visited[h.x][h.y] = hp;

        // 현재 사람이 편의점으로 가는 최단경로 탐색
        while (!q.isEmpty()){
            Pair ch = q.poll();

            if (ch.x == h.gx && ch.y == h.gy){
                break;
            }

            for (int i = 0; i < 4; i++){
                int nx = ch.x + dx[i];
                int ny = ch.y + dy[i];

                // 범위 밖이거나 방문한 곳이면 패스
                if (!inRange(nx, ny) || visited[nx][ny] != null || board[nx][ny] == -1){
                    continue;
                }

                q.offer(new Pair(nx, ny));
                visited[nx][ny] = ch;
            }
        }

        // 최단 경로를 역 탐색하여 이동
        Pair np = new Pair(h.gx, h.gy);

        while (true){
            if (visited[np.x][np.y] == hp){
                h.x = np.x;
                h.y = np.y;
                break;
            }

            np = visited[np.x][np.y];
        }
    }

    // 2. 편의점에 도착 시 멈춤. 지나가지 못하게 설정
    public static void checkArrived(){
        for (int i = 1; i <= m; i++){
            Human h = humans[i];
            if (!h.isMoving) continue;

            if (h.x == h.gx && h.y == h.gy){
                h.isMoving = false;
                board[h.x][h.y] = -1;
            }
        }
    }

    public static int findBasecamp(Human h, Pair p){

        Queue<Pair> q = new LinkedList<>();
        int[][] visited = new int[MAX_N + 1][MAX_N + 1];

        q.offer(p);
        visited[p.x][p.y] = 1;

        while (!q.isEmpty()){
            Pair cp = q.poll();

            if (cp.x == h.gx && cp.y == h.gy) return visited[cp.x][cp.y];

            for (int i = 0; i < 4; i++){
                int nx = cp.x + dx[i];
                int ny = cp.y + dy[i];

                // 범위 밖이거나 방문한 곳이면 패스
                if (!inRange(nx, ny) || visited[nx][ny] != 0 || board[nx][ny] == -1){
                    continue;
                }

                q.offer(new Pair(nx, ny));
                visited[nx][ny] = visited[cp.x][cp.y] + 1;
            }
        }

        return 0;

    }
    // 3. 가까이 있는 베이스캠프에 도착
    public static void goToBasecamp(int t){
        Human h = humans[t];

        int minDistance = Integer.MAX_VALUE;
        Pair minPos = null;
        // 베이스캠프를 순회.
        for (Pair p : basecamps){
            // if (board[p.x][p.y] == -1) continue;

            int num = findBasecamp(h, p);
            if (num != 0 && num < minDistance){
                minDistance = num;
                minPos = p;
            }
        }

        basecamps.remove(minPos);

        h.x = minPos.x;
        h.y = minPos.y;
        h.isMoving = true;
        board[h.x][h.y] = -1;
    }

    public static boolean isAllArrived(){
        for (int i = 1; i <= m; i++){
            if (humans[i].isMoving) return false;
        }

        return true;
    }
    public static int simulate(){
        int minutes = 1;
        while (true){
            // 1. 격자에 있는 사람 모두 이동
            for (int i = 1; i <= m; i++){
                if (!humans[i].isMoving) continue;
                move(humans[i]);
            }
            // 2. 편의점에 도착한다면 멈추고, 이제부터 다른 사람 접근 금지
            checkArrived();
            // 3. 현재 시간에 해당하는 사람이 베이스캠프 점거. 이후 접근 금지
            if (minutes <= m){
                goToBasecamp(minutes);
            }

            // for (int i = 1; i <= n; i++){
            //     for (int j = 1; j <= n; j++){
            //         System.out.print(board[i][j] + " " );
            //     }
            //     System.out.println();
            // }
            // System.out.println();

            if (isAllArrived()) return minutes;

            minutes++;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i < n + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++){
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) basecamps.add(new Pair(i, j));
                board[i][j] = num;
            }
        }

        Collections.sort(basecamps);

        for (int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int gx = Integer.parseInt(st.nextToken());
            int gy = Integer.parseInt(st.nextToken());

            humans[i] = new Human(gx, gy);
        }

        System.out.println(simulate());
    }
}
