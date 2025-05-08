import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 미로찾기 {
    public static void main(String[] args) throws Exception{
        new 미로찾기().solution();
    }

    int N;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        // 검정 -. 들어갈 수 없다
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int c = 0; c < s.length(); c++){
                map[i][c] = s.charAt(c) - '0';
            }
        }


        // 벽뚫고 이동하기 
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        boolean[][][] visited = new boolean[N][N][N*N];
        visited[0][0][0] = true;
        int min = Integer.MAX_VALUE;
        int[][] dp = new int[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[0][0] = 0;
        while(!pq.isEmpty()){
            Node cur= pq.poll();
            //System.out.println(cur.cost);
            if(cur.x == N-1 && cur.y == N-1){
                System.out.println(cur.cost);
                min = Math.min(min, cur.cost);
                return;
              
              
            }

            for(int i = 0; i < 4; i++){
                int nextX = dx[i] + cur.x;
                int nextY = dy[i] + cur.y;
                if(isValid(nextX, nextY)){
                    if(map[nextY][nextX] == 0){
                       // System.out.println(cur.cost);
                        if(visited[nextY][nextX][cur.cost+1]){
                            continue;
                        }

                        if(dp[nextY][nextX] > cur.cost+1){
                            dp[nextY][nextX] = cur.cost+1;
                        }
                        visited[nextY][nextX][cur.cost+1] = true;
                        pq.offer(new Node(nextX, nextY, dp[nextY][nextX]));
                      //  map[nextY][nextX] = 0;
                    }else{
                        
                       if(visited[nextY][nextX][cur.cost]) continue;
                        if(dp[nextY][nextX] > cur.cost){
                            dp[nextY][nextX] = cur.cost;
                        }
                        visited[nextY][nextX][cur.cost] = true;
                        
                        pq.offer(new Node(nextX, nextY, dp[nextY][nextX]));
                    }
                }
            }
        }
      
        System.out.println(min);
        
    }






    public boolean isValid(int x, int y){
        return x>=0 && y >=0 && x < N && y < N;
    }

    class Node implements Comparable<Node>{
        int x; 
        int y;
        int cost;
        public Node(int x, int y,int cost){
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
