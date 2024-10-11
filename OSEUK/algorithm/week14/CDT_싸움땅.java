/**
 * CODE TREE 삼성 SW 역량테스트
 * 2022 하반기 오전 1번 문제
 * 문제 이름 : 싸움땅
 * 난이도 : GOLD II
 */

package codetree.samsung;
/*
싸움땅

n * n 크기의 격자에서 진행. 무기 들이 있음.
보드판에는 총과, 총의 공격력만 있음.
Player의 정보는 위치, 방향, 공격력을 포함함 . 초기에는 보드판의 0인 값으로 지정.

1. 본인이 향하고 있는 방향대로 한 칸 이동.
1-1. 범위를 벗어날 시 반대 방향  (d + 2) % 4;

2. 이동 후 처리
2- 1. 이동한 자리에 플레이어가 없다면, 총을 획득 or 교체.
2- 2. 이동한 자리에 플레이어가 있다면, 싸운다.
2-2-1. 싸우게 된다면, 능력치 + 총의 공격력을 바탕으로 두 명이 싸움.
2-2-2. 같으면, 능력치만 비교.
2-2-3. 같으면 2-2-1에서 비교한 능력치의 차이만큼 포인트를 획득.
2-3. 진 플레이어가 먼저 움직임. 총은 격자에 내려놓고, 가진 방향대로 움직임. 90도씩 이동. 총이 있다면 획득.
2-4. 이긴 플레이어는 자기 자리에서 가장 공격력이 높은 총을 획득.

K 라운드 동안 반복.

Block[][] 배열로 구성.
Block 객체에 넣을 것.
- 총과 각 총의 공격력, ArrayList<Gun> guns가 필요할듯.
- Player player -> null이면 빈칸이고, 있으면 걔랑 싸움.
- 플레이어가 있다면 싸우고 아니면 Pass하는 형식으로.

Player 객체
해당 플레이어의 좌표, 보고있는 방향, 능력치, 총이 있어야함.

1. 보고있는 방향 탐색
2. 만약 block[nx][ny].Player이 있다면 -> 싸움.
3. 움직인 사람이 지면, nx,ny 기준으로 탐색 후 이동
3-1. Player.Gun을 = 0으로 하고, 1번이랑 똑같이 진행하되, 벽이나 사람이 있으면 피함.
4. 움직인 사람이 이기면, block[nx][ny].Player 이동시키고, Player 좌표와 block[nx][ny] Player 갱신
4-1. 원래 있던 사람의 gun을 내려놓고, block 리스트에 추가하고 이동.

총은 공격력만 가지고 있으므로, Integer로 대체

*/
import java.util.*;
import java.io.*;

public class FightArea {
    private static final int MAX_N = 20;
    private static final int MAX_M = 30;
    private static final int DSIZE = 4;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    static int n, m, k;
    static Block[][] board = new Block[MAX_N + 1][MAX_N + 1];
    static Player[] players = new Player[MAX_M + 1];
    static int score = 0;

    static class Block{
        Player player;
        List<Integer> guns;

        public Block(){
            this.player = null;
            this.guns = new ArrayList<>();
        }
    }

    static class Player{
        int x;
        int y;
        int d;  // 방향
        int power;  // 기본 능력치
        int point = 0;
        int gun = 0; // 처음엔 총이 없음.

        public Player(int x, int y, int d, int power){
            this.x = x;
            this.y = y;
            this.d = d;
            this.power = power;
        }

        public Block run(){
            int nx = this.x + dx[this.d];
            int ny = this.y + dy[this.d];

            while (!inRange(nx, ny) || board[nx][ny].player != null){
                this.d = (this.d + 1) % DSIZE;

                nx = this.x + dx[this.d];
                ny = this.y + dy[this.d];
            }

            board[nx][ny].player = this;
            this.x = nx;
            this.y = ny;

            return board[nx][ny];
        }
    }

    // 범위 검사. 지고나서 움직일 때
    public static boolean inRange(int x, int y){
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    // 현재 칸과, 해당 플레이어를 인자로 넣어 총 교체 및 획득.
    public static void requireOrChangeGun(Block next, Player p){
        int nGun = p.gun;

        for (int gun : next.guns){
            if (gun > nGun){
                nGun = gun;
            }
        }

        if (nGun != p.gun){
            // 0이 아니면 교체임.
            if (p.gun != 0){
                next.guns.add(p.gun);
            }
            // 0이면 획득만 하게 됨.
            p.gun = nGun;
            next.guns.remove((Integer) nGun);
        }
    }

    // 두 플레이어를 입력받아 싸우게 한 후, 승자 반환
    public static Player fight(Player p1, Player p2){
        int p1Power = p1.power + p1.gun;
        int p2Power = p2.power + p2.gun;

        score = Math.abs(p1Power - p2Power);

        if (p1Power == p2Power){
            if (p1.power > p2.power) return p1;
            return p2;
        }
        else if (p1Power > p2Power) return p1;

        return p2;
    }
    // 플레이어 이동
    public static void move(Player p){
        // 보고있는 방향으로 이동.
        int nx = p.x + dx[p.d];
        int ny = p.y + dy[p.d];

        // 범위 벗어날 시 방향 돌려서 이동
        if (!inRange(nx, ny)){
            p.d = (p.d + 2) % DSIZE;
            nx = p.x + dx[p.d];
            ny = p.y + dy[p.d];
        }

        board[p.x][p.y].player = null;

        Block next = board[nx][ny];

        // 빈 칸이라면 총 선택하고 종료
        if (next.player == null){
            // 해당 칸으로 이동
            next.player = p;
            p.x = nx;
            p.y = ny;

            // 총 교체 및 획득 작업
            if (next.guns.size() != 0){
                requireOrChangeGun(next, p);
            }
        }
        else{
            // 승자와 패자 설정
            Player winner = fight(p, next.player);
            Player loser = (winner == p) ? next.player : p;

            // 승자가 자리를 먹음

            winner.x = nx;
            winner.y = ny;
            loser.x = nx;
            loser.y = ny;

            next.player = winner;
            winner.point += score;

            // 패자가 총을 갖고있었다면 내려놓고 떠난다.
            if (loser.gun != 0){
                next.guns.add(loser.gun);
                loser.gun = 0;
            }

            // 이동한 곳의 블럭을 입력받고, 총 획득
            Block b = loser.run();
            requireOrChangeGun(b, loser);

            // 승자는 해당 위치에서 총을 설정
            requireOrChangeGun(next, winner);
        }
    }

    // K 턴 동안 플레이
    public static void simulate(){
        while (k-- > 0){

            for (int i = 1; i <= m; i++){
                Player p = players[i];
                move(p);
            }

        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++ ){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++){
                board[i][j] = new Block();
                int num = Integer.parseInt(st.nextToken());
                if (num > 0){
                    board[i][j].guns.add(num);
                }
            }
        }

        for (int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());

            players[i] = new Player(x, y, d, power);
            board[x][y].player = players[i];
        }

        simulate();

        for (int i = 1; i <= m; i++){
            System.out.print(players[i].point + " ");
        }
    }
}
