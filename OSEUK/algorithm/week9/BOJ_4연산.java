/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 4연산
 * 문제 번호 : 14395
 * 난이도 : GOLD IV
 */

package baekjoon.bfs.gold;

import java.util.*;

public class BOJ14395 {

    static class Node {
        long value;
        String operations;

        Node(long value, String operations) {
            this.value = value;
            this.operations = operations;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long s = sc.nextLong();
        long t = sc.nextLong();

        if (s == t) {
            System.out.println(0);  // s와 t가 같으면 연산이 필요 없음
            return;
        }

        // BFS를 위한 큐와 방문 체크를 위한 Set
        Queue<Node> queue = new LinkedList<>();
        Set<Long> visited = new HashSet<>();

        queue.add(new Node(s, ""));
        visited.add(s);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.value == t){
                System.out.println(current.operations);
                return;
            }

            // * 연산
            long nextValue = current.value * current.value;

            if (nextValue <= t && !visited.contains(nextValue)) {
                queue.add(new Node(nextValue, current.operations + "*"));
                visited.add(nextValue);
            }

            // + 연산
            nextValue = current.value + current.value;

            if (nextValue <= t && !visited.contains(nextValue)) {
                queue.add(new Node(nextValue, current.operations + "+"));
                visited.add(nextValue);
            }

            // / 연산 (0이 아닌 수만 처리)
            if (!visited.contains(1L)){
                queue.add(new Node(1, current.operations + "/"));
                visited.add(1L);
            }

        }

        // s에서 t로 변환할 수 없으면 -1 출력
        System.out.println(-1);
    }
}
