package programmers.skilltest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lv1_1 {
    public static int solution(int n, int[] lost, int[] reserve){
        int[] uniforms = new int[n + 1];
        for (int i : lost)
            uniforms[i]--;
        for (int i : reserve)
            uniforms[i]++;

        for (int i = 1; i < uniforms.length; i++){
            if (uniforms[i] == 0) continue;
            if (uniforms[i] == 1 && uniforms[i-1] == -1){
                uniforms[i]--;
                uniforms[i-1]++;
            }
            if (uniforms[i] == -1 && uniforms[i-1] == 1){
                uniforms[i]++;
                uniforms[i-1]--;
            }
        }

        int answer = -1;
        for (int uniform : uniforms) {
            if (uniform >= 0)
                answer++;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] losts = br.readLine().split(" ");
        String[] reserves = br.readLine().split(" ");

        int[] lost = new int[losts.length];
        int[] reserve = new int[reserves.length];

        for (int i = 0; i < losts.length; i++)
            lost[i] = Integer.parseInt(losts[i]);
        for (int i = 0; i < reserves.length; i++)
            reserve[i] = Integer.parseInt(reserves[i]);

        System.out.println(solution(n, lost, reserve));
    }
}
