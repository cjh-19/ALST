package programmers.kit.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class TheBiggestNumber {
    public static String solution(int[] numbers) {
        // int 배열 String으로 변환
        String[] nums = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        // 두 값을 합해서 비교
        Arrays.sort(nums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        // [0, 0, 0..] 일 경우 예외처리
        if (nums[0].equals("0")) {
            return "0";
        }

        // 문자열로 변환
        StringBuilder answer = new StringBuilder();
        for (String num : nums) {
            answer.append(num);
        }

        return answer.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int[] numbers = new int[strs.length];
        for(int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.parseInt(strs[i]);

        System.out.println(solution(numbers));
    }
}
