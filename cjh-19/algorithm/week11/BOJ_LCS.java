import java.util.*;
import java.io.*;

public class Main {
    static String str1;
    static String str2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        str1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        str2 = st.nextToken();
        
        br.close();
    }

}