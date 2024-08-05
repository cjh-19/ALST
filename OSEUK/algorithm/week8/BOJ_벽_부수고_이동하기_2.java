/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 벽 부수고 이동하기 2
 * 문제 번호 : 14442
 * 난이도 : GOLD III
 */
package baekjoon.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14442 {
    static int N, M, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static int bfs(int[][] map){
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][K+1];

        q.add(new int[]{0, 0, 1, 0});
        visited[0][0][0] = true;

        while (!q.isEmpty()){
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];
            int broken = current[3];

            if (x == N-1 && y == M-1) return dist;

            for (int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0|| ny >= M) continue;

                if (map[nx][ny] == 0 && !visited[nx][ny][broken]){
                    q.add(new int[]{nx, ny, dist + 1, broken});
                    visited[nx][ny][broken] = true;
                }

                if (map[nx][ny] == 1 && broken < K && !visited[nx][ny][broken+1]){
                    q.add(new int[]{nx, ny, dist + 1, broken + 1});
                    visited[nx][ny][broken + 1] = true;
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
        K = Integer.parseInt(nm[2]);
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
