package programmers.kit.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HIndex {
    public static int solution(int[] citations){
        Arrays.sort(citations);
        int hIndex = 0;
        for (int i = citations.length-1; i>=0; i--){
            int h = Math.min(citations[i], citations.length-i);
            hIndex  = Math.max(hIndex, h);
        }

        return hIndex;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int[] citations = new int[strs.length];

        for (int i = 0; i < citations.length; i++){
            citations[i] = Integer.parseInt(strs[i]);
        }

        System.out.println(solution(citations));
    }
}
