package programmers.kit.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
    int x;
    int y;
    int count;

    public Pos(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

public class GameMap {
    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {1, 0, -1, 0};
    public static int solution(int[][] maps) {
        int m = maps.length;
        int n = maps[0].length;
        boolean[][] visited = new boolean[m][n];

        return bfs(maps, visited);
    }

    private static int bfs(int[][] maps, boolean[][] visited){
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(0, 0, 1));
        visited[0][0] = true;

        int m = maps.length;
        int n = maps[0].length;

        while (!queue.isEmpty()){
            Pos p = queue.poll();

            /* 상대팀 진영에 도착할 경우 */
            if (p.x == m-1 && p.y == n-1){
                return p.count;
            }

            for (int i = 0; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                /* 범위 벗어날 경우, 0으로 이동할 경우 무시 */
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (maps[nx][ny] == 0) continue;

                if (!visited[nx][ny]){
                    queue.add(new Pos(nx, ny, p.count + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        /* 도착 못할 경우 */
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] maps = new int[m][n];
        for (int i = 0; i < m; i++){
            String[] strs = br.readLine().split(" ");
            for (int j = 0; j < n; j++){
                maps[i][j] = Integer.parseInt(strs[j]);
            }
        }

        System.out.println(solution(maps));

    }
}
