/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 돌 그룹
 * 문제 번호 : 12886
 * 난이도 : GOLD IV
 */
package baekjoon.bfs.gold;

import java.util.*;

public class BOJ12886 {
    static int A, B, C;
    private static int solution(int A, int B, int C){
        if ((A + B + C) % 3 != 0) return 0; // 돌의 총합이 3의 배수가 아니면 불가능

        // 그룹의 총 합은 항상 같으므로 2차원배열로 선언
        boolean[][] visited = new boolean[1501][1501];
        int[] arr = new int[]{A, B, C};

        return bfs(arr, visited);
    }

    private static int bfs(int[] arr, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        Arrays.sort(arr);
        q.add(arr);
        visited[arr[0]][arr[1]] = true;

        while (!q.isEmpty()){
            int[] stones = q.poll();
            int x = stones[0];
            int y = stones[1];
            int z = stones[2];

            if (x == y && y == z) return 1;

            int[][] newStates = {
                    {x + x, y - x, z},
                    {x, y + y, z - y},
                    {x + x, y, z - x}
            };

            for (int[] newState : newStates) {
                Arrays.sort(newState);
                int newX = newState[0];
                int newY = newState[1];
                if (!visited[newX][newY]) {
                    visited[newX][newY] = true;
                    q.add(newState);
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        System.out.println(solution(A, B, C));
    }
}
