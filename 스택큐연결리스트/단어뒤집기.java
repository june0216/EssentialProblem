;
import java.util.*;
import java.io.*;
public class 단어뒤집기 {
    public static void main(String[] args) throws Exception{
        new 단어뒤집기().solution();
    }
    public static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int total = Integer.parseInt(br.readLine());

        while(total--> 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                result.append(new StringBuilder(st.nextToken()).reverse().toString()).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);

    }
}
