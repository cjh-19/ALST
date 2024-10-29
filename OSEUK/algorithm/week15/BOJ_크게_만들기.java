/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 크게 만들기
 * 문제 번호 : 2812
 * 난이도 : GOLD III
 */
package baekjoon.greedy.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ2812 {
    static int N, K;
    static char[] nums;

    // 구현
    public static String simulate(){
        Stack<Integer> stack = new Stack<>();

        // 각 글자 탐색
        for (int i = 0; i < nums.length; i++){
            // int로 변환
            int curr = Character.getNumericValue(nums[i]);

            // 현재 값과 stack top에 있는 값을 비교해서 더 큰 값이 맨 앞에 들어가도록 pop
            while (!stack.isEmpty() && stack.peek() < curr && K != 0){
                stack.pop();
                K--;
            }

            // 현재 값은 항상 stack에 넣음
            stack.push(curr);
        }

        // K개가 다 지워지지 않았다면, K개 지워질 때 까지 pop
        while( K > 0 ){
            stack.pop();
            K--;
        }

        // stack을 string으로 매핑 (최대 길이가 500000)
        String result = stack.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String s = br.readLine();
        nums = s.toCharArray();
        System.out.println(simulate());
    }
}
