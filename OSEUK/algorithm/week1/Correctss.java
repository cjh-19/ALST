package programmers.kit.stackqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Correctss {
    public static boolean solution(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stk = new Stack<>();

        for (char c : chars) {
            if (c == '(')
                stk.push('(');
            if (c == ')'){
                if (stk.isEmpty())
                    return false;
                stk.pop();
            }
        }
        return stk.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println(solution(s));
    }
}
