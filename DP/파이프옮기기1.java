import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.*;
import java.io.*;

public class 파이프옮기기1{
	public static void main(String[] args) throws Exception{
		new 파이프옮기기1().solution();
	}

	int  WALL = 1;
	int EMPTY = 0;
	int[][] map;
	int N;
	int res;

	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		res = 0;
		dfs(1, 0, 1);
		System.out.println(res);





	}

	public void dfs(int x, int y, int dir){
		if(x == N-1 && y == N-1){
			res++;
			return;
		}

		if(dir == 0){
			//세로
			if(y+1 < N && map[y+1][x] == EMPTY){
				dfs(x, y+1, 0);
			}

			if(x+1 < N && y+1 < N && map[y+1][x+1] == EMPTY){
				if(x < N && y + 1 < N && map[y+1][x] == EMPTY){
					if(x+1 < N && y  < N && map[y][x+1] == EMPTY){
						dfs(x+1, y+1, 2);
					}
				}
			}

		}else if(dir == 1){
			// 가로
			if(x+1 < N && map[y][x+1] == EMPTY){
				dfs(x+1, y, 1);
			}
			if(x+1 < N && y+1 < N && map[y+1][x+1] == EMPTY){
				if(x < N && y + 1 < N && map[y+1][x] == EMPTY){
					if(x+1 < N && y  < N && map[y][x+1] == EMPTY){
						dfs(x+1, y+1, 2);
					}
				}
			}


		}else if(dir == 2){
			// 대각선
			if(y+1 < N && map[y+1][x] == EMPTY){
				dfs(x, y+1, 0);
			}
			if(x+1 < N && map[y][x+1] == EMPTY){
				dfs(x+1, y, 1);
			}
			if(x+1 < N && y+1 < N && map[y+1][x+1] == EMPTY){
				if(x < N && y + 1 < N && map[y+1][x] == EMPTY){
					if(x+1 < N && y  < N && map[y][x+1] == EMPTY){
						dfs(x+1, y+1, 2);
					}
				}

			}

		}
	}
}
