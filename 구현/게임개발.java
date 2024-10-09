import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class 게임개발 {
    public static void main(String[] args) throws Exception{
        long before = System.currentTimeMillis();

        new 게임개발().solution();
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }

    public static int LAND = 0;
    public static int OCEAN = 1;

    // 반시계방향으로
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};

    public int X;
    public int Y;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int[][] map = new int[Y][X];

        st = new StringTokenizer(br.readLine());

        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for(int i = 0; i < Y; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < X; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[Y][X];

        int res = 1;
        visited[startY][startX] = true;
        int nx = startX;
        int ny = startY;
        int cnt = 0;
        int originDir = direction;
        while(true){
            if(cnt == 4){
                nx = nx - dx[originDir];
                ny = ny - dy[originDir];
                if(isRange(ny, nx) && map[ny][nx] != OCEAN){
                    direction = changeDirection(originDir);
                    originDir = direction;
                    continue;
                }else{
                    break;
                }

            }

            // 반시계 0 -> 3 -> 2 -> 1 -> 0
            direction = changeDirection(direction);
            int nextX = nx + dx[direction];
            int nextY = ny + dy[direction];
            if(isRange(nextY, nextX) && !visited[nextY][nextX] && map[nextY][nextX] != OCEAN){
                visited[nextY][nextX] = true;
                nx = nextX;
                ny = nextY;
                res++;
                cnt = 0; // 다시 시작
            }

            cnt++;

        }
        System.out.println(res);



    }

    public boolean isRange(int y, int x){
        if(x >= 0 && x < X && y >= 0 && y < Y){
            return true;
        }
        return false;
    }

    public int changeDirection(int dir){
        if(dir == 0){
            return 3;
        }else{
            return --dir;
        }
    }
}
