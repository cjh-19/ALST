/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 순회 강연
 * 문제 번호 : 2109
 * 난이도 : GOLD III
 */
package baekjoon.greedy.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ2109 {
    static int N;
    static int[][] lectures;
    public static long solution(){
        if (N == 0) return 0;

        // 가장 널널한 날짜의 강의
        Arrays.sort(lectures, (o1, o2) -> o2[1] - o1[1]);

        long answer = 0L;
        // 가격이 가장 큰 강의가 앞에 오도록
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int lecIdx = 0;

        // 가장 널널한 강의부터 날짜 역순으로
        for (int day = lectures[0][1]; day >= 1; day--){

            while (lecIdx < N && lectures[lecIdx][1] == day){
                pq.add(lectures[lecIdx][0]);
                lecIdx++;
            }

            // pq에 들어있는 강의들은 day에 항상 가능한 강의이므로 하루에 1개씩 poll
            if (!pq.isEmpty()){
                answer += pq.poll();
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lectures = new int[N][2];
        for (int i = 0; i< N; i++){
            String[] pd = br.readLine().split(" ");
            lectures[i][0] = Integer.parseInt(pd[0]);
            lectures[i][1] = Integer.parseInt(pd[1]);
        }

        System.out.println(solution());
    }
}
