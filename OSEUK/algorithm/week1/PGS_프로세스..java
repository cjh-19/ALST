package programmers.kit.stackqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Process {
    public static int solution(int[] priorities, int location) {

        // 큐에 인덱스 값 넣기
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(i);
        }

        int answer = 0;
        while (!queue.isEmpty()){
            int cur = queue.poll();
            boolean highpr = true;

            // 우선순위 가장 높은지 검사
            for (Integer q : queue) {
                if (priorities[cur] < priorities[q]) {
                    highpr = false;
                    break;
                }
            }

            // 찾고자하는 location값이면 return
            if (highpr) {
                if (cur == location)
                    return answer + 1;
                answer++;
                continue;
            }

            // 우선순위가 더 큰값이 있으면 큐 맨 뒤에 삽입
            queue.add(cur);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int location = Integer.parseInt(br.readLine());

        int[] priorities = new int[str.length];
        int i = 0;
        for (String s : str) {
            priorities[i++] = Integer.parseInt(s);
        }

        // 결과 출력
        System.out.println(solution(priorities, location));

    }
}
