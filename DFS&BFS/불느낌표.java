import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.*;

public class 불느낌표 {
    int R;
    int C;

    public static char WALL = '#';
    public static char FIRE = 'F';

    public static char START = 'J';

    public static char ROAD = '.';
    int[] dx = new int[]{1, -1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};


    char[][] map;
    Deque<Node> fire;


    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Node start = new Node(0, 0, 0);

        map = new char[R][C];
        fire = new ArrayDeque<>();
        for(int i =0; i < R; i++){
            map[i] =  br.readLine().toCharArray();
            for(int j = 0; j < C; j++){
                if(map[i][j] == START){ // 시작점을 따로 저장한다.
                    start = new Node(j, i, 0);
                }
                if(map[i][j] == FIRE){ // 불이라면 불 정보를 담는 큐에 넣는다.
                    fire.offer(new Node(j, i, 0));
                }


            }
        }

        int result = bfs(start);
        if(result == -1){ // 불가능할 경우
            System.out.println("IMPOSSIBLE");
        }
        else{
            System.out.println(result);
        }
    }

    public int bfs(Node start){
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(start); // 시작점을 넣는다.
        boolean[][] visited = new boolean[R][C];
        visited[start.y][start.x]= true;
        int time = 0;
        change(); // 시작하자마자 불이 번졌어야 했다.
        while(!que.isEmpty()){
            Node cur  = que.poll();
            if(cur.time > time){ // 시간이 지나는 순간 불이 퍼지도록 한다.
                time = cur.time;
                change();

            }
            if((cur.x == 0 || cur.y == 0 || cur.x == C-1 || cur.y == R-1)){ // 마지막 부분이라면 바로 탈출한다.
                return cur.time+1;
            }
            for(int i = 0; i< 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >= 0 && ny >= 0 && nx < C && ny < R){
                    if(map[ny][nx] == ROAD && !visited[ny][nx]){ // 갈 수 있는 길이라면
                        que.offer(new Node(nx, ny, cur.time+1));
                        visited[ny][nx] = true;

                    }

                }

            }

        }
        return -1; // 큐에 있는 것 다 돌렸는데 탈출 못함

    }

    public void change(){ // 불을 퍼지게 한다
        int total = fire.size(); // 해당 시간에 있는 신규  불의 사이즈
        while(total-- > 0 ){
            Node cur = fire.poll();
            for(int i = 0; i< 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >= 0 && ny >= 0 && nx < C && ny < R){
                    if(map[ny][nx] != WALL && map[ny][nx] != FIRE){ // 벽이 아니고 불이 아니라면 번지게 한다.
                        map[ny][nx] = FIRE;
                        fire.offer(new Node(nx, ny, 0)); // 신규 불에 넣는다. (다음 시간에 불이 파지는 근원지)
                    }

                }
            }
        }
    }


    class Node implements Comparable<Node>{
        int x;
        int y;
        int time;
        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;

        }

        @Override
        public int compareTo(Node node){
            return this.time - node.time;
        }
    }
    public static void main(String[] args) throws Exception{
        new 불느낌표().solution();
    }

}
