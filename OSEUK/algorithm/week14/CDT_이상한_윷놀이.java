/**
 * CODE TREE 삼성 SW 역량테스트
 * 2019 하반기 오전 2번 문제
 * 문제 이름 : 이상한 윷놀이
 * 난이도 : GOLD III
 */
package codetree.samsung;

import java.util.*;
import java.io.*;
public class StrangeYutGame {
    static int n, k;    // 판의 크기 n. 말의 개수 k
    static int[][] board;   // 윷놀이 판
    static Yut[] yuts;

    // 윷 클래스 정의
    static class Yut{
        // 1, 2, 3, 4 -> 우, 좌, 상 하
        final int[] dx = {0, 0, 0, -1, 1};
        final int[] dy = {0, 1, -1, 0, 0};

        int x;
        int y;
        int direction;

        Yut carriedYut = null;  // 내 위에 있는 윷
        Yut baseYut = null;     // 내 밑에 있는 윷

        // 생성자
        public Yut(int x, int y, int direction){
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        // 말 하나를 이동하는 작업
        public boolean start(){
            int nx = this.x + dx[this.direction];
            int ny = this.y + dy[this.direction];

            // board[nx][ny] 값에 따라
            // 0 이면 흰색
            // 1 이면 빨간색
            // 2 이면 파란색

            Yut y = this.emptyHere(nx, ny); // 다음칸의 말 상태 찾기

            // 다음 칸이 흰색이라면
            if (inRange(nx, ny) && board[nx][ny] == 0)
            {
                // 기존 연결 해제
                if (this.baseYut != null) {
                    this.baseYut.carriedYut = null;
                }
                this.baseYut = null;

                // 다음 칸에 말이 있다면, 상단의 말과 이동할 말과 연결 생성
                if (y != null){
                    y.carriedYut = this;
                    this.baseYut = y;
                }

                // 이동
                this.move(this.direction);


            }
            // 다음 칸이 빨간 색이라면
            if (inRange(nx, ny) && board[nx][ny] == 1){
                if (this.baseYut != null) {
                    this.baseYut.carriedYut = null;
                }
                this.baseYut = null;

                // 해당 위 칸으로 연결구조를 뒤집음
                this.flip();

                // 연결구조가 바뀌었으므로, flip한 윷들 중 가장 밑으로 이동
                Yut yy = this.toBottom();

                // 다음 칸과의 연결관계 설정
                if (y != null){
                    y.carriedYut = yy;
                    yy.baseYut = y;
                }

                // 이동
                yy.move(this.direction);
            }

            // 다음 칸이 벽이거나 파란색이면
            if (!inRange(nx, ny) || board[nx][ny] == 2){
                // 방향을 반대로 바꿈
                this.changeDirection();
                nx = this.x + dx[this.direction];
                ny = this.y + dy[this.direction];

                // 바꾸고 이동할 공간이 여전히 파란색, 벽이라면, 움직이지 않고 종료
                if (!inRange(nx, ny) || board[nx][ny] == 2){
                    return true;
                }

                // 흰/빨간이면 그쪽으로 이동할 수 있도록.
                this.start();
            }

            // 움직이면서 새로운 연결관계가 생겼으므로 가장 밑으로 이동
            Yut yut = this.toBottom();

            // 가장 밑에서 업은 말의 개수를 세는 함수로, 4개 이상일 경우 false 반환.
            if (yut.countCarry(1) >= 4){
                return false;
            }

            return true;
        }
        // 범위 검사
        public boolean inRange(int x, int y){
            return x >= 0 && x < n && y >= 0 && y < n;
        }

        // 해당 칸에서 가장 위/밑으로 가는 함수
        public Yut toTop(){
            if (this.carriedYut == null){
                return this;
            }
            else{
                return this.carriedYut.toTop();
            }
        }
        public Yut toBottom(){
            if (this.baseYut == null){
                return this;
            }
            else{
                return this.baseYut.toBottom();
            }
        }

        // 해당 칸에 있는 말의 수를 셈
        public int countCarry(int cnt) {
            // 현재 윷에 업혀 있는 윷이 있는지 확인
            if (this.carriedYut != null) {
                // 업혀 있는 윷이 있다면, 재귀적으로 개수 추가
                return this.carriedYut.countCarry(cnt + 1);
            } else {
                // 업혀 있는 윷이 없으면 현재까지의 개수를 반환
                return cnt;
            }
        }

        // 내가 이동하고자 하는 칸이 비어있는지 확인.
        // 만약 차 있다면, 업혀있을 수도 있으니 가장 상단의 말을 반환
        public Yut emptyHere(int x, int y){
            for (int i = 1; i <= k; i++){
                if (yuts[i].x == x && yuts[i].y == y){
                    Yut yy = yuts[i].toTop();
                    return yy;
                }
            }

            return null;
        }

        // 방향 반대로 변경
        public void changeDirection(){
            if (this.direction == 1){
                this.direction = 2;
            }
            else if (this.direction == 2){
                this.direction = 1;
            }
            else if (this.direction == 3){
                this.direction = 4;
            }
            else {
                this.direction = 3;
            }
        }

        // 업고있는 연결관계 변경
        public void flip(){
            // 내 위로 업고 있는 윷들도 바꾸기
            if (this.carriedYut != null){
                this.carriedYut.flip();
            }

            Yut temp = this.carriedYut;
            this.carriedYut = this.baseYut;
            this.baseYut = temp;
        }

        // 윷 움직임. 업고있는 것까지 움직이도록
        public void move(int dir){

            this.x = this.x + dx[dir];
            this.y = this.y + dy[dir];

            if (this.carriedYut != null){
                this.carriedYut.move(dir);
            }
        }
    }

    public static int simulate(){
        int turns = 0;
        while (true){
            turns++;

            // 각 윷을 이동시키며 모든 윷 시행
            for (int i = 1; i <= k; i++){
                if(!(yuts[i].start()))  // 해당 말이 이동한 칸의 말 개수가 4 이상이면 종료
                    return turns;
            }

            // 턴을 1000번 이상 사용할 시
            if (turns > 1000) break;
        }

        return -1;

    }

    public static void main(String[] args) throws IOException{
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 게임판 설정
        board = new int[n][n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 윷 객체의 좌표와 이동하는 방향 설정
        yuts = new Yut[k + 1];
        for (int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            yuts[i + 1] = new Yut(x - 1, y - 1, direction);
        }

        System.out.println(simulate());


    }
}
