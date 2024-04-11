import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 거리 {
	public static void main(String[] args) throws Exception{
		new 거리().solution();

	}
	int[] dp;
	char[] text;
	static final char[] BOJ = new char[]{'B', 'O', 'J'};
	public void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N];

		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		text = br.readLine().toCharArray();

		for(int i = 0 ; i < N-1; i++){
			for(int j = i+1; j < N; j++){ // i번째 위치에서 갈 수 있는 모든 지점을 탐색한다.
				if(checkNext(text[i], text[j])){
					int cost = (j-i)*(j-i);
					if(dp[i] != Integer.MAX_VALUE){
						dp[j] = Math.min(dp[j], dp[i]+cost);
					}


				}

			}
		}
		System.out.println(dp[N-1] == Integer.MAX_VALUE ? -1 : dp[N-1]);


	}

	public boolean checkNext(char current, char next){
		return (current == 'B' && next == 'O') ||
			(current == 'O' && next == 'J') ||
			(current == 'J' && next == 'B');
	}
}
