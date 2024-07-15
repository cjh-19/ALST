package programmers.kit.greedy;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class IslandConnect {
    public static int solution(int n, int[][] costs) {
        ArrayList<ArrayList<int[]> > graph = new ArrayList<>();

        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for (int[] cost : costs) {
            graph.get(cost[0]).add(new int[]{cost[1], cost[2]});
            graph.get(cost[1]).add(new int[]{cost[0], cost[2]});
        }

        boolean[] visited = new boolean[n];

        int answer = prim(graph, visited, n);
        return answer;

    }

    /* 프림 알고리즘 */
    public static int prim(ArrayList<ArrayList<int[]>> graph, boolean[] visited, int n){
        /* 우선순위 큐 사용. 짧은 거리 기준으로 정렬되도록 */
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        pq.add(new int[]{0, 0});
        int totalCost = 0;
        int nodesVisited = 0;

        /* 모든 정점 방문 시 종료 */
        while (!pq.isEmpty() && nodesVisited < n){
            int[] cost = pq.poll();
            int currentNode = cost[0];
            int currentCost = cost[1];

            if (visited[currentNode]) continue;

            visited[currentNode] = true;
            totalCost += currentCost;
            nodesVisited++;

            for(int[] nextNodes : graph.get(cost[0])){
                int nextNode = nextNodes[0];
                int distance = nextNodes[1];

                if (!visited[nextNode] && distance != 0){
                    pq.add(new int[]{nextNode, distance});
                }
            }
        }

        return totalCost;
    }


    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2},  {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};

        System.out.println(solution(n, costs));
    }
}
