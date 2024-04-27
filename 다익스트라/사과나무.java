import java.util.*;
import java.io.*;
class 사과나무{
    public static void main(String[] args) throws Exception{
        new 사과나무().solution();
    }

    int[][] graph;
    int[][] dp;

    int N;
    List<Integer> res;
    boolean[][] visited;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        for(int i = 0 ; i < N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        graph = new int[N][N];
        res = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                res.add(graph[i][j]);
            }
        }




        for(int i = 2 ; i <= N; i++){
            dfs(i);
        }
        Collections.sort(res, Collections.reverseOrder());
        System.out.println(res.get(0));


    }

    public void dfs(int total){
        for(int i = 0; i <= N-total; i++){
            for(int j = 0; j <= N-total; j++){
                res.add(cal(i, j, total));
            }

        }
    }
    public int cal(int startX, int startY, int total){
        int sum = 0;
        for(int i = 0; i < total; i++){
            for(int j = 0 ; j < total; j++) {
                sum += graph[startY+i][startX+j];
            }
        }
        return sum;
    }

}