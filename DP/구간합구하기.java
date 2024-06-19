import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기 {

    public static void main(String[] args) throws Exception{
        new 구간합구하기().solution();
    }

    int N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N+1][N+1];
        int[][] dp = new int[N+1][N+1];
        for(int i = 1 ; i < N+1; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + graph[i][j];
            }
        }


        StringBuilder sb = new StringBuilder();
        while(M-- >0){
            st= new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            int ans = dp[endX][endY] - dp[endX][startY-1] - dp[startX-1][endY] + dp[startX-1][startY-1];
            sb.append(ans).append("\n");

        }
        System.out.println(sb);
    }
}
