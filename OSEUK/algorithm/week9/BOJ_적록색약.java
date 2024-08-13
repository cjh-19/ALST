/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 적록색약
 * 문제 번호 : 10026
 * 난이도 : GOLD V
 */

package baekjoon.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ10026 {
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static int[] solution(int N, char[][] map){
        int[] answer = new int[]{0, 0};

        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (!visited[i][j]) {
                    bfs(i, j, map, visited);
                    answer[0]++;
                }
            }
        }

        // 방문 배열 초기화
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (!visited[i][j]) {
                    redBlueBfs(i, j, map, visited);
                    answer[1]++;
                }
            }
        }

        return answer;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    // 적록색약인 경우
    public static void redBlueBfs(int startx, int starty, char[][] map, boolean[][] visited){
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{startx, starty});
        visited[startx][starty] = true;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny)) continue;

                if (map[x][y] == 'R' || map[x][y] == 'G'){
                    if ((map[nx][ny] == 'R' || map[nx][ny] == 'G') && !visited[nx][ny] ){
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
                else{
                    if (map[nx][ny] == 'B' && !visited[nx][ny]){
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    // 일반인경우
    public static void bfs(int startx, int starty, char[][] map, boolean[][] visited){
        Queue<int[] > q = new LinkedList<>();

        q.add(new int[]{startx, starty});
        visited[startx][starty] = true;

        while (!q.isEmpty()){
            int[] curr=  q.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx , ny )) continue;

                if (map[nx][ny] == map[x][y] && !visited[nx][ny]){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++){
            map[i] = br.readLine().toCharArray();
        }


        int[] result = solution(N, map);
        System.out.println(result[0] +  " " + result[1]);

    }
}
