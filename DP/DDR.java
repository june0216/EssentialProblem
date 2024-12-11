import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class DDR {
    public static void main(String[] args) throws Exception{
        new DDR().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        List<Integer> arr = new ArrayList<>();
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            if(num == 0){
                break;
            }
            arr.add(num);
        }


        int[][][] dp = new int[100001][5][5]; // 오, 왼의 값을 저장하려고 헀음 -> size 2로 했었다. 하지만 오른쪽 왼쪽값을 확정하는 것이 아니라 모든 경우의 수를 일단 저장해야하므로 상하좌우중간를 보관할 size 5를 선언한다.
        for(int i = 0; i < 100000; i++){
            for(int j = 0; j < 5; j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE/2); // 오버 플로우 방지
            }
        }
        dp[0][0][0] = 0;

        for(int i = 1; i <= arr.size(); i++){
            int next = arr.get(i-1);
            for(int left = 0; left < 5; left++){
                for(int right = 0; right < 5; right++){
                    if(right == left){
                        //continue;
                    }
                    // 오른발을 옮기는 경우
                    if(next != left){
                        int rightCost = calculate(right, next);
                        dp[i][next][left] = Math.min(dp[i-1][right][left] + rightCost, dp[i][next][left]);
                    }

                    // 왼발을 옮기는 경우
                    if(next != right){
                        int leftCost = calculate(left, next);
                        dp[i][right][next] = Math.min(dp[i-1][right][left] + leftCost, dp[i][right][next]);
                    }

                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                min = Math.min(dp[arr.size()][i][j], min);
            }
        }
        System.out.println(min);

    }

    public int calculate(int pre, int next){

        if(pre == next){
            return 1;
        }
        if(pre == 0){
            return 2;
        }
        int diff = Math.abs(pre - next);
        if(diff == 2){
            return 4;
        }else{
            return 3;
        }


    }
}
