import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 퇴사2 {
    public static void main(String[] args) throws Exception{
        new 퇴사2().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        //StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[N+2];
        int[] time = new int[N+2];
        int[] cost= new int[N+2];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }


        int max = 0;
        // 시간이 기준이 아니라 cost가 기준 
        // dp 배열 => 해당 시간까지의 최대 비용 
        for(int i = 1; i <= N+1; i++){
            //전이
            dp[i] = Math.max(dp[i], dp[i-1]);

            if(i+time[i] > N+1) continue;
        
            
            dp[i+time[i]] = Math.max(dp[i+time[i]] , dp[i]+ cost[i] );
            
        
            
            
        }
        dp[N+1] = Math.max(dp[N+1], dp[N]); 

        System.out.println(dp[N+1]);

        

        
    }
}
