/**
 * CODE TREE 삼성 SW 역량테스트
 * 2020 상반기 오후 1번 문제
 * 문제 이름 : 승자독식 모노폴리
 * 난이도 : GOLD II
 */

package codetree.samsung;

import java.util.*;
import java.io.*;

public class Monopoly {
    static int n, m, k; // 격자 크기, 플레이어 수, 지속 턴 수
    static Block[][] board; // 게임판
    static Player[] players;    // 플레이어
    static boolean[] isplayerDied;  // 플레이어 사망 여부
    // U, D, L, R (상 하 좌 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 블럭 객체.
    // 이 땅이 어떤 플레이어의 것인지, 몇 턴동안 지속되는지 여부를 판단
    static class Block{
        int playerNum;
        int remainingTurn;

        public Block(int playerNum, int turn){
            this.playerNum = playerNum;
            this.remainingTurn = turn;
        }
    }

    // 플레이어 객체.
    // 플레이어의 좌표와 현재 보고 있는 방향,
    // 플레이어가 보고있는 방향 마다 이동할 방향을 설정
    static class Player{
        int x, y;
        int currDir;
        int[][] nextDir;
    }

    // 범위 검사
    public static boolean inRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }


    // 0이 아닌 전체 땅 턴 감소
    // turn이 0이 되었다면 빈 땅으로 초기화
    public static void minusTurn(Block block){
        if (block.remainingTurn > 0)
            block.remainingTurn--;

        if (block.remainingTurn == 0){
            block.playerNum = 0;
        }
    }

    // 플레이어를 이동시킴.
    public static void move(){
        // 플레이어가 이동했는지 여부
        boolean[] isPlayerMoved = new boolean[m + 1];
        for (int i = 1; i <= m; i++){
            Player p = players[i];
            // 해당 플레이어가 죽은 상태라면, 이동하지 않음.
            if (isplayerDied[i]) continue;
            // 각 플레이어마다 현재 방향에서 정해진 방향 순서대로 탐색
            for (int next : p.nextDir[p.currDir]){
                int nx = p.x + dx[next];
                int ny = p.y + dy[next];

                // 빈칸이라면 바로 이동
                if (inRange(nx, ny) && board[nx][ny].playerNum == 0){
                    isPlayerMoved[i] = true;
                    p.currDir = next;
                    p.x = nx;
                    p.y = ny;
                    break;
                }
            }

            // 주변에서 빈칸을 찾지 못했다면, 재탐색하여 내가 독점한 땅으로 이동.
            if (!isPlayerMoved[i]){
                for (int next : p.nextDir[p.currDir]){
                    int nx = p.x + dx[next];
                    int ny = p.y + dy[next];

                    if (inRange(nx, ny) && board[nx][ny].playerNum == i){
                        isPlayerMoved[i] = true;
                        p.currDir = next;
                        p.x = nx;
                        p.y = ny;
                        break;
                    }
                }
            }
        }
    }

    //
    public static void checkAndRemove(){
        for (int i = 1; i <= m; i++){
            Player p = players[i];
            // 해당 플레이어가 죽은 상태라면, 이동하지 않음.
            if (isplayerDied[i]) continue;

            // 해당 플레이어가 새로 도착한 블럭에 대해서
            // 블럭 정보 갱신.
            // 보드는 n*n의 블럭으로 구성됨
            Block pos = board[p.x][p.y];
            // 빈 칸에 도착했다면 개척
            if (pos.playerNum == 0){
                pos.playerNum = i;
                pos.remainingTurn = k;
            }
            // 내가 이미 독점했던 땅이라면, 턴만 갱신
            else if (board[p.x][p.y].playerNum == i){
                pos.remainingTurn = k;
            }
            // 도착한 땅이, 숫자가 낮은 플레이어가 이미 도착한 땅이라면
            // 사망
            else{
                isplayerDied[i] = true;
            }
        }
    }
    public static int simulate(){
        // 플레이어가 살아있는지 여부
        isplayerDied = new boolean[m + 1];
        int turns = 1;
        while (true){
            // 이동
            move();

            // 이동 후 턴 갱신
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    minusTurn(board[i][j]);
                }
            }

            // 만약 사람이 겹쳐있다면, 한명만 남기고 제거
            // 이전 턴을 갱신했으므로 이번 턴에 밟은 지역 갱신
            checkAndRemove();

            // 살아있는 플레이어의 정보 갱신
            int aliveCount = 0;
            for (int i = 1; i <= m; i++){
                if (!isplayerDied[i]) aliveCount++;
            }

            // 만약 1번만 살아있다면 현재 턴 반환
            if (aliveCount == 1){
                return turns;
            }

            // 만약 1000번 넘게 진행되면 종료 . -1반환
            if( turns >= 1000){
                break;
            }

            turns++;
        }

        return -1;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 맵, 플레이어 초기화
        board = new Block[n][n];
        players = new Player[m + 1];
        for (int i = 0; i <= m ; i++){
            players[i] = new Player();
        }

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                if (num == 0){
                    board[i][j] = new Block(0, 0);
                }
                // 해당 블럭에 플레이어가 있다면
                // 블럭 점유 및 턴 갱신
                else {
                    players[num].x = i;
                    players[num].y = j;
                    board[i][j] = new Block(num, k);
                }
            }
        }


        st = new StringTokenizer(br.readLine());
        for (int i = 1 ; i <= m; i++){
            // 각 플레이어마다 보고있는 방향 설정
            players[i].currDir = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i <= m; i++){
            // 4개의 방향을 바라볼 때의 진행 방향 우선순위 설정
            players[i].nextDir = new int[4][4];
            for (int j = 0; j < 4; j++){
                st = new StringTokenizer(br.readLine());
                for (int next = 0; next < 4; next++){
                    // 각 플레이어가 보고 있는 방향마다 이동할 방향 설정
                    players[i].nextDir[j][next] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        System.out.println(simulate());
    }
}
