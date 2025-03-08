import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 사회망서비스2 {
	public static void main(String[] args) throws Exception{
		new 사회망서비스2().solution();
	}


	int N;
	List<Integer>[] tree;
	int[][] dp;
	public void solution() throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new List[N+1];
		for(int i= 0; i < N+1; i++){
			tree[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for(int i = 0; i < N-1; i++){
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			tree[num1].add(num2);
			tree[num2].add(num1);
		}
		dp = new int[N+1][2];
		visited = new boolean[N+1];
		visited[1] = true;
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));



	}

	// 우수마을과 비슷한 문제
	boolean[] visited;
	public void dfs(int start){
		dp[start][1] =1;

		visited[start] = true;
		for(int next : tree[start]){
			if(visited[next]) continue;

			dfs(next);
			// 부모이 얼리어답터 아니라면 자식은 꼭 얼리어답터여야함
			dp[start][0] += dp[next][1];

			// 부모이 얼리어답터라면 자식은 아니거다 이어도 됨
			dp[start][1] += Math.min(dp[next][0], dp[next][1]);
		}



	}
}