package programmers.kit.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DiskController {
    public static int solution(int[][] jobs) {
        /* 도착 시간 순서대로 정렬 */
        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0], b[0]));

        /* 실행 시간 짧은 순으로 들어가도록 */
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        int currentTime = 0;
        int totalWaitingTime = 0;
        int jobIndex = 0;
        int jobsCount = jobs.length;

        while (jobIndex < jobsCount || !pq.isEmpty()){
            while (jobIndex < jobsCount && jobs[jobIndex][0] <= currentTime){
                pq.add(jobs[jobIndex++]);
            }

            if (pq.isEmpty()){
                currentTime =  jobs[jobIndex][0];
                continue;
            }

            int[] job = pq.poll();
            currentTime += job[1];
            totalWaitingTime += currentTime - job[0];
        }

        return totalWaitingTime / jobsCount;
    }

    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};

        System.out.println(solution(jobs));
    }
}
