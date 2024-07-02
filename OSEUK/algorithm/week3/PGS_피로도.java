package programmers.kit.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fatigue {
    /* 최대 던전 수 */
    public static int max_count = 0;
    public static int solution(int k, int[][] dungeons){
        boolean[] visited = new boolean[dungeons.length];

        dfs(k, dungeons, visited, 0);

        return max_count;
    }
    /* dfs로 완전탐색 */
    private static void dfs(int k, int[][] dungeons, boolean[] visited, int count){
        if (count > max_count) max_count = count;

        for (int i = 0; i < dungeons.length; i++){
            if (dungeons[i][0] <= k && !visited[i]){ /* 최소 필요 필요도 충족 , 방문 여부 */
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, visited, count + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        int[][] dungeons = {{80,20}, {50,40}, {30, 10}};

        System.out.println(solution(k, dungeons));

    }
}
