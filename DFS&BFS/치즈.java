import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.*;

// 토마토 문제와 비슷
public class 치즈 {

    int R;
    int C;
    int[][] map;

    int[] dx = new int[]{1, -1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};
    boolean[][] visited;

    int result;
    int cheeseCnt;

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        result = 0;

        map = new int[R][C];
        //4변 중에서 적어도 2변 이상
        //내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로

        cheeseCnt = 0;

        for(int i = 0 ; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int  j = 0 ; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    cheeseCnt++;
                }
            }
        }
        dfs();


    }

    public void dfs(){
        visited = new boolean[R][C];
        while(true){
            setExtrAir();
            Deque<Node> meltingTarget = new ArrayDeque<>();

            for(int i = 0; i< R; i++){
                for(int j = 0 ; j < C; j++){
                    if(map[i][j] == 1 && isCheeseMelting(i, j)){
                        meltingTarget.offer(new Node(j, i));
                    }
                }
            }

            while(!meltingTarget.isEmpty()){
                Node cheese = meltingTarget.poll();
                map[cheese.y][cheese.x] = 0;
                cheeseCnt--;
            }
            result++;

            if(cheeseCnt == 0){
                System.out.println(result);
                break;
            }
        }

    }

    public boolean isCheeseMelting(int y, int x){
        int air = 0;
        for(int i = 0; i< 4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(nx >= 0 && ny >= 0 && nx < C && ny < R){
                if(map[ny][nx] == -1){ // 외부공기일 때
                    air++;
                }

            }
        }
        if(air >= 2) return true;
        return false;

    }

    public void setExtrAir(){
        visited = new boolean[R][C];
        Deque<Node> que = new ArrayDeque<>();
        que.offer(new Node(0, 0)); // 가장 자리는 항상 공기이므로 가장 자리부터 시작한다.
        map[0][0] = -1;
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(int i = 0; i< 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >= 0 && ny >= 0 && nx < C && ny < R){
                    if(!visited[ny][nx] && map[ny][nx] != 1){ // 치즈가 아니면서 방문한 적이 없으면 외부 공기이므로 -1로 변경
                        map[ny][nx] = -1;
                        visited[ny][nx] = true;
                        que.offer(new Node(nx, ny));

                    }

                }
            }
        }
    }



    class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;

        }
    }


    public static void main(String[] args) throws Exception{
        new 치즈().solution();
    }
}
