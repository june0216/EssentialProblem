import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 로봇조종하기 {
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for(int i = 0; i < N ;i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M ;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		int[][] dp =new int[N][M];
		dp[0][0] = map[0][0];
		// 먼저, 맨 위는 다 갈 수 있음
		for(int i = 1; i < M ;i++){
			dp[0][i] = map[0][i] + dp[0][i-1];
		}

		// 2차원 배열에서 2개를 고려해야함
		int[][] tempSize2 = new int[2][M];
		for(int i = 1; i < N; i++){

			// 위에서의 합과 지금 처음 위치의 합
			//tempSize2[0][0]는 위쪽에서만 올 수 있음
			tempSize2[0][0] = dp[i-1][0] + map[i][0];
			// 위랑 왼쪽 비교
			for(int j = 1 ; j < M; j++){
				tempSize2[0][j] = Math.max(dp[i-1][j], tempSize2[0][j-1]) + map[i][j];
			}

			tempSize2[1][M-1] = dp[i-1][M-1] + map[i][M-1];
			// 위랑 오른쪽 비교
			for(int j = M-2; j >=0 ; j--){
				tempSize2[1][j] = Math.max(tempSize2[1][j+1], dp[i-1][j]) + map[i][j];
			}

			for(int j = 0; j < M; j++){
				dp[i][j] = Math.max(tempSize2[0][j], tempSize2[1][j]);
			}
		}

		System.out.println(dp[N-1][M-1]);

	}
}