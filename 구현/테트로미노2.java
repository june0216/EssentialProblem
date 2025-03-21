import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 테트로미노2 {
	public static void main(String[] args) throws Exception{
		new 테트로미노2().solution();
	}


	int Y;
	int X;
	int[][] map;

	boolean[][] visited;
	int max;

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());


		visited = new boolean[Y][X];
		map = new int[Y][X];

		for(int i = 0; i < Y; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < X; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 모든 경우들을 다 돌아야 한다
		max = 0;

		// 범위를 틀려버림

		for(int i = 0; i < Y-1; i++){
			int sum = 0;
			for(int j = 0; j < X-2; j++){
				sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+1];
				max = Math.max(max, sum);
				sum = map[i+1][j] + map[i+1][j+1] + map[i+1][j+2] + map[i][j+1];
				max = Math.max(max, sum);




			}
		}


		// 세로 방향

		for(int i = 0; i < Y-2; i++){
			int sum = 0;
			for(int j = 0; j < X-1; j++){
				sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j+1];
				max = Math.max(max, sum);
				sum = map[i][j+1] + map[i+1][j+1] + map[i+2][j+1] + map[i+1][j];
				max = Math.max(max, sum);




			}
		}
		// System.out.println(max);

		for(int i = 0; i < Y; i++){
			for(int j = 0; j < X; j++){
				visited[i][j] = true;
				combi(j, i, 0, map[i][j]);
				visited[i][j] = false;
			}
		}

		System.out.println(max);



	}

	int[] dx = {0, 0, -1, 1};
	int[] dy = {-1, 1, 0, 0};



	public void combi(int x, int y, int depth, int tmp){
		if(depth == 3){
			if(max < tmp){
				//System.out.println(x + " " + y);
			}
			//System.out.println(tmp);
			// print();
			max = Math.max(max, tmp);
			return;
		}

		for(int i = 0; i < 4; i++){
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if(nx >=0 &&  ny >=0 && nx < X && ny < Y){
				if(visited[ny][nx]) continue;
				visited[ny][nx] = true;
				combi(nx, ny, depth+1, tmp + map[ny][nx]);
				visited[ny][nx] = false;

			}
		}

	}

	public void print(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Y; i++){
			for(int j = 0; j < X; j++){
				sb.append(visited[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}