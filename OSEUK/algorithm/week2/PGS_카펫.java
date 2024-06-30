package programmers.kit.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Carpet {
    public static int[] solution(int brown, int yellow){
        int[] answer = new int[0];
        for (int i = 1; i <= 5000; i++) {
            for (int j = i; i * j <= 2000000; j++) {
                if (i * j == yellow && (i + 2) * (j + 2) - yellow == brown) {
                    answer =  new int[]{j + 2, i + 2};
                    break;
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int brown = Integer.parseInt(br.readLine());
        int yellow = Integer.parseInt(br.readLine());

        int[] result = solution(brown, yellow);
        for (int res : result) {
            System.out.print(res + " ");
        }
    }
}
