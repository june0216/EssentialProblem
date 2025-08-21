import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 로봇 {
    public static void main(String[] args) throws Exception{
        new 로봇().solution();
    }

    int Y;
    int X;
    int[][] map;
    int startX;
    int startY;
    int endX;
    int endY;
    int min;
    int startDir;
    int endDir;
    
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new int[Y][X];
        for(int i = 0; i < Y; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < X; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        startY = Integer.parseInt(st.nextToken())-1;
        startX = Integer.parseInt(st.nextToken())-1;
        startDir = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        endY = Integer.parseInt(st.nextToken())-1;
        endX = Integer.parseInt(st.nextToken())-1;
        endDir = Integer.parseInt(st.nextToken());

        startDir = checkDir(startDir);
        endDir = checkDir(endDir);

        visited = new boolean[Y][X][4];
        min = Integer.MAX_VALUE;
        bfs(startX, startY, startDir, 0);
        
        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public int checkDir(int dir){
        if(dir == 1) return 1;      // 동
        else if(dir == 2) return 3; // 서
        else if(dir == 3) return 2; // 남
        else return 0;               // 북
    }

    // 북, 동, 남, 서 순서
    int dx[] = {0, 1, 0, -1};
    int dy[] = {-1, 0, 1, 0};
    boolean[][][] visited;
    
    public void bfs(int sx, int sy, int sdir, int scnt){
        Deque<Node> que = new ArrayDeque<>();
        que.offer(new Node(sx, sy, sdir, scnt));
        visited[sy][sx][sdir] = true;
        
        while(!que.isEmpty()){
            Node cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            int cnt = cur.cnt;
            int dir = cur.dir;

            if(x == endX && y == endY && dir == endDir){
                min = Math.min(min, cnt);
                continue;
            }

            // 오른쪽 회전 (시계방향)
            int rightDir = (dir + 1) % 4;
            if(!visited[y][x][rightDir]){
                visited[y][x][rightDir] = true;
                que.offer(new Node(x, y, rightDir, cnt + 1));
            }

            // 왼쪽 회전 (반시계방향)
            int leftDir = (dir + 3) % 4;
            if(!visited[y][x][leftDir]){
                visited[y][x][leftDir] = true;
                que.offer(new Node(x, y, leftDir, cnt + 1));
            }

            // 직진 (1~3칸)
            for(int d = 1; d <= 3; d++){
                int nx = x + dx[dir] * d;
                int ny = y + dy[dir] * d;
                
                if(!isValid(nx, ny)) break;
                
                // 중간 경로에 장애물이 있는지 확인
                
                
                if(!visited[ny][nx][dir]){
                    visited[ny][nx][dir] = true;
                    que.offer(new Node(nx, ny, dir, cnt + 1));
                }
            }
        }
    }

    class Node{
        int x;
        int y;
        int dir;
        int cnt;
        
        public Node(int x, int y, int dir, int cnt){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public boolean isValid(int x, int y){
        return x >= 0 && x < X && y >= 0 && y < Y && map[y][x] == 0;
    }
}
