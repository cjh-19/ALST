/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 중량제한
 * 문제 번호 : 1939
 * 난이도 : GOLD III
 */

package baekjoon.binarysearch.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1939 {
    private static final int MAX_WEIGHT = 1000000000;
    private static final int MAX_N = 10000;
    static int N, M;
    static List<List<Node>> graph;
    static int first, second;
    static boolean[] visited;
    static class Node{
        int v;
        int weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    public static boolean bfs(int weight){
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[MAX_N + 1];

        q.offer(first);
        visited[first] = true;

        while (!q.isEmpty()){
            int curr = q.poll();

            if (curr == second) return true;

            for (Node n : graph.get(curr)){
                if (visited[n.v] || n.weight < weight) continue;

                q.offer(n.v);
                visited[n.v] = true;
            }
        }

        return false;

    }

    public static int simulate(int s, int e){
        int answer = 0;

        while (s <= e){
            int mid = (s + e) / 2;

            if (bfs(mid)){
                answer = mid;
                s = mid + 1;
            }
            else e = mid - 1;
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        // 연결 리스트로 섬 간 연결
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        // 공장이 위치한 섬 두 가지.
        st = new StringTokenizer(br.readLine());
        first = Integer.parseInt(st.nextToken());
        second = Integer.parseInt(st.nextToken());

        System.out.println(simulate(1, MAX_WEIGHT));
    }
}
