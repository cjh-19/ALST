package programmers.kit.binarysearch;

import java.util.Arrays;

public class ImmigrationCheck {
    public static long solution(int n, int[] times) {
        // 심사 시간 정렬
        Arrays.sort(times);

        long left = 1;  // 최소 시간
        long right = (long) times[times.length - 1] * n; // 최대 시간

        long answer = right;

        while (left <= right){
            long mid = (left + right) / 2;
            long sum = 0;

            // 각 심사대에서 mid시간에 처리할 수 있는 인원 수 계산
            for (int time : times){
                sum += mid / time;
            }

            // 모든 사람을 심사할 수 있는 경우 -> 최소시간 찾기 위해 왼쪽으로
            if (sum >= n){
                answer = mid;
                right = mid - 1;
            }

            // 모든 사람을 심사할 수 없는 경우 -> 심사 가능하도록 시간을 늘림
            else{
                left = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};

        System.out.println(solution(n, times));
    }
}
