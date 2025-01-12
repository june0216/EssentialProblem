import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 동전1 {
    public static void main(String[] args) throws Exception {
        new 동전1().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int typeCnt = Integer.parseInt(st.nextToken());
        int targetAmount = Integer.parseInt(st.nextToken());

        int coins[] = new int[typeCnt+1];

        for(int i = 0; i < typeCnt; i++){

            coins[i] = Integer.parseInt(br.readLine());

        }

        int[] dp = new int[targetAmount+1];
        dp[0] = 1;

        for(int i = 1; i <= typeCnt ; i++){
            for(int j = coins[i-1]; j <= targetAmount; j++){

                dp[j] += dp[j-coins[i-1]]; // 누적해서 더한다. 
            }
        }
        System.out.println(dp[targetAmount]);

        /*
          0 1 2 3 4 5 6 7 8 9 10
        1 1 1 1 1 1 1 1 1 1 1 1  ( 1로 만들 수 있는 경우의 수 )
        2 1 1 2 2 3 3 4 4 5 5 6  
        5 1 1 2 2 3 4 5 6 7 8 10 
        */
        /*
        예를 들어, 숫자 2는 F(2)라고 하면 4는 F(4) = F(2) + (4를 만들 수 있는 다른 경우)
        */

        // 저 예시대로 하려다가 안되었다. 

    }
}