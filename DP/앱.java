import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 앱{
    public static void main(String[] args) throws Exception{
        new 앱().solution();
    }

    // 배낭 문제와 비슷
    // 모든 경우의 수를 다 돌기에는 수가 많고 메모리제이션을 통해 계산을 줄일 수 있다.
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int additionalMemory = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Node> appInfo = new ArrayList<>();
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            appInfo.add(new Node(num));
        }
        st = new StringTokenizer(br.readLine());
        int costSum = 0;

        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            costSum += num;
            appInfo.get(i).cost = num;
        }
        int[][] dp = new int[N+1][costSum+1];// 최대 비용

        // 비용은 최소, 메모리는 최대
        for(int i = 1; i <=N; i++){
            int cost = appInfo.get(i-1).cost;
            int memory =appInfo.get(i-1).size;
            for(int c = 0; c <= costSum; c++){
                if( c >= cost){ // 비용이 가능하다면
                    // 이전에 같은 비용의 메모리  vs 이전 중 지금 비용 빼고 + 현 메모리
                    dp[i][c] = Math.max(dp[i-1][c], dp[i-1][c-cost] + memory);
                }else{ // 비용이 불가능하자다면 못함
                    dp[i][c] = dp[i-1][c];
                }


            }
        }
        int result = Integer.MAX_VALUE;
        for (int c = 0; c <= costSum; c++) {
            if (dp[N][c] >= additionalMemory) { // M 바이트 이상의 메모리 확보
                result = Math.min(result, c);
            }
        }

        System.out.println(result);

    }
    class Node{
        int cost;
        int size;

        public Node(int cost, int size) {
            this.cost = cost;
            this.size = size;
        }

        public Node(int size){
            this.size = size;
        }
    }
}
/*
testCase Input
5 60
30 10 20 35 40
3 0 3 5 4
 */




