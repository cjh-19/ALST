package programmers.kit.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class FindingPrime {

    public static int solution(String numbers){
        Set<Integer> numSet = new HashSet<>();
        makeComb("", numbers, numSet);

        int count = 0;
        for (Integer i : numSet) {
            if (isPrime(i)) count++;
        }

        return count;
    }

    /* 문자열 조합의 경우의 수 숫자로 저장 */
    private static void makeComb(String prefix, String str, Set<Integer> numSet){
        int n = str.length();
        if (!prefix.isEmpty()) numSet.add(Integer.parseInt(prefix));

        for (int i = 0; i < n; i++){
            makeComb(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), numSet);
        }
    }

    /* 소수 구하기 */
    private static boolean isPrime(int num){
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num) ; i += 2){
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numbers = br.readLine();

        System.out.println(solution(numbers));
    }
}
