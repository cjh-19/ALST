/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 탈출
 * 문제 번호 : 3055
 * 난이도 : GOLD IV
 */
package baekjoon.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ3055 {
    static int R, C;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static int solution(char[][] map) {
        Queue<int[]> waterQueue = new LinkedList<>();
        Queue<int[]> beaverQueue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        int[] dest = new int[2];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'D') {
                    dest = new int[]{i, j};
                }
                if (map[i][j] == 'S') {
                    beaverQueue.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
                if (map[i][j] == '*') {
                    waterQueue.add(new int[]{i, j});
                }
            }
        }
        return bfs(map, visited, dest, beaverQueue, waterQueue);
    }

    public static int bfs(char[][] map, boolean[][] visited, int[] dest, Queue<int[]> beaverQueue, Queue<int[]> waterQueue) {
        while (!beaverQueue.isEmpty()) {
            // 물 확장 처리
            int waterSize = waterQueue.size();  // Queue사이즈 이용해서 한번에 돌리는게 포인트; 이거 지리네
            for (int i = 0; i < waterSize; i++) {
                int[] water = waterQueue.poll();
                int wx = water[0];
                int wy = water[1];

                for (int j = 0; j < 4; j++) {
                    int nwx = wx + dx[j];
                    int nwy = wy + dy[j];

                    if (nwx >= 0 && nwx < R && nwy >= 0 && nwy < C && map[nwx][nwy] == '.') {
                        map[nwx][nwy] = '*';
                        waterQueue.add(new int[]{nwx, nwy});
                    }
                }
            }

            // 비버 이동 처리
            int beaverSize = beaverQueue.size();
            for (int i = 0; i < beaverSize; i++) {
                int[] beaver = beaverQueue.poll();
                int bx = beaver[0];
                int by = beaver[1];
                int btime = beaver[2];

                if (bx == dest[0] && by == dest[1]) {
                    return btime;
                }

                for (int j = 0; j < 4; j++) {
                    int nbx = bx + dx[j];
                    int nby = by + dy[j];

                    if (nbx >= 0 && nbx < R && nby >= 0 && nby < C && !visited[nbx][nby] && (map[nbx][nby] == '.' || map[nbx][nby] == 'D')) {
                        visited[nbx][nby] = true;
                        beaverQueue.add(new int[]{nbx, nby, btime + 1});
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = br.readLine().split(" ");
        R = Integer.parseInt(rc[0]);
        C = Integer.parseInt(rc[1]);

        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int result = solution(map);

        if (result == 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(result);
        }
    }
}

