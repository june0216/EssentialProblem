import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 다리만들기 {
	public static void main(String[] args) throws Exception{
		new 다리만들기().solution();
	}

	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};
	int[][] map;
	boolean[][] visited;
	int N;
	int min;
	int result;
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		map = new int[N][N];
		min = Integer.MAX_VALUE;
		StringTokenizer st;
		for(int i = 0; i < N; i++){
			st =new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][N];
		int num =2;

		for(int i = 0; i < N; i++){

			for(int j = 0; j < N; j++){
				if(!visited[i][j] && map[i][j] != 0){
					visited[i][j] = true;
					map[i][j] = num;
					dfs(j, i, num);

					num++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++){

			for(int j = 0; j < N; j++){
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		//System.out.println(sb);


		min = Integer.MAX_VALUE;

		Set<Node> visitNum = new HashSet<>();
		result = 0;

		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(map[i][j] != 0){

					visited = new boolean[N][N];
					visited[i][j] = true;
					int dis = bfs(j, i, map[i][j]);

					min = Math.min(min, dis-1);

				}


			}

		}
		System.out.println(min);





	}

	// 0 을 먼저 넣을까. 아니면 섬을 넣을까

	public int bfs(int x, int y, int num){
		Deque<Node> que = new ArrayDeque<>();
		que.offer(new Node(x, y, 0));
		while(!que.isEmpty()){
			Node cur  = que.poll();
			if(map[cur.y][cur.x] != num && map[cur.y][cur.x] != 0){
				// System.out.println(cur.num);
				return cur.num;
			}
			for(int i = 0; i < 4; i++){
				int nx = dx[i] + cur.x;
				int ny= dy[i] + cur.y;
				if(nx >= 0 && ny >=0 && nx < N && ny < N){
					// 방문했거나 같은 숫자이면 패스
					if(visited[ny][nx] || map[ny][nx] == num){

						continue;
					}
					que.offer(new Node(nx, ny, cur.num+1));
					visited[ny][nx] = true;

				}
			}
		}
		return Integer.MAX_VALUE;
	}



	public void dfs(int x, int y, int num){

		for(int i = 0; i < 4; i++){
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if(nx >= 0 && nx < N && ny >=0 && ny < N){
				if(visited[ny][nx] || map[ny][nx] == 0){
					continue;
				}
				map[ny][nx] = num;
				visited[ny][nx] = true;
				dfs(nx, ny, num);
			}
		}
		return;


	}

	class Node{
		int x;
		int y;
		int num;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int num){
			this.x = x;
			this.y = y;
			this.num= num;
		}
	}
}
