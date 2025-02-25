import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 점프점프 {
	public static void main(String[] args) throws Exception{
		new 점프점프().solution();
	}

	int N;
	int[] map;
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 징검다리 건너기 문제 느낌
		map  = new int[N];
		for(int i = 0; i< N; i++){
			map[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[100001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 0; i < N; i++){
			if(dp[i] == Integer.MAX_VALUE) continue;
			int min = dp[i]+1;
			for(int j = 1; j <= map[i]; j++ ){
				dp[i+j] = Math.min(min, dp[j+i]);
			}
		}

		if(dp[N-1] == Integer.MAX_VALUE) System.out.println(-1);
		else{
			System.out.println(dp[N-1]);
		}
	}
}
