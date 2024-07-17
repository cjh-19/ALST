package programmers.kit.greedy;

import java.util.Arrays;

public class SpeedCamera {
    public static int solution(int[][] routes) {
        /* deadline 기준으로 정렬 */
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);

        /* 첫 번째 deadline에 단속카메라를 하나 설치한 것으로 설정 */
        int time = routes[0][1];
        int answer = 1;

        for (int[] route : routes) {
            if (route[0] <= time) continue; // 이번 차량이 단속카메라를 포함하면 패스

            answer++;
            time = route[1];    // 포함하지 않으면 단속카메라를 설치
        }

        return answer;

    }
    public static void main(String[] args) {
        int[][] routes = {
                {-20, -15},
                {-14, -5},
                {-18, -13},
                {-5, -3}
        };

        System.out.println(solution(routes));
    }
}
