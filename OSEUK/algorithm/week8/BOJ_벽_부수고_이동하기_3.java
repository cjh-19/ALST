/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 벽 부수고 이동하기 3
 * 문제 번호 : 16933
 * 난이도 : GOLD I
 */

package baekjoon.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16933 {
    static int N, M, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static int bfs(int[][] map){
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][K + 1];

        q.add(new int[]{0, 0, 1, 0, 0});
        visited[0][0][0] = true;

        while (!q.isEmpty()){
            int[] current = q.poll();
            int x = current[0]; // x좌표
            int y = current[1]; // y좌표
            int dist = current[2];  // 이동 거리
            int broken = current[3];    // 벽 부순 횟수
            int isNight = current[4];   // 0이면 낮 1이면 밤

            int next = isNight == 0 ? 1 : 0;    // 다음날 밤낮 여부

            if (x == N-1 && y == M-1) return dist;  // 목적지 도달

            // 밤일 경우 하루 머물도록 --> 밤일때 0인 곳은 다 이동해서 안가고 1인 곳만 부수게 됨
            if (isNight == 1){
                q.add(new int[]{x, y, dist + 1, broken, next});
            }

            for (int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                // 이동할 곳 벽 여부와 낮인지 체크
                if (map[nx][ny] == 1 && broken < K && !visited[nx][ny][broken + 1]  && isNight == 0){
                    q.add(new int[]{nx, ny, dist + 1, broken + 1, next});
                    visited[nx][ny][broken+1] = true;
                }

                if (map[nx][ny] == 0 && !visited[nx][ny][broken]){
                    q.add(new int[]{nx, ny, dist + 1, broken, next});
                    visited[nx][ny][broken] = true;
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
