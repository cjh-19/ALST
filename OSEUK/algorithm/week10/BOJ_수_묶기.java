/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 수 묶기
 * 문제 번호 : 1744
 * 난이도 : GOLD IV
 */
package baekjoon.greedy.gold;

import java.util.*;

public class BOJ1744 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int ones = 0; // 1의 개수
        int zeros = 0; // 0의 개수
        int result = 0;

        // 입력을 받아서 양수, 음수, 1, 0으로 나눔
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (num > 1) {
                positive.add(num);
            } else if (num == 1) {
                ones++;
            } else if (num == 0) {
                zeros++;
            } else {
                negative.add(num);
            }
        }

        // 양수는 내림차순으로 정렬 (큰 수끼리 묶는 것이 유리)
        Collections.sort(positive, Collections.reverseOrder());
        // 음수는 오름차순으로 정렬 (작은 수끼리 묶는 것이 유리)
        Collections.sort(negative);

        // 양수는 2개씩 묶어서 처리
        for (int i = 0; i < positive.size(); i += 2) {
            if (i + 1 < positive.size()) {
                result += positive.get(i) * positive.get(i + 1);
            } else {
                result += positive.get(i);
            }
        }

        // 음수는 2개씩 묶어서 처리
        for (int i = 0; i < negative.size(); i += 2) {
            if (i + 1 < negative.size()) {
                result += negative.get(i) * negative.get(i + 1);
            } else {
                // 음수가 하나 남았는데 0이 있으면 0과 묶어 없앨 수 있음
                if (zeros == 0) {
                    result += negative.get(i);
                }
            }
        }

        // 1의 개수만큼 더해줌 (1은 더하는 것이 최적)
        result += ones;

        // 최종 결과 출력
        System.out.println(result);
    }
}
