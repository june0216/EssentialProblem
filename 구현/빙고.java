import java.util.*;
import java.io.*;
public class 빙고 {
	public static void main(String[] args) throws Exception{
		new 빙고().solution();
	}
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] colSum = new int[5];
		int[] rowSum = new int[5];
		int right = 0;
		int left = 0;
		Map<Integer, int[]> findSpot = new HashMap<>();
		int[][] map = new int[5][5];
		for(int i = 0; i < 5; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				findSpot.put(map[i][j], new int[]{i, j});
			}
			colSum[i] = 0;
			rowSum[i] = 0;
		}


		int cnt = 0;
		int line = 0;
		for(int i = 0; i < 5; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++){
				int num = Integer.parseInt(st.nextToken());
				int[] spot = findSpot.get(num);
				if(colSum[spot[1]] != -1){
					colSum[spot[1]]++;
					if(colSum[spot[1]] == 5){
						line++;
						colSum[spot[1]] = -1;
					}
				}
				if(rowSum[spot[0]] != -1) {
					rowSum[spot[0]]++;
					if(rowSum[spot[0]] == 5){
						line++;
						rowSum[spot[0]] = -1;
					}
				}


				if(right != -1 && spot[0] == spot[1]){
					right++;
					if(right == 5){
						line++;
						right = -1;
					}
				}
				if(left != -1 && (spot[0] + spot[1]) == 4){
					left++;
					if(left == 5){
						line++;
						left =-1;
					}
				}

				if(line >= 3){
					System.out.println(cnt+1);

					return;
				}
				cnt++;
			}
		}
	}
}
