import java.util.*;

class 가장먼노드다익스트라 {
	public int solution(int n, int[][] edge) {
		int answer = 0;
		List<Node> info[] = new List[n+1];
		for(int i = 0; i <= n; i++){
			info[i] = new ArrayList<>();
		}
		for(int i = 0; i < edge.length; i++){
			int to = edge[i][0];
			int from = edge[i][1];
			info[to].add(new Node(from, 1));
			info[from].add(new Node(to, 1));

		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		int[] dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] =0;

		while(!pq.isEmpty()){
			Node cur = pq.poll();
			for(Node next : info[cur.to]){
				//System.out.println(next.to);
				if(dp[next.to] > cur.cost + 1){
					dp[next.to] =cur.cost + 1;
					pq.offer(new Node(next.to, dp[next.to]));
				}
			}
		}
		int max = 0;
		for(int i = 1; i <= n; i++){
			if(dp[i] == Integer.MAX_VALUE) continue;

			max = Math.max(dp[i], max);

		}
		System.out.println(max);
		for(int i = 1; i <= n; i++){
			if(dp[i] == max) answer++;
		}



		return answer;
	}

	class Node implements Comparable<Node>{
		int to;
		int cost;
		public Node(int to, int cost){
			this.cost = cost;
			this.to = to;
		}

		@Override
		public int compareTo(Node node){
			return this.cost-node.cost;
		}
	}
}