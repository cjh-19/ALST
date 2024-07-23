package programmers.kit.dfsbfs;

import java.util.*;

public class TravelLoad {
    private static List<String> route = new LinkedList<>(); // 결과 배열
    private static Map<String, PriorityQueue<String>> graph = new HashMap<>();  // 그래프 생성
    public static String[] solution(String[][] tickets) {
        /* 각 출발지별로 방문할 수 있는 노드 저장 */
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];

            graph.putIfAbsent(from, new PriorityQueue<>());

            graph.get(from).add(to);
        }

        dfs("ICN");

        return route.toArray(new String[0]);

    }

    public static void dfs(String node){
        PriorityQueue<String> pq = graph.get(node);

        while (pq != null && !pq.isEmpty()){
            dfs(pq.poll());
        }

        // 탐색이 완료된 후 역순으로 추가
        route.add(0, node);
    }


    public static void main(String[] args) {
        String[][] tickets = {
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"}
        };

        System.out.println(Arrays.toString(solution(tickets)));
    }
}
