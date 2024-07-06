package programmers.kit.bruteforce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VowelDictionary {
    private static final char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};
    private static List<String> dictionary = new ArrayList<>();

    public static int solution(String word){
        generateWords("", 0);
        Collections.sort(dictionary);
        return dictionary.indexOf(word) + 1;
    }

    /* dfs활용 완전탐색 */
    private static void generateWords(String current, int length){
        if (length > 5){
            return;
        }
        if (!current.isEmpty()){
            dictionary.add(current);
        }
        for (char c : VOWELS){
            generateWords(current + c, length + 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(solution("EIO"));
    }
}
