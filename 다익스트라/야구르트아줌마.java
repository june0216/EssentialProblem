import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 야구르트아줌마 {
	public static void main(String[] args) throws Exception{
		new 야구르트아줌마().solution();
	}

	List<Node>[] info;
	int N;
	public void solution()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		info = new List[N+1];
		for(int i = 0; i <= N ;i++){
			info[i] = new ArrayList<>();
		}



		for(int i = 0; i < E; i++){
			st= new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			info[from].add(new Node(to, cost));
			info[to].add(new Node(from, cost)); // 엥 단방향인지 아닌지 안나옴
		}


		int[] visitNode= new int[10];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 10; i++){

			int v = Integer.parseInt(st.nextToken());
			visitNode[i] = v;

		}

		int startN = Integer.parseInt(br.readLine());
		if(startN == visitNode[0]){
			System.out.println(visitNode[0]);
			return;
		}




		List<Integer> result = new ArrayList<>();

		long totalTime =0;
		int preIdx = 0;
		long[] route = di(startN, 0);
		for(int i =1 ; i < 10; i++){
			long[] time = di(visitNode[preIdx], visitNode[i]);
			if(time[visitNode[i]] == Long.MAX_VALUE){
				//preIdx = i;
				continue;
			}
			int target = visitNode[i];
			// System.out.println(startN);
			//long time2 =di(startN, visitNode[i+1]);
			// System.out.println(time2);
			totalTime += time[visitNode[i]];
			//System.out.println(totalTime);

			if(totalTime >= route[target]){
				result.add(visitNode[i]);
			}

			preIdx = i;



		}

		if(result.size() == 0){
			System.out.println(-1);
			return;
		}
		Collections.sort(result);
		System.out.println(result.get(0));



	}

	long[] dp;

	public long[] di(int start, int target){
		dp=new long[N+1];
		Arrays.fill(dp, Long.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer( new Node(start, 0));
		int time = 0;
		while(!pq.isEmpty()){
			Node cur = pq.poll();

			for(Node next : info[cur.to]){
				if(dp[next.to] > cur.cost+next.cost){
					dp[next.to] = cur.cost + next.cost;
					pq.offer(new Node(next.to, dp[next.to]));
				}
			}
		}
		return dp;
	}


	class Node implements Comparable<Node>{
		int to;
		long cost;

		public Node(int to,  long cost){
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node node){
			if(this.cost >= node.cost){
				return 1;
			}else{
				return -1;
			}

		}
	}
}