import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭 {
    public static void main(String[] args) throws Exception{
        new 평범한배낭().solution();

    }


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int limitWeight = Integer.parseInt(st.nextToken());
        int[][] info = new int[N+1][2];


        // value 최대
        int maxWeight = 0;
        for(int idx = 1; idx <= N; idx++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            info[idx][0] = weight;
            info[idx][1] = value;

        }

        int dp[][] = new int[limitWeight+1][N+1];


        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= limitWeight ;j++){
                if(info[i][0] <= j){ // 최대 무게 안에 들어왔을 때 넣을 수 있음
                    // 안넣기(같은 무게 범위 제한에서 앞 사람의 최대값 확인) vs 넣기 (넣을 때는 무게만큼 뺀 곳의 가치에 지금 시점의 가치를 더한다.
                    dp[j][i] = Math.max(dp[j-info[i][0]][i-1] + info[i][1], dp[j][i-1]);
                }else{
                    // ex) 4kg이 제한일 때 넣을 것이 없다면 3kg일 때 최대값을 넣는다.
                    dp[j][i] = dp[j][i-1];
                }

            }
        }




        System.out.println(dp[limitWeight][N]);


    }
}
