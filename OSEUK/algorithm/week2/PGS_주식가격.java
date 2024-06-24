package programmers.kit.stackqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StockPrices {
    public static int[] solution(int[] prices) {
        int[] counts = new int[prices.length];

        int idx = 0;
        /* 1초가 경과한 뒤 시간부터, 가격이 떨어져 있는지 검사하고 한번 떨어졌다면 카운트 증가 X */
        while (idx < prices.length){
            for (int i = idx+1; i < counts.length; i++){
                counts[idx]++;
                if (prices[i] < prices[idx]) break;
            }
            idx++;
        }

        return counts;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int[] prices = new int[strs.length];
        for (int i = 0; i < strs.length; i++){
            prices[i] = Integer.parseInt(strs[i]);
        }

        int[] result = solution(prices);
        for (int res : result) {
            System.out.print(res + " ");
        }
    }
}
