package programmers.kit.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MockExam {
    public static int[] solution(int[] answers) {
        ArrayList<Integer> al = new ArrayList<>();
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] arr3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int a = countAnswers(arr1, answers);
        int b = countAnswers(arr2, answers);
        int c = countAnswers(arr3, answers);

        int maxnum = Math.max(a, Math.max(b, c));

        if (a == maxnum)
            al.add(1);
        if (b == maxnum)
            al.add(2);
        if (c == maxnum)
            al.add(3);

        return al.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int countAnswers(int[] arr, int[] answers){
        int answer = 0;

        int idx;
        for (int i = 0; i < answers.length; i++){
            idx = i % arr.length;
            if (answers[i] == arr[idx])
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int[] answers = Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] answer = solution(answers);
        for (int ans : answer) {
            System.out.print(ans + " ");
        }
    }
}
