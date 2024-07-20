package programmers.kit.dp;

public class GoToSchool {

    public final static int DIV = 1000000007; // 전역변수 선언
    public static int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        map[0][0] = 1;  //dp 초기값

        // 물 웅덩이를 -1로 설정
        for (int[] puddle : puddles) {
            map[puddle[1]-1][puddle[0]-1] = -1;
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                /* 물 웅덩이이면 해당 칸의 경우의 수는 0 */
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }

                /* 왼 칸과 위 칸의 값을 더함 */
                if (i > 0) map[i][j] += map[i-1][j] % DIV;
                if (j > 0) map[i][j] += map[i][j-1] % DIV;

                map[i][j] %= DIV;
            }
        }

        return map[n-1][m-1];

    }
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};

        System.out.println(solution(m, n, puddles));
    }
}
