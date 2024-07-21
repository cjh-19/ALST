package programmers.kit.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordChange {
    static int answer = 0;
    public static int solution(String begin, String target, String[] words) {
        ArrayList<String> visited = new ArrayList<>();

        dfs(begin, target, words, 0, visited);

        return answer;
    }

    public static void dfs(String now, String target, String[] words, int count, ArrayList<String> visited){
        /* 탐색 길어지면 종료 */
        if (count > words.length) return;

        /* 변환 성공 시 결과 갱신 */
        if (now.equals(target)){
            answer = count;
            return;
        }

        visited.add(now); // 해당 단어 방문

        /* 변환 가능 && 방문 X 시 dfs */
        for (String word : words) {
            if (!visited.contains(word) && canTransform(now, word)){
                dfs(word, target, words, count + 1, visited);
            }
        }

    }

    /* 한 글자만 달라졌는지 */
    public static boolean canTransform(String a, String b){
        int diffCount = 0;
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) != b.charAt(i)) diffCount++;

            if (diffCount > 1) return false;
        }

        if (diffCount == 1) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String begin = br.readLine();
        String target = br.readLine();
        String[] words = br.readLine().split(" ");

        System.out.println(solution(begin, target, words));
    }
}
