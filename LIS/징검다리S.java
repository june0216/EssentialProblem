import java.io.*;
import java.util.*;

public class 징검다리S {

    public static void main(String[] args) throws Exception{
        new 징검다리S().solution();
    }
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 조건 1 - 가장 긴 부분 수열 구해야함, 연속되지 않아도 된다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] number = new int[N];
        number[0] = Integer.parseInt(st.nextToken());
        int count = 1;
        int dp[] = new int[N];
        Arrays.fill(dp, 1); // 최소 갯수가 1임 -> 돌을 최소 한 번은 밟기 때문
        int temp = 0;
        for(int i = 1; i < N; i++){ // 자신이 제일 마지막에 밟는 돌이라고 생각 -> 자기 앞에 있는 돌들이 자기보다 작은 경우
            number[i] = Integer.parseInt(st.nextToken());
            temp = 0;
            for(int j = 0; j < i ; j++){ // 마지막돌 보다 작은 다 체크 -> 작은 돌들 중 최댓값을 골라 자기 자신으로 갱신
                if(number[j] < number[i]){
                    temp = Math.max(dp[j], temp);
                }

            }
            dp[i] = temp+1;



        }
        for(int i = 0; i < dp.length;i++){
            count = Math.max(count, dp[i]);
        }
        System.out.println(count);

    }
}


