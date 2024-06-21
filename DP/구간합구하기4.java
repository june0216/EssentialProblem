import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기4 {
    public static void main(String[] args) throws Exception{
        new 구간합구하기4().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int QueryNum = Integer.parseInt(st.nextToken());


        int dp[] = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i<= N; i++){
            dp[i] = dp[i-1] + Integer.parseInt(st.nextToken()); // 누적합구하기
        }

        StringBuilder sb = new StringBuilder();
        while(QueryNum -- > 0){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(dp[end] - dp[start-1]).append("\n");
        }
        System.out.println(sb);
    }
}
