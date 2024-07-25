package programmers.kit.graph;


public class Rank {
    public static int solution(int n, int[][] results) {
        int[][] graph = new int[n+1][n+1];

        /* 승패 관계에서 이기면 1 지면 -1 */
        for (int[] result : results) {
            graph[result[0]][result[1]] = 1;
            graph[result[1]][result[0]] = -1;
        }

        /* 플로이드 와샬로 선수들의 승패 관계 알아내기 */
        for (int k = 1; k <= n; k++){   // 중간 정점
            for (int i = 1; i <= n; i++){   // 시작 정점
                for (int j = 1; j <= n; j++){   // 도착 정점
                    if (graph[i][k] == 1 && graph[k][j] == 1){
                        graph[i][j] = 1;
                        graph[j][i] = -1;
                    }
                    if (graph[i][k] == -1 && graph[k][j] == -1){
                        graph[i][j] = -1;
                        graph[j][i] = 1;
                    }
                }
            }
        }


        /* 갱신 후 자신과 모든 관계를 알 수 있으면 +1 */
        int answer = 0;
        for (int i = 1; i <= n; i++){
            boolean knowRank = true;
            for (int j = 1; j <= n; j++){
                if (i != j && graph[i][j] == 0){
                    knowRank = false;
                    break;
                }
            }
            if (knowRank) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {
                {4, 3},
                {4, 2},
                {3, 2},
                {1, 2},
                {2, 5}
        };

        System.out.println(solution(n, results));
    }
}
