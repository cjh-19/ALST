/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 벽 부수고 이동하기 4
 * 문제 번호 : 16946
 * 난이도 : GOLD II
 */
package baekjoon.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BOJ16946 {
    static int N, M;
    static int groupIdx = 0;
    static int[][] groups;
    static int[] groupSize;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    private static int[][] solution(int[][] map){
        makeGroup(map);
        
        int[][] answer = new int[N][M];
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                answer[i][j] = getAnswer(map, i, j);
            }
        }
        
        return answer;
    }

    private static int getAnswer(int[][] map, int i, int j) {
        Set<Integer> set = new HashSet<>();
        int count = 0;

        if (map[i][j] == 0){
            return count;
        }

        if (map[i][j] == 1){
            count = 1;
            for (int k = 0; k < 4; k++){
                int nx = i + dx[k];
                int ny = j + dy[k];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (set.contains(groups[nx][ny])) continue;

                set.add(groups[nx][ny]);
                count += groupSize[groups[nx][ny]];
            }
        }

        return count % 10;
    }

    private static void makeGroup(int[][] map){
        groups = new int[N][M];
        groupSize = new int[N * M + 1];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (map[i][j] == 0 && groups[i][j] == 0){
                    groupIdx++;
                    bfs(i, j, map);
                }
            }
        }
    }

    private static void bfs(int x, int y, int[][] map){
        groups[x][y] = groupIdx;
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{x, y});
        int size = 1;

        while (!q.isEmpty()){
            int[] nums = q.poll();
            int cx = nums[0];
            int cy = nums[1];

            for (int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if (groups[nx][ny] == 0 && map[nx][ny] == 0){
                    q.add(new int[]{nx, ny});
                    groups[nx][ny] = groupIdx;
                    size++;
                }
            }
        }
        groupSize[groupIdx] = size;

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

        
        int[][] answer = solution(map);
        for (int[] ans : answer) {
            for (int a : ans) {
                System.out.print(a);
            }
            System.out.println();
        }
    }
}
