/* SCPC 1차 예선 3번 문항 */
/* 58 / 200 */

package programmers.scpc;

import java.util.ArrayList;
import java.util.Scanner;

public class SCPC_보안망 {
    static Long Answer;
    static int Number;

    public static void main(String[] args) throws Exception	{
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */

		/*
		   Make new scanner from standard input System.in, and read data.
		 */
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new FileInputStream("input.txt"));

        int T = sc.nextInt();

        for(int test_case = 0; test_case < T; test_case++) {

            Answer = 0L;
            Number = 0;
            /////////////////////////////////////////////////////////////////////////////////////////////
			int N = sc.nextInt();
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < N; i++){
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < N+1; i++){
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            int s = -1;
            int e = -1;

            for (int i = 0; i < N; i++){
                if (graph.get(i).size() == 3)
                    if (s == -1)
                        s = i;
                    else e = i;
            }

            SCPC_보안망 cs = new SCPC_보안망();
            boolean[] visited = new boolean[N];

            cs.dfs(graph, s, e, 1, visited);

            Answer = cs.calc(Number - 1) + cs.calc(N - Number + 1);

            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            System.out.println("Case #"+(test_case+1));
            System.out.println(Answer);
        }
    }

    private long calc(long n){
        if (n < 2) return 0;
        return n * (n-1) / 2;
    }


    private void dfs(ArrayList<ArrayList<Integer>> graph, int s, int e, int count, boolean[] visited){
        visited[s] = true;
        if (s == e) {
            Number = count;
            return;
        }

        ArrayList<Integer> curr_node = graph.get(s);
        for (Integer neighbor : curr_node) {
            if (neighbor == e && count == 1) continue;

            if (!visited[neighbor]) {
                dfs(graph, neighbor, e, count + 1, visited);
            }

            if (Number != 0) return; // 사이클을 찾은 경우 종료

        }
    }

}
