package programmers.kit.dfsbfs;

import java.util.ArrayList;

public class Network {
    public static int solution(int n, int[][] computers) {
        /* 그래프로 바꾸기 */
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        /* 나 아닌 것만 연결 */
        for (int i = 0; i < n; i++){
            int[] computer = computers[i];

            for (int j = 0; j < computer.length; j++){
                if (i == j) continue;
                if (computer[j] == 1) graph.get(i).add(j);
            }
        }

        boolean[] visited = new boolean[n];
        int answer = 0;

        /* dfs 한번 돌 때마다 네트워크 1개임 */
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                dfs(graph, visited, i);
                answer++;
            }
        }

        return answer;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int curr_node){
        visited[curr_node] = true;

        for (Integer node : graph.get(curr_node)) {
            if (!visited[node]) dfs(graph, visited, node);
        }
    }


    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };

        System.out.println(solution(n, computers));
    }
}
