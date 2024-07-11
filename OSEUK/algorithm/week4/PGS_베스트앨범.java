package programmers.kit.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BestAlbum {
    public static int[] solution(String[] genres, int[] plays) {
        HashMap<String, int[]> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++){
            if (!map.containsKey(genres[i])){
                map.put(genres[i], new int[]{plays[i], i, -1});
                continue;
            }

            /*
               value[0] = 총 재생 횟수
               value[1] = 가장 많이 재생된 노래의 index
               value[2] = 두번째로 많이 재생된 노래의 index
             */
            int[] value = map.get(genres[i]);
            value[0] += plays[i];

            if (plays[i] > plays[value[1]]){
                value[2] = value[1];
                value[1] = i;
            } else if (value[2] == -1) {
                value[2] = i;
            } else if (plays[i] > plays[value[2]]){
                value[2] = i;
            }
        }

        /* 정렬 */
        List<String> sortedGenres = map.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue()[0], e1.getValue()[0]))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();

        for (String genre : sortedGenres) {
            int[] value = map.get(genre);

            result.add(value[1]);
            if (value[2] != -1) result.add(value[2]);
        }

        return result.stream().mapToInt(i -> i).toArray();

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] genres = br.readLine().split(" ");
        int[] plays = new int[genres.length];

        String[] strs = br.readLine().split(" ");
        for (int i = 0; i  < plays.length; i++){
            plays[i] = Integer.parseInt(strs[i]);
        }

        int[] answer = solution(genres, plays);
        for (int ans : answer) {
            System.out.print(ans + " ");
        }

    }
}
