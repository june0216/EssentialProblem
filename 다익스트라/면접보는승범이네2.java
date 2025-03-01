import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 면접보는승범이네2 {
	public static void main(String[] args) throws Exception{
		new 면접보는승범이네2().solution();
	}


	List<Node>[] map;
	int N;
	int M;
	int K;
	long[] dp;
	int interview[];
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new long[N+1];
		map = new List[N+1];
		interview = new int[N+1];
		for(int i = 0; i < N+1; i++){
			map[i] = new ArrayList<>();
		}

		for(int i =0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost= Integer.parseInt(st.nextToken());
			// 단방향
			map[to].add(new Node(from, cost));

		}

		Arrays.fill(dp, Long.MAX_VALUE);
		// 인터뷰

		PriorityQueue<Node> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K ;i++){
			interview[i] = Integer.parseInt(st.nextToken());
			dp[interview[i]] = 0l;
			pq.offer(new Node(interview[i], 0));
		}

		// 다익스트라
		while(!pq.isEmpty()){
			Node cur = pq.poll();
			// 아래 한 줄을 추가해야 시간초과 안남 -> 다른 문제들과 다르게 면접장 여러 곳에서 시작해서
			if(dp[cur.to] < cur.cost) continue;
			for(Node next : map[cur.to]){
				if(dp[next.to] > dp[cur.to]+ next.cost ){

					dp[next.to] = dp[cur.to] + next.cost;
					pq.offer(new Node(next.to, dp[next.to]));
				}
			}
		}

		long result = 0;
		int resultIdx = 0;
		for(int i = 1; i < N+1; i++){
			if(result < dp[i]){
				resultIdx = i;
				result = dp[i];

			}
		}

		System.out.println(resultIdx);
		System.out.println(result);












	}

	class Node implements Comparable<Node>{

		int to;
		long cost;
		public Node( int to, long cost){

			this.to = to;
			this.cost = cost;
		}

		// 오름차순

		@Override
		public int compareTo(Node node){
			if(this.cost > node.cost){
				return 1;
			}else if(this.cost == node.cost){
				return 0;
			}
			return -1;
		}
	}
}
