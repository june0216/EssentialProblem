import java.util.*;

class 경주로건설2 {
    
    // 다익스트라 느낌으로 
    // 왜? -> 거리가 짧아도 비용이 클수도 있다. (먼저 도착한 것이 최솟값이 아님)
    
    
    /*
    고민점 
    (1) 방향 정보까지 DP에 저장 여부 -> 다시 방문할 수 없으므로 꼭 필요 
    (2) 우선순위 큐 대신 큐 사용하는 이유 
    */
    
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};
    // 방향 추가
    int N;
    int min;
    int[][][] dp;
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        // 방향이 없으면 안된다. -> 
        dp = new int[N][N][4];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        
        
        min = Integer.MAX_VALUE;
        // pq
        Deque<Node> pq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        dp[0][0][0] = 0;
        visited[0][0] = true;
         visited[1][0] = true;
        if(board[0][1] == 0){
            visited[0][1] = true;
            
            dp[0][1][0] = 100;
            pq.offer(new Node(1, 0, 100, 0)); // 옆
        }
        if(board[1][0] == 0){
             visited[1][0] = true;
            dp[1][0][1] = 100;
             pq.offer(new Node(0, 1, 100, 1));
        }
        
       
        
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.x == N-1 && cur.y == N-1 && min > cur.cost){
                //System.out.println(min);
                min = Math.min(min, cur.cost);
                //break;
                
            }
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(isValid(nx, ny)){
                    if(board[ny][nx] == 1) continue;
                    if(cur.dir ==i){
                        
                        if(!visited[ny][nx]){
                            dp[ny][nx][i] = cur.cost+100;
                            pq.offer(new Node(nx, ny, cur.cost+100, i));
                            visited[ny][nx] = true;
                            continue;
                        }
                        if(dp[ny][nx][i] >= cur.cost+100){
                            dp[ny][nx][i] = cur.cost+100;
                            pq.offer(new Node(nx, ny, cur.cost+100, i));
                        }
                        
                    }
                    
                    else{
                        // 비용 추가 
                        if(!visited[ny][nx]){
                            dp[ny][nx][i] = cur.cost+600;
                            visited[ny][nx] = true;
                            pq.offer(new Node(nx, ny, cur.cost+600, i));
                            continue;
                        }
                        
                        if(dp[ny][nx][i] >= cur.cost+600){ // 방문한 적있으면 방향 꺾은 거만 비교해보면 된다.
                            dp[ny][nx][i] = cur.cost+600;
                            pq.offer(new Node(nx, ny, cur.cost+600, i));
                        }
                    
                    }
                    
                }
            }
            
            
            
        }
        return min;
    }
    
    public boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    
    class Node implements Comparable<Node>{
        int x;
        int y;
        int cost;
        int dir;
        
        public Node(int x, int y, int cost, int dir){
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
        
        @Override
        public int compareTo(Node node){
            return this.cost - node.cost;
        }
    }
}
