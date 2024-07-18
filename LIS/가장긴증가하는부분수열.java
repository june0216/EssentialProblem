import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열 {
    public static void main(String[] args) throws Exception{
        new 가장긴증가하는부분수열().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[N+1]; // 해당 위치 idx에서 가질 수 있는 LIS
        int[] num = new int[N];
        for(int i = 0 ; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        int max = 1;
        for(int i = 0 ; i < N; i++){
            dp[i] = 1; // 자기 자신은 일단 포함되므로
            for(int j = 0; j < i; j++){ // 앞에서 보면서 자기가 합류할 애들을 모색함
                if(num[i] > num[j]){ // 나보다 작으면 얘 다음에 내가 합류할 수 있는 가능성이 있으니까 최댓값 검사해보기
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);// 전체 수열에 대한 갱신
        }

        System.out.println(max);
    }

}
