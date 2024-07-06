/* SCPC 1차 예선 2번 문항 */
/* 150 / 150 */

package programmers.scpc;

import java.util.Arrays;
import java.util.Scanner;

public class SCPC_배달 {
    static Long Answer;

    public static void main(String args[]) throws Exception	{
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
            /////////////////////////////////////////////////////////////////////////////////////////////
			int N = sc.nextInt();
            int[] arr = new int[N];

            for (int i = 0; i < N; i++){
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);

            int len = arr.length;
            int k = len / 4;

            for (int i = 0; i < k; i++){
                Answer += 2L * arr[len-1-i];
                Answer -= 2L * arr[i];
            }
            /////////////////////////////////////////////////////////////////////////////////////////////


            // Print the answer to standard output(screen).
            System.out.println("Case #"+(test_case+1));
            System.out.println(Answer);
        }
    }
}
