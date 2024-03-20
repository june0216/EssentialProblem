import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 유기농배추 {
    int H;
    int W;
    int[][] garden;

    boolean[][] visited;
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- >0){

            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken()); //  가로길이
            H = Integer.parseInt(st.nextToken()); // 세로 길이
            int connected = Integer.parseInt(st.nextToken());
            garden = new int[H][W];
            visited = new boolean[H][W];

            while(connected-- > 0){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                garden[y][x] = 1;
            }
            int count = 0;
            for(int i = 0 ; i < H; i++){
                for(int j = 0 ; j < W; j++){
                    if(!visited[i][j] && garden[i][j] == 1){
                        dfs(i, j); // 배추가 있는 영역들을 구하기
                        count++; // 한 번 탐색을 하면 하나의 영역들이 다 방문 상태이다.
                    }

                }
            }
            sb.append(count).append("\n");

        }

        System.out.println(sb);

    }
    public void dfs(int x, int y){
        visited[x][y] = true;
        for(int i = 0 ; i < 4; i++) { // 4방을 확인하며 배추가 있는 곳들을 확인한다.
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && ny >= 0 && nx < H && ny < W && !visited[nx][ny] && garden[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }

    }

    public static void main(String[] args) throws Exception{
        new 유기농배추().solution();
    }
}
