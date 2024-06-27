import java.io.*;
import java.util.*;

public class 우물안개구리 {
    public static void main(String[] args) throws Exception{
        new 우물안개구리().solution();
    }
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] weight = new long[N+1];
        int[] lose = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N; i++){
            weight[i] = Integer.parseInt(st.nextToken());

        }

        for(int i = 0; i < M;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            if(weight[num1] > weight[num2]){
                lose[num2]++;
            }else if(weight[num1] < weight[num2]){
                lose[num1]++;
            }else{ // 같으면 둘 다 진 것으로 간주
                lose[num1]++;
                lose[num2]++;
            }
        }

        int res = 0;
        for(int i = 1 ; i <= N; i++){
            if(lose[i] == 0) res++; // 져본적 없는 사람들을 카운트

        }
        System.out.println(res);
    }


}
