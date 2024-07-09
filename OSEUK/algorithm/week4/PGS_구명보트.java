package programmers.kit.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class HelpBoat {
    public static int solution(int[] people, int limit){
        Arrays.sort(people);
        int startIdx = 0;
        int endIdx = people.length - 1;
        int answer = 0;

        while (startIdx <= endIdx){
            if (people[startIdx] + people[endIdx] <= limit){
                startIdx++;
            }

            endIdx--;

            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        int limit = 100;

        System.out.println(solution(people, limit));
    }
}
