import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class 빗물2 {
	public static void main(String[] args) throws Exception{
		new 빗물2().solution();
	}


	//처음에는 스택으로 풀려고 했다. -> 자기를 기준으로 뒤에 오목한 부분이 생기면 (제일 컸다가 감소하는 부분이 생기면) 그때 계산하려고 했는데 까다로웠다.
	// 생각을 전환하여 자기를 기준으로 왼쪽과 오른쪽 중 가장 큰 2개를 골라서 자신의 위치에서 물이 고이는 정도를 생각하면 된다.
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());


		int H = Integer.parseInt(st.nextToken());
		int W= Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] map = new int[W];
		for(int i = 0 ; i< W; i++){
			map[i]= Integer.parseInt(st.nextToken());
		}
		int result = 0;
		for(int i = 1; i < W-1; i++){
			int leftMax = 0;
			int rightMax = 0;
			for(int j = 0; j < i; j++){
				if(map[i] < map[j]){
					leftMax = Math.max(leftMax, map[j]);
				}
			}
			for(int r = i; r < W; r++){
				if(map[i] < map[r]){
					rightMax = Math.max(rightMax, map[r]);
				}
			}
			if(leftMax != 0 && rightMax != 0){
				result +=( Math.min(leftMax, rightMax) - map[i]);
			}
		}

		System.out.println(result);

	}
}
