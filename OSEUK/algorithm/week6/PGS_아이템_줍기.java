package programmers.kit.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

public class ItemGet {
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        /* 테두리만 가져온 맵 생성*/
        int[][] map = makeMap(rectangle);

        /* 목적지까지 최단 거리 출력 */
        return bfs(map, characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

    public static int bfs(int[][] map, int characterX, int characterY, int itemX, int itemY) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(characterX, characterY, 0));

        /* 상하좌우 및 방문여부 체크 */
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        boolean[][] visited = new boolean[101][101];
        visited[characterX][characterY] = true;

        while (!q.isEmpty()){
            Pos pos = q.poll();

            /* 목적지 도달 시 return */
            if (pos.x == itemX && pos.y == itemY){
                return pos.count / 2;
            }

            /* 길 따라 이동 */
            for (int i = 0; i < 4; i++){
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < 101 && ny < 101 && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Pos(nx, ny, pos.count + 1));
                }
            }
        }

        return -1;
    }


    private static int[][] makeMap(int[][] rectangle) {
        int[][] map = new int[101][101];

        /* 2를 곱하여 좌표 확장 -> 폭이 1일 경우 확인이 어렵기 때문 */
        for (int[] rect : rectangle) {
            int minX = rect[0] * 2;
            int minY = rect[1] * 2;
            int maxX = rect[2] * 2;
            int maxY = rect[3] * 2;
            for (int i = minX; i <= maxX; i++) {
                for (int j = minY; j <= maxY; j++) {
                    map[i][j] = 1; // 경계 및 내부를 1로 설정
                }
            }
        }

        // 내부를 0으로 되돌림
        // 먼저 테두리 그리고 내부 제거 하는거 중요 이거 때문에 오래걸림
        for (int[] rect : rectangle) {
            int minX = rect[0] * 2 + 1;
            int minY = rect[1] * 2 + 1;
            int maxX = rect[2] * 2 - 1;
            int maxY = rect[3] * 2 - 1;
            for (int i = minX; i <= maxX; i++) {
                for (int j = minY; j <= maxY; j++) {
                    map[i][j] = 0;
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        int[][] rectangle = {
                {1, 1, 7, 4},
                {3, 2, 5, 5},
                {4, 3, 6, 9},
                {2, 6, 8, 8}
        };
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;

        System.out.println(solution(rectangle, characterX,characterY,itemX,itemY));
    }

}
