package programmers.kit.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DividePowergrid {
    public static int solution(int n, int[][] wires){
        int answer = Integer.MAX_VALUE;

        /* 그래프 생성 */
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        /* 무방향 그래프 */
        for (int[] wire : wires){
            int u = wire[0] - 1;
            int v = wire[1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        /* 간선 하나씩 제거해보며 dfs 통해 차이 최소화 완전탐색 */
        for (int[] wire: wires){
            int u = wire[0] - 1;
            int v = wire[1] - 1;

            graph.get(u).remove(Integer.valueOf(v));
            graph.get(v).remove(Integer.valueOf(u));

            boolean[] visited = new boolean[n];
            int nodeCounts = countNodes(graph, u, visited);

            int diff = Math.abs(nodeCounts - (n - nodeCounts));
            answer = Math.min(answer, diff);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return answer;

    }

    private static int countNodes(List<List<Integer>> graph, int node, boolean[] visited){
        visited[node] = true;
        int count = 1;

        for (int neighbor : graph.get(node)){
            if (!visited[neighbor]){
                count += countNodes(graph, neighbor, visited);
            }
        }

        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] wires = {{1,3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, { 7, 8}, {7, 9}};

        System.out.println(solution(n, wires));
    }
}
