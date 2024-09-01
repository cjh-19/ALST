/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 병든 나이트
 * 문제 번호 : 1783
 * 난이도 : SILVER III
 */
package baekjoon.greedy.silver;

import java.util.Scanner;

public class BOJ1783 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int answer = 1;
        if (N == 1 || M == 1) System.out.println(answer);
        else if (N == 2){
            answer = answer + ((M-1) / 2);
            if (answer > 4) System.out.println(4);
            else System.out.println(answer);
        }
        else {
            if (M <= 4){
                answer = M;
            } else if( M <= 6){
                answer = 4;
            } else {
                answer = M - 2;
            }

            System.out.println(answer);
        }
    }
}
