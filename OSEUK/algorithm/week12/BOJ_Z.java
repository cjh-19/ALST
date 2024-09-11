/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : Z
 * 문제 번호 : 1074
 * 난이도 : GOLD V.
 */
package baekjoon.dnc.gold;
import java.util.Scanner;

public class BOJ1074 {
    public static int divide_Z(int n, int r, int c, int num) {
        if (n == 0) return num;

        int half = 1 << (n - 1);  // 2^(n-1)
        int areaSize = half * half;  // 한 사분면의 크기

        // 1사분면
        if (r < half && c < half) {
            return divide_Z(n - 1, r, c, num);
        }
        // 2사분면
        else if (r < half && c >= half) {
            return divide_Z(n - 1, r, c - half, num + areaSize);
        }
        // 3사분면
        else if (r >= half && c < half) {
            return divide_Z(n - 1, r - half, c, num + 2 * areaSize);
        }
        // 4사분면
        else {
            return divide_Z(n - 1, r - half, c - half, num + 3 * areaSize);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        System.out.println(divide_Z(n, r, c, 0));
    }
}
