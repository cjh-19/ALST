package programmers.kit.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JoyStick {
    public static int solution(String name) {
        int n = name.length();
        int totalMoves = 0;

        // 상하 이동 계산
        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);
            totalMoves += Math.min(c - 'A', 'Z' - c + 1);
        }

        // 좌우 이동 계산
        int minLeftRightMoves = n - 1; // 한 방향으로 쭉 가는 경우
        for (int i = 0; i < n; i++) {
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }
            // 좌우 이동 최소값 갱신
            minLeftRightMoves = Math.min(minLeftRightMoves, i + n - next + Math.min(i, n - next));
        }

        return totalMoves + minLeftRightMoves;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();

        System.out.println(solution(name));
    }
}
