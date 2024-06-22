package programmers.skilltest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lv1_2 {
    public static int solution(int[] absolutes, boolean[] signs) {
        for (int i = 0; i < absolutes.length; i++){
            if (!signs[i]) absolutes[i] -= 2 * absolutes[i];
        }
        int answer = 0;
        for (int absolute : absolutes) {
            answer += absolute;
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
}
