package programmers.kit.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MoreHot {
    public static int solution(int[] scoville, int K) {
        /* PriorityQueue 활용 시 쉽게 구현 가능 */
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int s : scoville) {
            pq.add(s);
        }

        int count = 0;
        while (pq.size() > 1 && pq.peek() < K){
            int first = pq.poll();
            int second = pq.poll();

            int newScoville = first + 2 * second;
            pq.add(newScoville);
            count++;
        }

        if (pq.peek() >= K)
            return count;
        else
            return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int K = Integer.parseInt(br.readLine());

        int[] scoville = new int[strs.length];
        for (int i = 0; i < strs.length; i++){
            scoville[i] = Integer.parseInt(strs[i]);
        }

        System.out.println(solution(scoville, K));
    }
}
