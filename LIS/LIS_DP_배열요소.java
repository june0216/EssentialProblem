import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class LIS_DP_배열요소 {
    public static void main(String[] args) throws Exception{
        new LIS_DP_배열요소().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb= new StringBuilder();
        int[] dp = new int[N + 1]; // 해당 위치 idx에서 가질 수 있는 LIS
        int[] pre = new int[N+1];

        int[] num = new int[N];
        for(int i = 0 ; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++){
            pre[i]  = i;
        }

        for(int i = 0; i < N; i++){
            dp[i] = 1; // 자기 자신 시점에서 일단 나는 증가 원소임
            for(int j = 0; j < i; j++){
                if(num[i] > num[j]){
                    if(dp[j]+1 > dp[i]){
                        pre[i] =j ;
                        dp[i] = dp[j]+1;
                    }

                }
            }
        }
        int max = 0;
        int p = 0;
        for(int i = 0; i < N; i++){
            if(dp[i] > max){
                max = dp[i];
                p = i;

            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        while(true){
            stack.addFirst(num[p]);
            if(p == pre[p]){
                break;
            }
            p = pre[p];
        }

        sb.append(max).append("\n");
        for(int n : stack){
            sb.append(n).append(" ");
        }
        System.out.println(sb);

    }
}
