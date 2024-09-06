/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 배열 합치기
 * 문제 번호 : 11728
 * 난이도 : SILVER V
 */
package baekjoon.dnc.silver;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ11728 {
    public static void main(String[] args) throws IOException {
        // 시간 제한이 빡세서 br에 bw까지 써야 통과됨 ;;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());

        int[] A = new int[sizeA];
        int[] B = new int[sizeB];

        st= new StringTokenizer(br.readLine());
        for (int i = 0; i < sizeA; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sizeB; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        int pa = 0, pb = 0, idx = 0;
        int[] result = new int[sizeA + sizeB];

        while (pa < sizeA || pb < sizeB){
            if (pa >= sizeA) {
                result[idx++] = B[pb++];
                continue;
            }
            if (pb >= sizeB) {
                result[idx++] = A[pa++];
                continue;
            }

            if (A[pa] > B[pb])  {
                result[idx++] = B[pb++];
            }
            else{
                result[idx++] = A[pa++];
            }
        }

        for (int res : result) {
            bw.write(res + " ");
        }

        bw.flush();  // BufferedWriter에 저장된 내용을 한 번에 출력
        bw.close();
    }
}
