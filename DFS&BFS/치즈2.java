import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class 치즈2 {
    public static void main(String[] args) throws Exception {
            new 치즈2().solution();
    }

    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    int R;
    int C;
    int time;
    int cheese;
    int[][] map;

    int melt;
    boolean[][] visit;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        cheese = 0;
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){ // 치즈라면 치즈의 개수를 카운트
                    cheese++;
                }

            }
        }

        time = 0;
        bfs();


    }


    public void bfs(){


        // 치즈가 다 녹을 떄까지 반복
        while(true){

            setExAir(); // 외부 공기 알아내기
            Deque<Node> meltingTarget = new ArrayDeque<>();
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    if(map[i][j] == 1 && isMelting(j, i)){ // 녹을 치즈를 저장
                        meltingTarget.add(new Node(j, i));
                        cheese--;
                    }
                }
            }

            time++;
            // 다 녹았으면 반복 종료
            if(cheese == 0){
                System.out.println(time);
                break;
            }

            // 안녹았으면 녹은 치즈들을 공기로 전환
            while(!meltingTarget.isEmpty()){
                Node cur = meltingTarget.poll();
                map[cur.y][cur.x] = 0;
            }
        }



    }


    // 2변 이상이 외부 공기일 경우 찾기
    public boolean isMelting(int x, int y){
        int air = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(checkRange(nx, ny)){
                if(map[ny][nx] == -1){ // 외부 공기
                    air++;
                }
            }
        }
        return air >= 2;
    }

    public void setExAir(){
        Deque<Node> que = new ArrayDeque<>();
        que.offer(new Node(0, 0));
        visit = new boolean[R][C];
        map[0][0] = -1;

        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(checkRange(nx, ny) && !visit[ny][nx]){
                    if(map[ny][nx] != 1 ){ // 공기라면
                        que.add(new Node(nx, ny));
                        visit[ny][nx] = true;
                        map[ny][nx] = -1;
                    }
                }
            }
        }
    }


    public boolean checkRange(int x, int y){
        if(x >= 0 && y >= 0 && x < C && y < R){
            return true;
        }
        return false;
    }

    class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;

        }
    }
}
