/**
 * CODE TREE 삼성 SW 역량테스트
 * 2024 상반기 오전 2번 문제
 * 문제 이름 : 코드트리 투어
 * 난이도 : GOLD II
 */
package codetree.samsung;

import java.util.*;
import java.io.*;
public class CodetreeTour {
    private static final int MAX_N = 2000;
    private static final int MAX_M = 10000;
    private static final int MAX_ID = 30000;
    private static final int INF = 100000000;

    static int Q;
    static int n, m;
    static ArrayList<ArrayList<Node>> land = new ArrayList<>();
    static int[] distances;
    static PriorityQueue<Item> items = new PriorityQueue<>();
    static boolean[] isMade = new boolean[MAX_ID + 1];
    static boolean[] isCancel = new boolean[MAX_ID + 1];

    static class Node implements Comparable<Node>{
        int v;  // 정점
        int w;  // 가중치

        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node n){
            return this.w - n.w;
        }
    }

    static class Item implements Comparable<Item>{
        int id;
        int revenue;
        int dest;
        int profit;

        public Item(int id, int revenue, int dest, int profit){
            this.id = id;
            this.revenue = revenue;
            this.dest = dest;
            this.profit = profit;
        }

        @Override
        public int compareTo(Item i){
            if (this.profit == i.profit){
                return this.id - i.id;
            }
            return i.profit - this.profit;
        }
    }

    // 다익스트라 알고리즘으로 출발지로부터 노드로의 거리 계산
    public static void setDistances(int s){
        for (int i = 0; i < n; i++){
            distances[i] = INF;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        distances[s] = 0;

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            int cv = curr.v;
            int cw = curr.w;

            if (cw > distances[cv]) continue;

            for (Node n : land.get(cv)){
                int newDist = distances[cv] + n.w;

                if (newDist < distances[n.v]){
                    distances[n.v] = newDist;
                    pq.add(new Node(n.v, newDist));
                }
            }
        }

    }

    public static int sell(){
        while (!items.isEmpty()){
            Item i = items.peek();

            if (i.profit < 0){
                break;
            }

            items.poll();
            if (!isCancel[i.id]){
                return i.id;
            }
        }

        return -1;
    }

    public static void changeStart(int s){
        setDistances(s);
        List<Item> packages = new ArrayList<>();
        while (!items.isEmpty()){
            packages.add(items.poll());
        }

        for (Item p : packages){
            items.add(new Item(p.id, p.revenue, p.dest, p.revenue - distances[p.dest]));
            isMade[p.id] = true;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Q = Integer.parseInt(st.nextToken());

        while (Q-- > 0){
            st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());

            switch (order){
                case 100:
                    // 코드트리 랜드 건설
                    n = Integer.parseInt(st.nextToken());
                    // 출발지로부터의 거리를 측정한 distance
                    distances = new int[n];

                    m = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < n; i++){
                        land.add(new ArrayList<>());
                    }
                    for (int i = 0; i < m; i++){
                        int v = Integer.parseInt(st.nextToken());
                        int u = Integer.parseInt(st.nextToken());
                        int w = Integer.parseInt(st.nextToken());
                        land.get(v).add(new Node(u, w));
                        land.get(u).add(new Node(v, w));
                    }

                    setDistances(0);
                    break;
                case 200:
                    // 여행 상품 생성
                    int id = Integer.parseInt(st.nextToken());
                    int revenue = Integer.parseInt(st.nextToken());
                    int dest = Integer.parseInt(st.nextToken());

                    items.add(new Item(id, revenue, dest, revenue - distances[dest]));
                    isMade[id] = true;
                    break;
                case 300:
                    // 여행 상품 취소
                    id = Integer.parseInt(st.nextToken());

                    if (isMade[id]) isCancel[id] = true;
                    break;
                case 400:
                    // 최적의 여행 상품 판매
                    System.out.println(sell());
                    break;
                case 500:
                    // 여행 상품의 출발지 변경
                    int number = Integer.parseInt(st.nextToken());
                    changeStart(number);
                    break;

            }

        }
    }
}
