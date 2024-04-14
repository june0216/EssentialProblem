import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 숫자조합으로수만들기2 {

	public static void main(String[] args) throws Exception{
		new 숫자조합으로수만들기2().solution();
	}
	int N;
	int[][] dp;
	static final int MAX = 10000;

	// 조합 -> 중복 불가 -> 오름차순으로 하기 -> 뒷자리를 검사함
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		dp = new int[MAX][3];
		dp[1][1] = 1;

		dp[2][1] = 1;
		dp[2][2] = 1;

		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3]= 1;
		for(int i = 4; i <= MAX; i++){
			dp[i][1] = dp[i-1][1]; // 1로 끝나는 경우
			dp[i][2] = dp[i-2][1] + dp[i-2][2]; // 2로 끝나는 경우
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3]; // 3으로 끝나는 경우
		}

		for(int i = 0; i < N; i++){
			int num = Integer.parseInt(br.readLine());
			sb.append(dp[num-1][1] + dp[num-1][2]+ dp[num-1][3]).append("\n");
		}
		System.out.println(sb);

	}


}
