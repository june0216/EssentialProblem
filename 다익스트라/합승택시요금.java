import java.util.*;



class 합승택시요금 {
	/*
	처음에 3가지 케이스만 되는 줄 알았다 -> 가다가 A혼자, 가다가 B혼자 , 각각 혼자
	하지만 "중간 지점"까지 가서 나눠지는 케이스도 있다는 걸 고려하지 않았다.
	*/
	List<Node>[] info;
	int N;
	int B;
	int A;
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
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int result = Integer.MAX_VALUE;
		info = new List[n+1];
		for(int i = 0; i<= n; i++){
			info[i] = new ArrayList<>();
		}
		N = n;
		A = a;
		B = b;
		for(int i = 0; i < fares.length; i++){
			info[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
			info[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
		}


		// 각각
		int[] dpA = di(a);
		int[] dpB = di(b);
		int min = dpA[s]+ dpB[s];
		for(int i = 1; i < n+1; i++){
			if( dpB[i] == Integer.MAX_VALUE || dpA[i] !=Integer.MAX_VALUE) continue;
			min = Math.min(min,  Math.min(dpB[i]  + dpA[s], dpB[s] + dpA[i]));
		}
		// a ~ s까지의 거리 + 그 거리 중에서 b와의 최단 거리

		// S -> (I) -> A -> B
		// S -> (I) -> B-> A

		for(int i = 1; i < n+1; i++){

			int[] dpI= di(i);

			if( dpB[i] == Integer.MAX_VALUE || dpA[i] !=Integer.MAX_VALUE){
				min = Math.min(min, dpI[s] + dpB[i]  + dpA[i]);
				//System.out.println(i + " " + dpB[i]);
			}

			//min = Math.min(min, Math.min(dpI[s] + dpA[i] + dpI[b] , dpI[s] + dpB[i]  + dpI[a]));

		}



		return min;
	}


	public int[] di(int start){
		int[] dp = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dp[start] = 0;
		while(!pq.isEmpty()){
			Node cur  = pq.poll();
			// 이거 있어야 시간 초과 안남
			if(cur.cost > dp[cur.to]){
				continue;
			}

			for(Node next : info[cur.to]){

				if(dp[next.to] > cur.cost + next.cost){

					dp[next.to] = cur.cost + next.cost;

					pq.offer(new Node(next.to, dp[next.to]));

				}


			}
		}
		return dp;

	}
}





