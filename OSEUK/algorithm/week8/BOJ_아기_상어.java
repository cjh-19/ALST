package baekjoon.bfs.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Shark {
    int x, y, dist;

    Shark(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}
public class BOJ16236 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int sharkX, sharkY, sharkSize = 2, fishCount = 0, time = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            visited = new boolean[N][N];
            Shark nextFish = bfs();

            if (nextFish == null) break;

            sharkX = nextFish.x;
            sharkY = nextFish.y;
            time += nextFish.dist;
            map[sharkX][sharkY] = 0;
            fishCount++;

            if (fishCount == sharkSize) {
                sharkSize++;
                fishCount = 0;
            }
        }

        System.out.println(time);
    }
    static Shark bfs() {
        Queue<Shark> queue = new LinkedList<>();
        queue.add(new Shark(sharkX, sharkY, 0));
        visited[sharkX][sharkY] = true;

        List<Shark> fishList = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Shark current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] <= sharkSize) {
                        visited[nx][ny] = true;
                        queue.add(new Shark(nx, ny, current.dist + 1));

                        if (map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
                            if (current.dist + 1 < minDist) {
                                minDist = current.dist + 1;
                                fishList.clear();
                                fishList.add(new Shark(nx, ny, minDist));
                            } else if (current.dist + 1 == minDist) {
                                fishList.add(new Shark(nx, ny, minDist));
                            }
                        }
                    }
                }
            }
        }

        if (fishList.isEmpty()) return null;

        Shark nextFish = fishList.get(0);
        for (Shark fish : fishList) {
            if (fish.x < nextFish.x || (fish.x == nextFish.x && fish.y < nextFish.y)) {
                nextFish = fish;
            }
        }

        return nextFish;
    }



}
