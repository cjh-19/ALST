/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 보석 도둑
 * 문제 번호 : 1202
 * 난이도 : GOLD II
 */

package baekjoon.greedy.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1202 {
    static int N, K;
    static int[][] gem;
    static int[] bagMaxWeight;
    public static long solution(){
        long answer = 0L;

        // 보석 무게기준 오름차순 정렬
        Arrays.sort(gem, (o1, o2) -> o1[0] - o2[0]);
        // 가방 무게기준 오름차순 정렬
        Arrays.sort(bagMaxWeight);

        // pq에 가격기준 내림차순 정렬되도록
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int gemIdx = 0;

        // 작은 가방부터 순서대로
        for (int i = 0; i < K; i++){
            // 무게순으로 정렬되어있으므로 순서대로 넣음
            while (gemIdx < N && gem[gemIdx][0] <= bagMaxWeight[i]){
                pq.offer(gem[gemIdx][1]);
                gemIdx++;
            }

            // 현재 무게에서 담을 수 있는 가장 비싼 걸 넣음
            if (!pq.isEmpty()){
                answer += pq.poll();
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        N = Integer.parseInt(nk[0]);
        K = Integer.parseInt(nk[1]);

        gem = new int[N][2];

        for (int i = 0; i < N; i++){
            String[] mv = br.readLine().split(" ");
            gem[i][0] = Integer.parseInt(mv[0]);
            gem[i][1] = Integer.parseInt(mv[1]);
        }

        bagMaxWeight = new int[K];

        for (int i = 0; i < K; i++){
            bagMaxWeight[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution());
    }
}
