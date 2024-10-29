import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노 {
    public static void main(String[] args) throws Exception {
        new 테트로미노().solution();
    }

//테트리스를 쌓을 수 있는지 + 최대합
    // 각 틀의 공통점 -> 연속 2개가 있음

    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};



    int R;
    int C;
    int[][] map;
    boolean[][] visited;

    int max;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = 0;
        map = new int[R][C];
        visited  = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i< R; i++) {
            for (int j = 0; j < C; j++) {
                visited[i][j] = true;
                dfs(1, j, i, map[i][j]);
                visited[i][j] = false;

                ex(1, 0, j, i, map[i][j]);

            }
        }

        System.out.println(max);



    }


    // 깊이가 4인 dfs 상하좌우 탐색
    // 경우의 수가 많아 보임 -> 규칙을 찾아보자
    // 예외가 있더라도 1개라면 비벼볼만함

    public void ex(int depth, int start, int x, int y, int sum){
        if(depth == 4){
            max = Math.max(max, sum);
            return;
        }
        for(int i = start; i < 4; i++){
            int nx = x + dx[i];
            int ny = y+ dy[i];
            if(nx >= 0 && ny >= 0 && nx < C && ny < R){
                ex(depth+1, i+1 ,x, y, sum+map[ny][nx]);
            }

        }

    }
    public void dfs(int depth, int x, int y, int sum){
        if(depth == 4){
            max = Math.max(max, sum);
            return;
        }
        for(int i = 0; i < 4; i++){
            int nx= dx[i] + x;
            int ny = dy[i] + y;
            if(nx >= 0 && ny >= 0 && nx < C && ny < R){
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    dfs(depth+1, nx, ny, sum+map[ny][nx]);
                    visited[ny][nx] = false;
                }
            }
        }
    }

}
