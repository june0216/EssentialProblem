import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DanceDanceRevolution {
    public static void main(String[] args) throws Exception{
        new DanceDanceRevolution().solution();
    }

    int min;
    List<Integer> li;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

       li = new ArrayList<>();

        while(st.hasMoreTokens()){

            int num = Integer.parseInt(st.nextToken());
            if (num == 0) {
                break;
            }
            li.add(num);

        }

        int[][][] dp = new int[li.size()+1][5][5];

        for(int i = 0 ;i <= li.size(); i++){
            for(int j = 0; j < 5; j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE / 2); // 오버플로우 발생 방자
            }
        }


        dp[0][0][0] = 0;
        for(int i = 1; i <= li.size(); i++){
            int next = li.get(i-1);
            for(int left = 0; left < 5; left++){ // 이 부분에서 계속 5번 반복하면서 왼쪽 발 위치를 찾아야 하나 고민했었음
                //발의 위치가 5개 중 어디에 있을지 모르기 때문에, 가능한 모든 상태를 탐색해야 함.
                for(int right = 0; right < 5; right++){
                    if(next != left){ // right가 next로 이동할 때 둘 다 같은 발이면 안된다.
                        dp[i][left][next] = Math.min(dp[i][left][next], dp[i-1][left][right] + calculateCost(right, next));
                    }
                    if(next != right){ // 왼발을 옮기는 경우
                        dp[i][next][right] = Math.min(dp[i][next][right], dp[i-1][left][right] + calculateCost(left, next));
                    }
                }

            }
        }
        min = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                min = Math.min(min, dp[li.size()][i][j]);
            }

        }
        System.out.println(min);
    }

    public int calculateCost(int pre, int cur){
        if(pre == cur){
            return 1;
        }
        if(pre ==0){
            return 2;
        }
        int diff = Math.abs(pre - cur);
        if(diff == 1 || diff == 3){
            return 3;
        }
        if(diff == 2 ){
            return 4;
        }
        return 0;
    }


}
