import java.util.*;
import java.lang.*;
import java.io.*;

public class 퇴사 {

	public static void main(String[] args) throws Exception{
		new 퇴사().solution();

	}


	int N;
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] costs = new int[N+2];
		int[] days = new int[N+2];
		StringTokenizer st;
		for(int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			costs[i] = cost;
			days[i] = day;


		}

		int[] dp = new int[N+2];
		int result = 0;

		for(int i = 1; i <= N; i++){

			if(i+days[i]-1 <= N){
				// 지금 일 시작해서 벌 수 있는 돈 vs 그 날짜에 이미 벌 수 있는 돈
				dp[i+days[i]-1] = Math.max(dp[i-1]+costs[i], dp[i+days[i]-1]);

			}

			// 다음 날에게 자신과 그 다음날 중 큰 값을 토스
			//dp[i+1] = Math.max(dp[i+1], dp[i]);
			// 현재 vs 다음날 // 전날 vs 다음날
			dp[i] = Math.max(dp[i-1], dp[i]);
			result = Math.max(dp[i], result);


		}




        /*
        0 0 10 20 20 30
            */
		System.out.println(result);
	}


}
