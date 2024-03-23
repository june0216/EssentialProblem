import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class 미로탐색 {

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    int H;
    int W;
    int[][] map;
    boolean[][] visited;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W];
        for(int i =0; i < H; i++){
            String num = br.readLine();
            for(int j = 0 ; j < W; j++){
                map[i][j] = num.charAt(j) - '0'; // String을 integer로 형변환
            }
        }
        System.out.println(bfs());

    }

    public int bfs(){
        Deque<Pos> que = new ArrayDeque<>();
        que.offer(new Pos(0, 0, 1)); // 개수는 처음 있는 곳부터 1로 세팅하기 때문에 1로 설정
        while(!que.isEmpty()){
            Pos current = que.poll();
            /*if(current.x == H-1 && current.y == W-1){ // 큐에서 최초로 도착지점이 나온 것 == 최단 경로
                return current.cnt;
            }*/
            for(int i = 0 ; i < 4; i++){
                int nx = dx[i] + current.x;
                int ny = dy[i] + current.y;
                if(nx >= 0 && ny >= 0 && ny < W && nx < H && map[nx][ny] == 1 && !visited[nx][ny]){
                    que.offer(new Pos(nx, ny, current.cnt+1)); // 경로 1 증가
                    if(nx== H-1 && ny == W-1){
                        return current.cnt+1;
                    }
                    visited[nx][ny] = true;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception{
        new 미로탐색().solution();
    }

    public class Pos{
        int x;
        int y;
        int cnt;
        public Pos(int x, int y, int current){
            this.x = x;
            this.y = y;
            this.cnt = current;
        }
    }



}
