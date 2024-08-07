/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 움직이는 미로 탈출
 * 문제 번호 : 16954
 * 난이도 : GOLD III
 */

package baekjoon.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16954 {
    // 2중 for문으로 상하좌우 대각선 + 제자리
    static int[] dx = {1, 0, -1};
    static int[] dy = {1, 0, -1};
    private static int bfs(char[][] map){
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[8][8][9];

        q.add(new int[]{7, 0, 0});
        visited[7][0][0] = true;

        int precount = 0;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int moveCount = curr[2];

            // 큐 한번마다가 아니라 1초에 모두 이동한 뒤 벽이 이동해야함
            if (precount != moveCount) {
                move(map, moveCount);
                precount = moveCount;
            }

            if (map[x][y] == '#') continue; // 현재 위치가 벽이 되버리면 패스

            if (x == 0 && y == 7) return 1; //  목적지

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    int nx = x + dx[i];
                    int ny = y + dy[j];

                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue;

                    // 이동할 위치가 빈칸, 벽 이동할 떄마다 visited도 새로 초기화, 8번 이후에는 벽이 무조건 없음
                    if (map[nx][ny] == '.' && !visited[nx][ny][Math.min(moveCount +1, 8)]){
                        q.add(new int[]{nx, ny, Math.min(moveCount + 1, 8)});
                        visited[nx][ny][Math.min(moveCount + 1, 8)] = true;
                    }

                }
            }

        }

        return 0;
    }

    // 벽 이동
    private static void move(char[][] map, int moveCount){
        if (moveCount >= 8) return;

        for(int i = 0; i < 8; i++){
            map[7][i] = '.';
        }
        for (int i = 6; i >= 0; i--){
            for (int j = 0; j < 8; j++){
                if (map[i][j] == '#') {
                    map[i+1][j] = '#';
                    map[i][j] = '.';
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[8][8];
        for (int i = 0; i < 8; i++){
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs(map));
    }
}
