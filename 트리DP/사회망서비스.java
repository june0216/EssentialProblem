import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 사회망서비스 {
	public static void main(String[] args) throws Exception{
		new 사회망서비스().solution();
	}

	int N;
	List<Integer>[] info;
	int[][] dp;
	boolean[] visited;
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		info =new List[N+1];
		dp = new int[N+1][2];
		for(int i = 0; i <= N; i++){
			info[i] = new ArrayList<>();
			//Arrays.fill(dp[i], Integer.MAX_VALUE);
			// min값을 찾아야 하는데 왜 최댓값을 세팅안해도 되는가?
		}
		StringTokenizer st;
		visited = new boolean[N+1];
		for(int i = 0; i < N-1; i++){
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			info[one].add(two);
			info[two].add(one);

		}


		dfs(1);
		// 단말 노드까지 찍고 올라가는 형태로 값을 업데이트하기 때문에

		System.out.println(Math.min(dp[1][0], dp[1][1]));




	}


	/*
	1
		2
			5
			6
		3
		4
	*/
	public void dfs(int start){
		//System.out.println(start);

		visited[start] = true;
		dp[start][1]=1;
		dp[start][0] = 0;
		for(int child : info[start]){
			if(visited[child]) continue; // 양방향으로 저장했지만 하나 방문하면 또 방문할 필요 없다.
			dfs(child);
			// System.out.println(child);
			dp[start][0] += dp[child][1]; // 얼리어답터 아닌 경우 반드시 자식은 얼리어닷터여야 한다.
			dp[start][1] += Math.min(dp[child][0], dp[child][1] ); // 얼리어닷터인 경우 상관없다.


		}
	}
}