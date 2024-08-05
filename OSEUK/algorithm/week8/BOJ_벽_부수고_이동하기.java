/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 벽 부수고 이동하기
 * 문제 번호 : 2206
 * 난이도 : GOLD III
 */
package baekjoon.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2206 {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static int bfs(int[][] map){
        boolean[][][] visited = new boolean[N][M][2];
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0, 0, 1});
        visited[0][0][0] = true;

        while (!q.isEmpty()){
            int[] current = q.poll();
            // x, y, 벽 부숨 여부, 거리
            int x = current[0];
            int y = current[1];
            int isBroken = current[2];
            int dist = current[3];

            if (x == N - 1 && y == M - 1) return dist;

            for (int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)  continue;

                if (map[nx][ny] == 1 && isBroken == 0 && !visited[nx][ny][1]) {
                    q.add(new int[]{nx, ny, 1, dist + 1});
                    visited[nx][ny][1] = true;
                }

                if (map[nx][ny] == 0 && !visited[nx][ny][isBroken]) {
                    q.add(new int[]{nx, ny, isBroken, dist + 1});
                    visited[nx][ny][isBroken] = true;
                }

            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++){
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++){
                map[i][j] = chars[j] - '0';
            }
        }

        System.out.println(bfs(map));
    }
}
