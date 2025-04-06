import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 스타트택시 {
	public static void main(String[] args) throws Exception{
		new 스타트택시().solution();
	}




	int N;
	int M;
	int initialFuel;
	int startX;
	int startY;
	Map<Integer, Node> goalInfo;
	int[][] map;
	List<Node> customers;
	Set<Integer> finish;
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		initialFuel =  Integer.parseInt(st.nextToken());
		goalInfo = new HashMap<>();
		customers = new ArrayList<>();
		map = new int[N][N];
		finish=new HashSet<>();
		for(int i = 0; i < N ;i++){
			st =  new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				map[i][j] =  Integer.parseInt(st.nextToken());
				if(map[i][j] == 1){
					map[i][j] = -1;
				}

			}
		}

		st =  new StringTokenizer(br.readLine());
		startY =  Integer.parseInt(st.nextToken())-1;
		startX =  Integer.parseInt(st.nextToken())-1;

		for(int i =1; i <= M; i++){
			st =  new StringTokenizer(br.readLine());
			int y1 =  Integer.parseInt(st.nextToken())-1;
			int x1 =  Integer.parseInt(st.nextToken())-1;
			int y2 =  Integer.parseInt(st.nextToken())-1;
			int x2 =  Integer.parseInt(st.nextToken())-1;

			map[y1][x1] = i;
			goalInfo.put(i, new Node(x2, y2, 0));

		}

		for(int i = 0 ; i < M;i++){
			taxiToPerson();
			if(customers.size() == 0){
				System.out.println(-1);
				return;
			}
			Node cur = customers.get(0);
			finish.add(map[cur.y][cur.x]);
			// System.out.println(map[cur.y][cur.x]);

			initialFuel -= cur.cost;
			if(initialFuel <0){
				System.out.println(-1);
				return;
			}

			// System.out.println("cost "  + cur.cost);
			startX = cur.x;
			startY = cur.y;

			Node target= goalInfo.get(map[cur.y][cur.x]);
			int res = toGoal(target.x, target.y);
			if(res == -1){
				System.out.println(-1);
				return;
			}
			initialFuel -= res;
			if(initialFuel<0){
				System.out.println(-1);
				return;
			}
			initialFuel += (res*2);
			//System.out.println("goal"  + res);
			startX = target.x;
			startY = target.y;

			map[cur.y][cur.x] = 0;

		}
		System.out.println(initialFuel);
	}


	int dx[] = {-1, 1, 0, 0};
	int dy[] = {0, 0, -1, 1};
	public void taxiToPerson(){
		Deque<Node> que = new ArrayDeque<>();
		que.offer(new Node(startX, startY, 0));
		boolean visited[][] = new boolean[N][N];
		visited[startY][startX] = true;
		customers.clear();

		int min = Integer.MAX_VALUE;
		while(!que.isEmpty()){
			Node cur = que.poll();
			if(cur.cost > min) continue;  // 더 먼 경우는 무시
			if(map[cur.y][cur.x] >= 1){
				if(!finish.contains(map[cur.y][cur.x])){ // 틀린이유 -> CONTINUE해버려서
					if(cur.cost < min){
						min = cur.cost;
						customers.clear();
					}

					customers.add(new Node(cur.x, cur.y, cur.cost));
				}


			}
			for(int i = 0; i < 4; i++){
				int nx = dx[i] + cur.x;
				int ny = dy[i] + cur.y;
				if(nx >=0 && ny >= 0 && nx < N && ny < N){
					if(visited[ny][nx]) continue;
					if(map[ny][nx] != -1){
						que.offer(new Node(nx, ny, cur.cost+1));
						visited[ny][nx] = true;
					}
				}
			}
		}

		Collections.sort(customers, new Comparator<Node>(){
			public int compare(Node a, Node b){
				if(a.y == b.y) return a.x - b.x;
				return a.y - b.y;
			}
		});
	}

	public int toGoal(int x, int y){
		Deque<Node> que = new ArrayDeque<>();
		que.offer(new Node(startX, startY, 0));
		boolean visited[][] = new boolean[N][N];
		visited[startY][startX] = true;
		while(!que.isEmpty()){
			Node cur = que.poll();
			if(cur.x == x && cur.y == y){
				return cur.cost;
			}
			for(int i = 0; i < 4; i++){
				int nx = dx[i] + cur.x;
				int ny = dy[i] + cur.y;
				if(nx >=0 && ny >= 0 && nx < N && ny < N){
					if(visited[ny][nx]) continue;
					if(map[ny][nx] != -1){
						visited[ny][nx] = true;
						que.offer(new Node(nx, ny, cur.cost+1));
					}
				}
			}
		}
		return -1;
	}

	class Node implements Comparable<Node>{
		int x;
		int y;
		int cost;

		public Node(int x, int y, int cost){
			this.x= x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node node){
			//그런 승객이 여러 명이면 그중 행 번호가 가장 작은 승객을, 그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객을 고른다.
			if(this.cost == node.cost){
				if(this.y == node.y ){
					return this.x - node.x;
				}

				return this.y - node.y;
			}
			return this.cost - node.cost;
		}

	}
}