import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*

판다가 가장 오래 살 수 있는(최대로 이동할 수 있는) 날짜를 구하는 문제.
항상 증가하는 방향으로만 이동하므로 다시 돌아올 일이 없음.
 */

public class 욕심쟁이판다 {
	public static void main(String[] args) throws Exception {
		new 욕심쟁이판다().solution();
	}

	int N;
	int[][] dp;
	int[][] map;
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		map = new int[N][N];
		dp = new int[N][N];
		for(int i = 0 ; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[i], Integer.MIN_VALUE);
		}

		int max = 0 ;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				int num = dfs(j, i);
				///System.out.println(num);
				max = Math.max(num, max);
			}
		}




		System.out.println(max);


	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public int dfs(int x, int y){
		if(dp[y][x] != Integer.MIN_VALUE){
			return dp[y][x];
		}
		dp[y][x] = 1;
		for(int i = 0; i <4; i++ ){
			int ny = dy[i] + y;
			int nx = dx[i] + x;
			if(nx >=0 && ny >= 0 && ny < N && nx < N){
				if(map[ny][nx] > map[y][x]){ // 오름차순이어야 함
					dp[y][x] = Math.max(dfs(nx, ny) + 1, dp[y][x]);


				}


			}
		}
		return dp[y][x];


	}
}
