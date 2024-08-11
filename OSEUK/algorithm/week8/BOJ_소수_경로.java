/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 소수 경로
 * 문제 번호 : 1963
 * 난이도 : GOLD IV
 */
package baekjoon.bfs.gold;

import java.util.*;

public class BOJ1963 {
    final static int N = 10000;
    static boolean[] isPrime;
    public static int solution(int A, int B){
        if (A == B) return 0;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        q.add(A);
        visited[A] = true;
        int count = 0;

        while (!q.isEmpty()){
            int size = q.size();
            for (int s =  0; s < size; s++){

                int num = q.poll();

                if (num == B) return count;

                for (int i = 0; i < 4; i++){
                    char[] charNum = String.valueOf(num).toCharArray();
                    for (char c = '0'; c <= '9'; c++){
                        if (i == 0 && c == '0') continue;

                        char olddigit = charNum[i];
                        charNum[i] = c;
                        int next = Integer.parseInt(String.valueOf(charNum));

                        if (!visited[next] && isPrime[next]){
                            q.add(next);
                            visited[next] = true;
                        }
                        charNum[i] = olddigit;
                    }
                }
            }

            count++;

        }
        return -1;

    }

    public static void main(String[] args) {
        isPrime = eratosthenes();

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 0; test_case < T; test_case++){
            int A = sc.nextInt();
            int B = sc.nextInt();

            int res = solution(A, B);

            if (res == -1) System.out.println("Impossible");
            else System.out.println(res);
        }
    }

    /* 에라토스테네스의 체 */
    public static boolean[] eratosthenes(){
        boolean[] isPrime = new boolean[N];

        for (int i = 2; i < N; i++){
            isPrime[i] = true;
        }

        for(int i = 2; i <= Math.sqrt(N); i++){
            if (isPrime[i]){
                for (int j = i * i; j < N; j += i){
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;

    }

}
