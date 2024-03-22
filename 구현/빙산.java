import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class 빙산 {

    int H;
    int W;
    int[][] map;

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    boolean[][] visited;
    List<Integer> ice;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        //visited = new boolean[H][W];
        for(int i = 0; i < H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램
        int result = 0;
        int[][] origin = new int[H][W];
        while(true){
            int oneNum = 0;
            for(int i = 0 ; i < H; i++){
                for(int j = 0; j < W; j++){
                    origin[i][j] = map[i][j];
                    if(origin[i][j] == 0)oneNum++;
                }
            }
            if(oneNum == H*W) {
                System.out.println(0);
                break;
            }
            bfs(origin);
            result++;
            int count = countPiece();
            if(count >= 2) {
                System.out.println(result);
                break;
            }

        }

    }

    // 동서남북으로 0이 있는 개수만큼
    public void bfs(int[][] temp){
        ArrayDeque<Pos> que = new ArrayDeque<>();
        for(int i = 0 ; i < H; i++){
            for(int j = 0; j < W; j++){
                if(map[i][j] != 0){
                    que.offer(new Pos(i, j, 1));
                }
            }
        }

        while(!que.isEmpty()){
            Pos current = que.poll();
            int count = 0;
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + current.x;
                int ny = dy[i] + current.y;
                if(nx >= 0 && ny >= 0 && nx < H && ny < W && temp[nx][ny] == 0){ // 얼음이라면
                    count++;
                }
            }
            map[current.x][current.y] -= count;
            if(map[current.x][current.y] <= 0){
                map[current.x][current.y] = 0;
            }
        }

    }

    public int countPiece(){
        int count = 0;
        visited = new boolean[H][W];
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(!visited[i][j] && map[i][j] != 0){
                    dfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int x, int y){
        visited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(nx >= 0 && ny >= 0 && nx < H && ny < W && map[nx][ny] != 0 && !visited[nx][ny]){ // 얼음이라면
                dfs(nx, ny);
            }
        }
    }



    class Pos{
        int x;
        int y;
        int year;
        public Pos(int x, int y, int year){
            this.x = x;
            this.y = y;
            this.year = year;
        }
    }

    public static void main(String[] args) throws Exception{
        new 빙산().solution();
    }
}
