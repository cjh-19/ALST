/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 가장 긴 증가하는 부분 수열 2
 * 문제 번호 : 12015
 * 난이도 : GOLD II
 */


package baekjoon.greedy.gold;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ12015 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        ArrayList<Integer> lis = new ArrayList<>();

        for (int i = 0; i < n; i++){
            int num = arr[i];

            int pos = Collections.binarySearch(lis, num);

            if (pos < 0){
                pos = -pos - 1;
            }

            if (pos < lis.size()){
                lis.set(pos, num);
            } else{
                lis.add(num);
            }
        }

        System.out.println(lis.size());
    }
}

