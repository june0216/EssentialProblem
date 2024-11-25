import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;
import java.util.StringTokenizer;

public class 녹색옷을입은애가젤다지 {
    public static void main(String[] args) throws Exception{
        new 녹색옷을입은애가젤다지().solution();
    }


    int map[][];
    int cost[][];

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while(true){

            int n = Integer.parseInt(br.readLine());
            if(n == 0){
                break;
            }
            map = new int[n][n];
            cost = new int[n][n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0, map[0][0]));
            for(int i = 0; i < n; i++){
                Arrays.fill(cost[i], Integer.MAX_VALUE);
            }


            cost[0][0] = map[0][0];
            while(!pq.isEmpty()){
                Node cur = pq.poll();

                for(int i = 0; i < 4; i++){
                    int ny = dy[i] + cur.y;
                    int nx = dx[i] + cur.x;
                    if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                        if(cost[ny][nx] > map[ny][nx] +cur.cost ){
                            cost[ny][nx] = map[ny][nx] + cur.cost;
                            pq.offer(new Node(nx, ny, cost[ny][nx]));
                        }

                    }
                }
            }

            sb.append("Problem " + cnt + ": ").append(cost[n-1][n-1]).append("\n");
            cnt++;



        }
        System.out.println(sb);



    }

    class Node implements Comparable<Node> {
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

