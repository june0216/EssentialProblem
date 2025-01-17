import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰증가하는부분수열 {


    public static void main(String[] args) throws Exception{
        new 가장큰증가하는부분수열().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int[] dp = new int[N];
        dp[0] = arr[0];
        // 가장 증가하는
        for(int i = 0;  i < N ; i++){
            dp[i] = arr[i]; // 자기 차례에는 자기가 최대이다. ( 이 부분 때문에 자신이 최대라는 걸 보장이 된다.)
            for(int j = 0; j <= i; j++){
                if(arr[i] > arr[j]){ // 증가하는 수열이라면
                    // 자기 자신 + 그 이전의 합 vs 2중 반복문의 안쪽 반복문을 돌릴 때의 최댓값 업데이트
                    // 이 과정에서 넘어간다.
                    dp[i] = Math.max(arr[i] + dp[j], dp[i]); // 자기 자신까지의 최대 합을 업데이트 한다.
                }

            }
        }

        int max = 0;
        for(int i = 0; i < N; i++){
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
