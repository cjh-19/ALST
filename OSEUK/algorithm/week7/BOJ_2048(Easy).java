/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 2048 (Easy)
 * 문제 번호 : 12100
 * 난이도 : GOLD I
 */
package baekjoon.bruthforce.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12100 {
    static int answer = Integer.MIN_VALUE;
    public static int solution(int[][] board){
        dfs(board, 0);

        return answer;
    }

    public static void dfs(int[][] board, int count){
        // 5번 이동 시 현재 게임판의 최대 값
        if (count >= 5) {
            answer = Math.max(answer, findMax(board));
            return;
        }

        for (int i = 0; i < 4; i++){
            int[][] newBoard = moveBoard(i, board);
            dfs(newBoard, count + 1);
        }
    }

    // 게임판을 한쪽으로 미는 함수
    private static int[][] moveBoard(int direction, int[][] board) {
        int n = board.length;
        int[][] newBoard = new int[n][n];
        boolean[][] merged = new boolean[n][n];

        // 깊은 복사
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, n);
        }

        switch (direction) {
            case 0: // 상 (위로 밀기)
                for (int j = 0; j < n; j++) {   // 열 순서는 상관 X
                    for (int i = 1; i < n; i++) {   // 행 순서는 중요
                        if (newBoard[i][j] == 0) continue;
                        int row = i;
                        while (row > 0 && newBoard[row - 1][j] == 0) { // 위쪽에 숫자가 막고있지 않으면 이동
                            newBoard[row - 1][j] = newBoard[row][j];
                            newBoard[row][j] = 0;
                            row--;
                        }
                        // 이동 다 했는데 앞의 숫자가 같은 숫자면 합침. 이미 합쳐진적 있으면 패스
                        if (row > 0 && newBoard[row - 1][j] == newBoard[row][j] && !merged[row - 1][j]) {
                            newBoard[row - 1][j] *= 2;
                            newBoard[row][j] = 0;
                            merged[row - 1][j] = true;
                        }
                    }
                }
                break;
            case 1: // 하 (아래로 밀기)
                for (int j = 0; j < n; j++) {
                    for (int i = n - 2; i >= 0; i--) {
                        if (newBoard[i][j] == 0) continue;
                        int row = i;
                        while (row < n - 1 && newBoard[row + 1][j] == 0) {
                            newBoard[row + 1][j] = newBoard[row][j];
                            newBoard[row][j] = 0;
                            row++;
                        }
                        if (row < n - 1 && newBoard[row + 1][j] == newBoard[row][j] && !merged[row + 1][j]) {
                            newBoard[row + 1][j] *= 2;
                            newBoard[row][j] = 0;
                            merged[row + 1][j] = true;
                        }
                    }
                }
                break;
            case 2: // 좌 (왼쪽으로 밀기)
                for (int i = 0; i < n; i++) {
                    for (int j = 1; j < n; j++) {
                        if (newBoard[i][j] == 0) continue;
                        int col = j;
                        while (col > 0 && newBoard[i][col - 1] == 0) {
                            newBoard[i][col - 1] = newBoard[i][col];
                            newBoard[i][col] = 0;
                            col--;
                        }
                        if (col > 0 && newBoard[i][col - 1] == newBoard[i][col] && !merged[i][col - 1]) {
                            newBoard[i][col - 1] *= 2;
                            newBoard[i][col] = 0;
                            merged[i][col - 1] = true;
                        }
                    }
                }
                break;
            case 3: // 우 (오른쪽으로 밀기)
                for (int i = 0; i < n; i++) {
                    for (int j = n - 2; j >= 0; j--) {
                        if (newBoard[i][j] == 0) continue;
                        int col = j;
                        while (col < n - 1 && newBoard[i][col + 1] == 0) {
                            newBoard[i][col + 1] = newBoard[i][col];
                            newBoard[i][col] = 0;
                            col++;
                        }
                        if (col < n - 1 && newBoard[i][col + 1] == newBoard[i][col] && !merged[i][col + 1]) {
                            newBoard[i][col + 1] *= 2;
                            newBoard[i][col] = 0;
                            merged[i][col + 1] = true;
                        }
                    }
                }
                break;
        }
        return newBoard;
    }

    // 보드에서 최댓값 찾기
    private static int findMax(int[][] board) {
        int max = -1;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j] > max) max = board[i][j];
            }
        }

        return max;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++){
            String[] strs = br.readLine().split(" ");
            for (int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(strs[j]);
            }
        }

        System.out.println(solution(board));
    }
}
