package programmers.kit.dfsbfs;

import java.util.*;

public class FillPuzzle {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(int[][] game_board, int[][] table) {
        // 테이블에서 퍼즐 조각들을 추출
        List<int[][]> pieces = extractShapes(table, 1);
        // 게임 보드에서 빈 공간들을 추출
        List<int[][]> spaces = extractShapes(game_board, 0);

        int filledSpaces = 0;

        // 각 빈 공간에 대해 퍼즐 조각들을 시도하여 맞는지 확인
        for (int[][] space : spaces) {
            for (Iterator<int[][]> it = pieces.iterator(); it.hasNext();) {
                int[][] piece = it.next();
                if (canFit(space, piece)) {
                    // 맞는 퍼즐 조각이 있으면 빈 공간을 채운 후 조각을 리스트에서 제거
                    filledSpaces += piece.length;
                    it.remove();
                    break;
                }
            }
        }
        return filledSpaces;
    }

    // 보드에서 특정 값을 가진 블록들을 추출
    private List<int[][]> extractShapes(int[][] board, int target) {
        int n = board.length;
        boolean[][] visited = new boolean[n][n];  // 방문 여부를 기록할 배열
        List<int[][]> shapes = new ArrayList<>();  // 추출된 블록들을 저장할 리스트

        // 보드의 각 위치를 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // target 값이며 아직 방문하지 않은 경우
                if (board[i][j] == target && !visited[i][j]) {
                    List<int[]> shape = new ArrayList<>();
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;

                    // BFS를 사용하여 연결된 블록들을 탐색
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        shape.add(current);
                        for (int[] direction : DIRECTIONS) {
                            int x = current[0] + direction[0];
                            int y = current[1] + direction[1];
                            if (x < 0 || x >= n || y < 0 || y >= n || visited[x][y] || board[x][y] != target) continue;

                            queue.add(new int[]{x, y});
                            visited[x][y] = true;
                        }
                    }

                    // 추출된 블록을 정규화하여 리스트에 추가
                    shapes.add(normalizeShape(shape));
                }
            }
        }
        return shapes;
    }

    // 블록을 정규화하여 (0,0)을 기준으로 변환
    private int[][] normalizeShape(List<int[]> shape) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        // 블록의 최소 x, y 좌표를 찾음
        for (int[] block : shape) {
            minX = Math.min(minX, block[0]);
            minY = Math.min(minY, block[1]);
        }

        // (0,0)을 기준으로 블록을 변환
        int[][] normalized = new int[shape.size()][2];
        for (int i = 0; i < shape.size(); i++) {
            normalized[i][0] = shape.get(i)[0] - minX;
            normalized[i][1] = shape.get(i)[1] - minY;
        }

        // 블록들을 정렬하여 순서를 고정
        Arrays.sort(normalized, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));

        return normalized;
    }

    // 빈 공간에 퍼즐 조각을 맞출 수 있는지 확인
    private boolean canFit(int[][] space, int[][] piece) {
        for (int i = 0; i < 4; i++) {  // 4번 회전 시도
            if (fits(space, piece)) return true;
            piece = rotateShape(piece);  // 퍼즐 조각을 90도 회전
        }
        return false;
    }

    // 두 블록이 동일한지 확인
    private boolean fits(int[][] space, int[][] piece) {
        if (space.length != piece.length) return false;
        for (int i = 0; i < space.length; i++) {
            if (space[i][0] != piece[i][0] || space[i][1] != piece[i][1]) return false;
        }
        return true;
    }

    // 블록을 90도 회전
    private int[][] rotateShape(int[][] shape) {
        int maxX = 0;
        int maxY = 0;

        // 블록의 최대 x, y 좌표를 찾음
        for (int[] block : shape) {
            maxX = Math.max(maxX, block[0]);
            maxY = Math.max(maxY, block[1]);
        }

        // 90도 회전
        int[][] rotated = new int[shape.length][2];
        for (int i = 0; i < shape.length; i++) {
            rotated[i][0] = shape[i][1];
            rotated[i][1] = maxX - shape[i][0];
        }

        // 블록들을 정렬하여 순서를 고정
        Arrays.sort(rotated, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));

        return rotated;
    }
    public static void main(String[] args) {
        FillPuzzle solution = new FillPuzzle();
        int[][] game_board = {
                {1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 0}
        };
        int[][] table = {
                {1, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {1, 0, 1, 1, 0, 0}
        };
        System.out.println(solution.solution(game_board, table));
    }
}
