/* SCPC 1차 예선 1번 문항 */
/* 100 / 100 */

package programmers.scpc;

import java.util.Scanner;

public class SCPC_A보다_B가_더_좋다 {
    static int Answer;

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
            Answer = 0;
            /////////////////////////////////////////////////////////////////////////////////////////////
			int N = sc.nextInt();   /* 문자열의 길이 입력 */
            String str = sc.next(); /* 문자열 입력 */

            int preA = 0;

            /* 첫 번째로 오는 A 탐색 */
            for (int i = 0; i < str.length(); i++){
                if (str.charAt(i) == 'A'){
                    preA = i;
                    break;
                }
            }

            /* A 사이마다 B 2개씩 채우도록 하는 카운트 추가 ex) ABBABBABBA */
            for (int i = preA + 1; i < str.length(); i++){
                if (str.charAt(i) == 'A'){
                    int gap = i - preA - 1;
                    if (gap == 0) Answer += 2;
                    if (gap == 1) Answer += 1;

                    preA = i;
                }
            }
            ///////////////////////////////////////////////////////////////////////////////////////////


            // Print the answer to standard output(screen).
            System.out.println("Case #"+(test_case+1));
            System.out.println(Answer);
        }
    }
}
