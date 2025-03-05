import java.util.*;
import java.lang.*;
import java.io.*;


class 불 {
	public static void main(String[] args) throws Exception{
		new 불().solution();
	}

	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());

		for(int i = 0; i < testCase; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			Deque<Node> fire = new ArrayDeque<>();
			List<Node> start = new ArrayList<>();
			char[][] map = new char[h][w];
			for(int k = 0; k < h; k++){
				String input = br.readLine();
				for(int j = 0; j < w; j++){
					map[k][j] = input.charAt(j);
					if(map[k][j] == '*'){
						fire.offer(new Node(j, k));
					}
					if(map[k][j] == '@'){
						start.add(new Node(j, k));
					}
				}
			}

			Deque<Node> que = new ArrayDeque<>();
			boolean[][] visited = new boolean[h][w];
			for(Node cur : start){
				que.offer(cur);
				visited[cur.y][cur.x] = true;
			}

			int time = 0;
			boolean flag = false;


			while(!que.isEmpty()){


				// 1차적으로 불  처리
				int fireLen = fire.size();

				for(int l = 0; l < fireLen; l++){
					Node fi = fire.poll();
					for(int j = 0; j < 4; j++){
						int nx = fi.x + dx[j];
						int ny = fi.y + dy[j];
						if(nx >=0 && ny >=0 && nx < w && ny < h){
							if(map[ny][nx] == '#' || map[ny][nx] == '*'){
								// 상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다.
								continue;
							}
							map[ny][nx] = '*';
							fire.offer(new Node(nx, ny));

						}
					}
				}


				// 상근이 이동
				int len = que.size();


				for(int l = 0; l < len; l++){
					Node cur = que.poll();
					for(int j = 0; j < 4; j++){
						int nx = cur.x + dx[j];
						int ny = cur.y + dy[j];
						if(nx >=0 && ny >=0 && nx < w && ny < h){
							if(map[ny][nx] == '#' || map[ny][nx] == '*' || visited[ny][nx]){
								// 상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다.
								continue;
							}
							que.offer(new Node(nx, ny));
							visited[ny][nx] = true;


						}else{
							//System.out.println(time);
							flag = true;
							break;
						}




					}
					if(flag) break;
				}

				time++;
				if(flag) break;




			}

			if(!flag){
				System.out.println("IMPOSSIBLE");
			}else{
				System.out.println(time);}




		}


	}

	class Node{
		int x;
		int y;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
