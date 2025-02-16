import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 내려가기 {
	public static void main(String[] args) throws Exception{
		new 내려가기().solution();
	}

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][3];
		for(int i = 0;  i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dp = new int[3];
		int[] mindp = new int[3];
		dp[0] = map[0][0];
		dp[1] = map[0][1];
		dp[2] = map[0][2];
		mindp[0] = map[0][0];
		mindp[1] = map[0][1];
		mindp[2] = map[0][2];

		for(int i =1; i < N; i++){
			int tmp1  =dp[0];
			int tmp2 = dp[1];
			int tmp3 = dp[2];
			dp[0] = Math.max(dp[0], dp[1]) + map[i][0];
			dp[1] = Math.max(dp[2], Math.max(tmp1, dp[1])) + map[i][1];
			dp[2] = Math.max(tmp2, dp[2]) + map[i][2];


			tmp1 = mindp[0];
			tmp2 = mindp[1];
			tmp3 = mindp[2];

			mindp[0] = Math.min(tmp2, tmp1) + map[i][0];
			mindp[1] = Math.min(tmp3, Math.min(tmp1, tmp2)) + map[i][1];
			mindp[2] = Math.min(tmp2, tmp3) + map[i][2];
		}

		int max = 0;
		int min = Integer.MAX_VALUE;
		max = Math.max(dp[2], Math.max(dp[0], dp[1]));
		min = Math.min(mindp[2], Math.min(mindp[1], mindp[0]));

		System.out.println(max + " " + min);


	}
}