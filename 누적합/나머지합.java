import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 나머지합 {
    public static void main(String[] args) throws Exception{
        new 나머지합().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

         long presum[] = new long[N+1];
        st = new StringTokenizer(br.readLine());


        long cnt = 0;
        long remain[] = new long[M]; 
        remain[0] = 1; // 처음 [1, 1] 구간의 부분을 구하기 위해 
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= N; i++){
            long num = Long.parseLong(st.nextToken());
            presum[i] = (long) (num + presum[i-1]);
           

            // 나머지 정리 -> [ (presum[i] - presum[j])%M == 0 ] => [ presum[i]%M == presum[j])%M ] 


            // 음수일 때도 처리해야한다/ 

             int mod = (int) (presum[i] % M);
            
            if(mod <= -1) mod +=  M;
            cnt += remain[mod]; // 같은 나머지들끼리 쌍을 이루는 경우의 수 
            remain[mod]++; // 같은 나머지들 몇 개인지 세기 
           
            
        }

        System.out.println(cnt);
    }
}
