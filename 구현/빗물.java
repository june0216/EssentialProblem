import java.util.*;
import java.io.*;

public class 빗물 {
    public static void main(String[] args) throws Exception{
        new 빗물().solution();
    }

    int H;
    int W;
    int wall[];

    public void solution() throws Exception{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        wall = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ;i< W; i++){
            wall[i] = Integer.parseInt(st.nextToken());
        }
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> lower = new ArrayDeque<>();
        int result = 0;
        int start = wall[0];
        for(int i = 0; i < W; i++){
            

        }


        System.out.println(result);
    }
}
