/* 프로그래머스 이중 우선순위 큐 */
package programmers.kit.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.*;

public class DoublePriorityQueue {
    public static int[] solution(String[] operations) {
        PriorityQueue<Integer> ascPq = new PriorityQueue<>();
        PriorityQueue<Integer> descPq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        Map<Integer, Integer> countMap = new HashMap<>();

        for (String operation : operations) {
            String[] parts = operation.split(" ");
            String command = parts[0];
            int num = Integer.parseInt(parts[1]);
            if (command.equals("I")) {
                ascPq.offer(num);
                descPq.offer(num);
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }
            else{
                if (countMap.isEmpty()) continue;

                if (num == 1){
                    removeElement(descPq, countMap);
                }else {
                    removeElement(ascPq, countMap);
                }
            }
        }

        if (countMap.isEmpty()){
            return new int[]{0, 0};
        } else{
            int max = removeElement(descPq, countMap);
            int min = removeElement(ascPq, countMap);
            return new int[]{max, min};
        }



    }

    private static int removeElement(PriorityQueue<Integer> heap, Map<Integer, Integer> countMap){
        while (!heap.isEmpty()){
            int num = heap.poll();
            int count = countMap.getOrDefault(num, 0);
            if (count > 0) {
                if (count == 1) {
                    countMap.remove(num);
                } else {
                    countMap.put(num, count - 1);
                }
                return num;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] operations = br.readLine().split(",\\s");

        int[] result = solution(operations);
        for (int res : result) {
            System.out.print(res + " ");
        }
    }
}
