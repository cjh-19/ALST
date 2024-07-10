package programmers.kit.dfsbfs;

public class targetNumber {
    private static int answer = 0;
    public static int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }

    private static void dfs(int[] numbers, int target, int idx, int cur){
        if (idx == numbers.length && cur == target){
            answer++;
            return;
        }

        if (idx >= numbers.length)
            return;

        dfs(numbers, target, idx + 1, cur + numbers[idx]);
        dfs(numbers, target, idx + 1, cur - numbers[idx]);

    }
    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println(solution(numbers, target));
    }
}
