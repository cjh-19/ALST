package codetree.samsung;

import java.util.*;
import java.io.*;

public class TurretBreak {
    private static final int MAX_N = 10;
    // 우 하 좌 상
    private static final int[] DX = {0, 1, 0, -1};
    private static final int[] DY = {1, 0, -1, 0};

    static int N, M, K;
    static Turret[][] board = new Turret[MAX_N][MAX_N];   // 각 포지션에는 공격력이 있다.
    static Turret strongestTurret = new Turret(new Pair(MAX_N, MAX_N), Integer.MIN_VALUE, true);
    static Turret weekestTurret = new Turret(new Pair(MAX_N, MAX_N), Integer.MAX_VALUE, true);

    static class Pair{
        int x;
        int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Turret{
        // 각 포탑의 위치, 공격력, 파괴여부
        Pair pos;
        int power;
        boolean isBroken;

        int lastAttackTime = 0; // 몇 턴 전에 사용됐는지

        public Turret(Pair pos, int power, boolean isBroken){
            this.pos = pos;
            this.power = power;
            this.isBroken = isBroken;
        }

    }
    // fix
    public static void setStrongestTurret(){
        // 가장 강한 포탑 설정
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                // 부서진 포탑은 탐색하지 않음
                if (board[i][j].isBroken) continue;
                // 공격력이 가장 높은지
                if (board[i][j].power > strongestTurret.power){
                    strongestTurret = board[i][j];
                }
                // 가장 공격한지 오래됐는지
                else if (board[i][j].power == strongestTurret.power
                        && board[i][j].lastAttackTime > strongestTurret.lastAttackTime){
                    strongestTurret = board[i][j];
                }
                // 행과 열의 합이 가장 작은지
                else if (board[i][j].power == strongestTurret.power
                        && board[i][j].lastAttackTime == strongestTurret.lastAttackTime
                        && i + j < strongestTurret.pos.x + strongestTurret.pos.y){
                    strongestTurret = board[i][j];
                }
                // 열 값이 가장 작은지
                else if (board[i][j].power == strongestTurret.power
                        && board[i][j].lastAttackTime == strongestTurret.lastAttackTime
                        && i + j == strongestTurret.pos.x + strongestTurret.pos.y
                        && j < strongestTurret.pos.y){

                    strongestTurret = board[i][j];
                }
            }
        }
    }

    // fix
    public static void setWeakestTurret(){
        // 가장 약한 포탑 설정
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                // 부서진 포탑은 탐색하지 않음
                if (board[i][j].isBroken) continue;
                // 공격력이 가장 낮은지
                if (board[i][j].power < weekestTurret.power){
                    weekestTurret = board[i][j];

                }
                // 가장 최근에 공격했는지
                else if (board[i][j].power == weekestTurret.power
                        && board[i][j].lastAttackTime < weekestTurret.lastAttackTime){
                    weekestTurret = board[i][j];
                }
                // 행과 열의 합이 가장 큰 지
                else if (board[i][j].power == weekestTurret.power
                        && board[i][j].lastAttackTime == weekestTurret.lastAttackTime
                        && i + j > weekestTurret.pos.x + weekestTurret.pos.y ){
                    weekestTurret = board[i][j];
                }
                // 열 값이 가장 큰지
                else if (board[i][j].power == weekestTurret.power
                        && board[i][j].lastAttackTime == weekestTurret.lastAttackTime
                        && i + j == weekestTurret.pos.x + weekestTurret.pos.y
                        && j > weekestTurret.pos.y){
                    weekestTurret = board[i][j];
                }
            }
        }

    }

    public static boolean[][] attackByLazer(Turret[][] prev){

        int attackPower = weekestTurret.power;
        boolean[][] isAttacked = new boolean[MAX_N][MAX_N];

        // System.out.println(attackPower);
        // 가장 강한 터렛이 먼저 공격받음
        if (strongestTurret.power <= attackPower){
            strongestTurret.power = 0;
            strongestTurret.isBroken = true;
        } else {
            strongestTurret.power -= attackPower;
        }

        Pair currPos = strongestTurret.pos;
        isAttacked[currPos.x][currPos.y] = true;

        while (true){
            // 역추적하여 하나하나씩 절반만큼 공격
            Turret nt = prev[currPos.x][currPos.y];
            // System.out.println(nt.pos.x + " " + nt.pos.y);
            // 출발지로 돌아왔다면 패스 ( 공격받지 않음)
            if (nt == weekestTurret) break;

            if (nt.power <= attackPower / 2){
                nt.power = 0;
                nt.isBroken = true;
            }
            else{
                nt.power -= attackPower / 2;

            }
            isAttacked[nt.pos.x][nt.pos.y] = true;

            currPos = nt.pos;
        }

        return isAttacked;
    }

    public static boolean[][] attackByBomb(){
        boolean[][] isAttacked = new boolean[MAX_N][MAX_N];

        // 가장 강한 포탑 먼저 공격받음
        int attackPower = weekestTurret.power;
        if (strongestTurret.power <= attackPower){
            strongestTurret.power = 0;
            strongestTurret.isBroken = true;
        } else {
            strongestTurret.power -= attackPower;
        }

        Pair currPos = strongestTurret.pos;
        isAttacked[currPos.x][currPos.y] = true;

        Turret ct = strongestTurret;

        // 주변 8개의 포탑에 전부 공격을 진행함.
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) continue;

                Turret nt = board[(ct.pos.x + i + N) % N ][(ct.pos.y + j + M) % M ];

                // 이미 부서지거나 공격포탑은 제외
                if (nt.isBroken || nt == weekestTurret) continue;

                nt.power -= attackPower / 2;

                if (nt.power <= 0){
                    nt.power = 0;
                    nt.isBroken = true;
                }

                isAttacked[nt.pos.x][nt.pos.y] = true;
            }
        }

        return isAttacked;
    }

    public static boolean[][] attack(){
        Queue<Turret> q = new LinkedList<>();
        Turret[][] prev = new Turret[MAX_N][MAX_N];
        boolean[][] visited = new boolean[MAX_N][MAX_N];

        q.add(weekestTurret);
        prev[weekestTurret.pos.x][weekestTurret.pos.y] = weekestTurret;
        visited[weekestTurret.pos.x][weekestTurret.pos.y] = true;

        boolean isAttackedLazer = false;

        while (!q.isEmpty()){
            Turret t = q.poll();

            if (t == strongestTurret){
                isAttackedLazer = true;
                break;
            }
            int cx = t.pos.x;
            int cy = t.pos.y;

            for (int i = 0; i < 4; i++){
                int nx = (cx + DX[i] + N) % N;
                int ny = (cy + DY[i] + M) % M;

                if (board[nx][ny].isBroken || visited[nx][ny]) continue;

                q.offer(board[nx][ny]);
                prev[nx][ny] = t;
                visited[nx][ny] = true;
            }
        }

        boolean[][] isAttacked;
        if (isAttackedLazer){
            isAttacked = attackByLazer(prev);
        } else{
            isAttacked = attackByBomb();
        }

        // 공격과 관련있던 지역 여부
        return isAttacked;
    }

    // 공격과 관련 없던 포탑은 수리
    public static void fixTurrets(boolean[][] isAttacked){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (isAttacked[i][j] || board[i][j].isBroken) continue;
                if (i == weekestTurret.pos.x && j == weekestTurret.pos.y) continue;

                board[i][j].power++;
            }
        }
    }

    // 시간을 경과시킴. 이 과정에서 부서지지 않은 포탑이 하나라면, 종료
    public static boolean updateAttackTime(){
        int notBrokenCount = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (board[i][j].isBroken){
                    continue;
                }

                if (board[i][j] == weekestTurret){
                    notBrokenCount++;
                    board[i][j].lastAttackTime = 0;
                    continue;
                }

                notBrokenCount++;
                board[i][j].lastAttackTime += 1;
            }
        }

        if (notBrokenCount == 1){
            return false;
        }

        return true;
    }
    public static boolean simulate(){
        setWeakestTurret(); // 가장 약한 포탑 설정


        setStrongestTurret();// 가장 강한 포탑 설정


        weekestTurret.power += N + M;

        // 공격
        boolean[][] isAttacked = attack();

        // for (int i = 0; i < N; i++){
        //     for (int j = 0; j < M; j++){
        //         System.out.print(board[i][j].power + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println();

        // 포탑 정비
        fixTurrets(isAttacked);

        // for (int i = 0; i < N; i++){
        //     for (int j = 0; j < M; j++){
        //         System.out.print(board[i][j].power + " ");
        //     }
        //     System.out.println();
        // }

        // System.out.println();

        if(!updateAttackTime()) return false;

        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                int num = Integer.parseInt(st.nextToken());
                if (num == 0){
                    board[i][j] = new Turret(new Pair(i, j), num, true);
                } else {
                    board[i][j] = new Turret(new Pair(i, j), num, false);
                }
            }
        }

        while (K-- > 0){
            // 공격
            if(!simulate()) break;


        }

        strongestTurret = new Turret(new Pair(MAX_N, MAX_N), Integer.MIN_VALUE, true);
        setStrongestTurret();

        // 남아있는 포탑 중 가장 강한 포탑의 공격력을 출력
        System.out.println(strongestTurret.power);
    }
}
