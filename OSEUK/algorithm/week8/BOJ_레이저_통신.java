/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 레이저 통신
 * 문제 번호 : 6087
 * 난이도 : GOLD III
 */
package baekjoon.bfs.gold;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
    int x, y, dir, mirrors;

    public Point(int x, int y, int dir, int mirrors) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.mirrors = mirrors;
    }
}

public class BOJ6087 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][][] visited;
    static int w, h;
    static char[][] map;
    static Point start, end;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        w = sc.nextInt();
        h = sc.nextInt();
        map = new char[h][w];
        visited = new int[h][w][4];

        int cCount = 0;
        for (int i = 0; i < h; i++) {
            String line = sc.next();
            for (int j = 0; j < w; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') {
                    if (cCount == 0) {
                        start = new Point(i, j, -1, 0);
                    } else {
                        end = new Point(i, j, -1, 0);
                    }
                    cCount++;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        for (int i = 0; i < 4; i++) {
            visited[start.x][start.y][i] = 0;
        }

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.x == end.x && cur.y == end.y) {
                return cur.mirrors;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nextMirrors = cur.mirrors;

                if (cur.dir != -1 && cur.dir != i) {
                    nextMirrors++;
                }

                while (nx >= 0 && nx < h && ny >= 0 && ny < w && map[nx][ny] != '*') {
                    if (visited[nx][ny][i] == 0 || visited[nx][ny][i] > nextMirrors) {
                        visited[nx][ny][i] = nextMirrors;
                        queue.add(new Point(nx, ny, i, nextMirrors));
                    }
                    nx += dx[i];
                    ny += dy[i];
                }
            }
        }

        return -1;
    }
}
