/**
 * CODE TREE 삼성 SW 역량테스트
 * 2020 하반기 오전 1번 문제
 * 문제 이름 : 불안한 무빙워크
 * 난이도 : GOLD V
 */
package codetree.samsung;

import java.util.*;
import java.io.*;
public class MovingWalk {
    static int n; // 무빙워크의 길이
    static int k; // 실험을 종료하게 하는 안전성이 0인 판의 개수
    static Block[] movingWalk;
    static int firstBlockIdx = 0;

    // 무빙워크는 각 칸이 블록으로 구성됨
    static class Block{
        boolean isHuman = false;    // 사람이 타 있는가
        int stableScore;    // 해당 칸의 안정성

        public Block(int score){
            this.stableScore = score;
        }
    }

    public static void rotate(){
        // 인덱스를 이동해서 배열을 움직인 효과를 냄
        if (firstBlockIdx == 0){
            firstBlockIdx = 2 * n - 1;
        } else{
            firstBlockIdx--;
        }
    }

    public static void move(){
        // + (n - 1)을 더해서 위쪽 무빙워크를 바깥족에서 시작하도록
        int lastBlockIdx = (firstBlockIdx + n - 1) % (2 * n);

        // 회전한 후 마지막 칸에 사람이 있다면 false;
        if (movingWalk[lastBlockIdx].isHuman){
            movingWalk[lastBlockIdx].isHuman = false;
        }

        int i = (lastBlockIdx - 1 + (2 * n)) % (2 * n);
        // for문으로 할 시 종료타이밍이 애매하므로 while문으로
        while(true)
        {
            Block b = movingWalk[i];
            Block nb = movingWalk[(i + 1) % (2 * n)];

            if (b.isHuman){
                // 이동 가능하다면 이동
                if (!nb.isHuman && nb.stableScore != 0){
                    nb.stableScore--;
                    b.isHuman = false;

                    // 이동했는데 마지막 블록이면 사람을 이동시키지 않고 뺌
                    if ((i + 1) % (2 * n) != lastBlockIdx){
                        nb.isHuman = true;
                    }
                }
            }

            // last ~ first 위쪽 블록을 탐색 후 탈출
            if (i == firstBlockIdx) break;

            i = (i - 1 + (2 * n)) % (2 * n);
        }
    }

    public static void goNewPerson(){
        // 첫 칸에 사람이 없고 안정성이 0이 아니면 새로운 사람을 올림
        if (!movingWalk[firstBlockIdx].isHuman && movingWalk[firstBlockIdx].stableScore != 0){
            movingWalk[firstBlockIdx].isHuman = true;
            movingWalk[firstBlockIdx].stableScore--;
        }
    }
    // 한 턴을 실행하는 로직
    public static void run(){
        // 1. 무빙워크 한 칸 회전
        rotate();
        // 2. 무빙워크가 회전하는 방향으로 이동
        move();
        // 3. 새로운 사람 올림
        goNewPerson();
    }

    // 무빙워크가 안정적인 상태인지
    public static boolean checkStable(){
        int zeroCount = 0;

        // 각 블록의 안정성을 탐색
        for(Block b : movingWalk){
            if (b.stableScore == 0){
                zeroCount++;
            }
        }

        // 안정성이 0인 칸의 개수가 k개 이상이면 종료
        if (zeroCount >= k){
            return false;
        }

        return true;
    }

    public static int simulate(){
        int testCount = 1;
        while(true){
            run();

            // k개 이상 블록이 안정성이 0이면 종료
            if (!checkStable())
                return testCount;

            testCount++;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        movingWalk = new Block[2 * n];
        for (int i = 0; i < 2 * n; i++){
            movingWalk[i] = new Block(Integer.parseInt(st.nextToken()));
        }

        System.out.println(simulate());
    }
}
