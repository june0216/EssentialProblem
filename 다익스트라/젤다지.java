import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 젤다지 {
    public static void main(String[] args) throws Exception{
        new 젤다지().solution();
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int cnt = 1;
        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0){
                break;
            }
            int[][] map = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0, map[0][0]));

            int[][] cost = new int[N][N];
            for(int c = 0 ;c <N; c++){
                Arrays.fill(cost[c], Integer.MAX_VALUE);
            }
            cost[0][0] = map[0][0];
            while(!pq.isEmpty()){
                Node cur = pq.poll();

                for(int i = 0; i < 4; i++){
                    int ny = dy[i] + cur.y;
                    int nx = dx[i] + cur.x;
                    if(nx >= 0 && ny >= 0 && nx < N && ny < N){
                        if(cost[ny][nx] > cur.cost+ map[ny][nx]){
                            cost[ny][nx] = cur.cost+ map[ny][nx];
                            pq.offer(new Node(nx, ny, cost[ny][nx]));
                        }

                    }
                }
            }
            sb.append("Problem " + cnt + ": ").append(cost[N-1][N-1]).append("\n");
            cnt++;
        }
        System.out.println(sb);
    }

    class Node implements Comparable<Node>{
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node){
            return this.cost - node.cost;
        }
    }
}
