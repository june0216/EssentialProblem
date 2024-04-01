import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 계단오르기 {
    public static void main(String[] args) throws Exception{
        new 계단오르기().solution();
    }

    int total;
    int[] scoreList;
    int[] dp;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = Integer.parseInt(br.readLine());
        scoreList = new int[total];

        for(int i = 0; i< total;i++){
            scoreList[i] = Integer.parseInt(br.readLine());
        }
        //연속 3개를 밟으면 안된다는 조건으로 3개씩 봐야함
        int res = 0;
        if(total ==1){
            res = scoreList[0];
        }else if(total ==2){
            res = scoreList[0] + scoreList[1];
        }else if(total ==3){
            res = Math.max(scoreList[1], scoreList[0]) + scoreList[2];
        }else{
            dp = new int[total+1];
            dp[total-1] = scoreList[total-1];
            for(int i = total-1; i >= 2;i--){
                // i번째 계단을 밟기 위해서 가능한 경우는 2가지인데 1) 직전 계단을 밟기 2) 한칸 띄고 밟기이다. 이때, 1의 경우 꼭 i-3번째를 밟아야 하므로 더한다.
                dp[i] = Math.max(scoreList[i-1] + scoreList[i-3], scoreList[i-1]) + scoreList[i];

            }
        }

    }
    
}
