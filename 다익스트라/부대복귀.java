import java.util.*;

class 부대복귀 {

	List<Node>[] info;
	int D;
	int N;
	int dp[];
	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		N = n;
		info = new List[n+1];
		for(int i = 0; i < n+1; i++){
			info[i] = new ArrayList<>();
		}
		D = destination;
		for(int i = 0; i < roads.length; i++){
			int to = roads[i][0];
			int from = roads[i][1];
			info[to].add(new Node(from, 1));
			info[from].add(new Node(to, 1));
		}

		List<Integer> ans = new ArrayList<>();
		dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		di();
		for(int i = 0; i < sources.length; i++){
			int result = dp[sources[i]];
			if(result == Integer.MAX_VALUE){
				ans.add(-1);
			}
			else{
				ans.add(result);
			}
		}
		int[] answer = new int[ans.size()];
		int idx = 0;
		for(int i = 0; i < ans.size(); i++){
			answer[i] = ans.get(idx);
			idx++;
		}

		return answer;
	}


	public void di(){
		PriorityQueue<Node> pq =new PriorityQueue<>();
		pq.offer(new Node(D, 0));
		dp[D] = 0;
		while(!pq.isEmpty()){
			Node cur = pq.poll();

			for(Node next : info[cur.to]){
				if(dp[next.to] > cur.cost+1){
					dp[next.to] = cur.cost+1;
					pq.offer(new Node(next.to, cur.cost+1));
				}

			}
		}
	}
	public int bfs(int start){
		Deque<Node> que = new ArrayDeque<>();
		que.offer(new Node(start, 0));
		while(!que.isEmpty()){
			Node cur = que.poll();
			if(cur.to == D){
				return cur.cost;
			}
			for(Node next : info[cur.to]){
				que.offer(new Node(next.to, cur.cost+1));
			}
		}
		return -1;
	}

	class Node implements Comparable<Node>{
		int to;

		int cost;
		public Node(int to, int cost){
			this.to = to;

			this.cost = cost;
		}

		@Override
		public int compareTo(Node node){
			return this.cost - node.cost;
		}
	}
}