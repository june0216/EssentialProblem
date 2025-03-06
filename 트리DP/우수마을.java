import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 우수마을 {
	public static void main(String[] args) throws Exception{
		new 우수마을().solution();
	}


	boolean[] visited;

	int N;
	int min;
	int result;
	List<Integer>[] map;
	int[] info;
	int cnt;
	int[][] dp;

	// 트리구조라는 것을 보지 못하고 visited 처리했다.
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		//'우수 마을'로 선정된 마을 주민 수의 총 합을 최대로 해야 한다.
		info = new int[N+1];
		map = new List[N+1];
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i =1; i <= N; i++){
			map[i] = new ArrayList<>();
			info[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < N-1; i++){
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			map[to].add(from);
			map[from].add(to);
		}

		// 모든 경우의 수를 탐색해야함
		// 조합? 이 생각나지만 겹치는 부분이 있음
		dp = new int[N+1][2]; // 우수마을 인지 아닌지
		visited = new boolean[N+1];
		//  visited[1] = true;
		dfs(1, 0, 0);
        /*
        for(int i = 1; i <= N; i++){


            visited = new boolean[N+1];
            dp = new int[N+1][2]; // 우수마을 인지 아닌지
            dfs(i, info[i], 0);
            //result = Math.max(dp[i][1], Math.max(result, dp[i][0]));
            //System.out.println(dp[i][1]);


        }*/



		for(int i = 1; i < N+1; i++){
			//System.out.println(dp[i][0] + " " + dp[i][1]);
			result = Math.max(dp[i][1], Math.max(result, dp[i][0]));
		}

		System.out.println(result);



	}

	public int dfs(int start, int parent, int isVisited){

		// dp[start][0] = 0;

		for(int next : map[start]){
			if(parent == next) continue;
			visited[next] = true;
			dfs(next, start, 0);
			// 위에가 아니면 무조건 가야함
			// 위에가 갔으면 무조건 안가야함
			//마을은 적어도 하나의 '우수 마을'과는 인접해 있어야 한다. 하나의 노드에 2개 자식 노드가 있는 경우를 의미하는듯 그런데 양수이므로 우수마을로 다 하는 게 좋음
			dp[start][0] += Math.max(dp[next][0], dp[next][1]);
			//System.out.println(start+" " +next+" "+dp[start][0]);
			dp[start][1] += dp[next][0];

		}

		dp[start][1] += info[start];
		return cnt;
	}




}
