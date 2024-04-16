import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 크리보드 {
	public static void main(String[] args) throws Exception{
		new 크리보드().solution();
	}

	long[] dp;
	int N;
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N  = Integer.parseInt(br.readLine());
		dp = new long[N+1];
		dp[1] = 1;
		int cnt = N;
		for(int i =1 ; i < N+1; i++){
			if(i <= 6){
				dp[i] = dp[i-1] +1; // 6까지는 단순 출력이 더 빠름
				continue;
			}
			for(int j = 3; j <= 5; j++){
				dp[i] = Math.max(dp[i-j]*(j-1) , dp[i]);
			}
		}
		System.out.println(dp[N]);
	}
}
