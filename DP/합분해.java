import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합분해 {
    public static void main(String[] args) throws Exception{
        new 합분해().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken(br.readLine()));
        int targetSum = Integer.parseInt(st.nextToken(br.readLine()));
         // 0
        int[][] dp = new int[N][N]; // gkq
         // 20 2
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j]); // 어차피 차이가 1이므로 이렇게
            }
        }

    }
}
