import java.util.*;

class 리코체로봇 {
    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, -1, 1};
    char[][] map;
    int startX;
    int startY;
    int goalX;
    int goalY;
    int X;
    int Y;
    public int solution(String[] board) {
        int answer = -1;
        Y = board.length;
        X = board[0].length();
        map = new char[Y][X];
        for(int i = 0; i < board.length;i++){
            String str = board[i];
            for(int j = 0; j < X; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R'){
                    startX = j;
                    startY = i;
                }
                
                if(map[i][j] == 'G'){
                    goalX = j;
                    goalY = i;
                }
            }
        }
        
        answer = bfs(new Node(startX, startY), new Node(goalX, goalY));
       
        
        
        return answer;
    }
    
    
    /*
    포인트
    (1) 도착지점에 왔다고 끝나는 것은 아님 
    (2) 하나씩 이동하는 게 아니라 갈 수 있을 때까지 미끄러지는 것 
    
    
    내가 놓친 거 
    (1) 이게 장애물을 만나기 전까지 쭉 갈 수 있기 때문에 도착지점이어도 지나갈 수 있다를, 도착지점에서 다시 시작하는 것으로 생각했다. 
    -> 자의적으로 해석하지 마라
    */
    public int bfs(Node start, Node goal){
        Deque<Node> que = new ArrayDeque<>();
        que.offer(new Node(startX, startY));
        boolean[][] visited = new boolean[Y][X]; // 한 번만 갈 수 있음 
        int cnt = 1;
        visited[startY][startX] = true;
        // 장애물을 만나거나 벽을 만나면 -> 방향 전환 
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.x != startX && cur.y != startY && cur.x == goal.x && cur.y == goal.y){
              
                return cur.cost;
            }
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                
                
                if(!isValid(nx, ny)){ // 애초에 전환했는데 범위를 넘어가면 탐색을 하지 못한다. 
                    continue;
                }
                 
                
                // 미끄러져서 쭉 갈 수 있다는 것을 표현
                while(true){
                    // 장애물을 만나지 않거나 , 벽이 아닐때까지
                    if(isValid(nx, ny) && map[ny][nx] != 'D'){
                        nx += dx[i];
                         ny += dy[i];
                        
                        
                    }else{
                        // 넘어가기 전에를 맞추고 
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    }
                    
                    
                    
                    
                     
                }
                
                
                // 방문한 적이 있다면 큐에 넣지 않음 -> 중복 방지
                if(visited[ny][nx]){
                    continue;
                }
                //System.out.println(nx + " " + ny);
                que.offer(new Node(nx, ny, cur.cost+1));
                visited[ny][nx] = true;
                
            }
            
        }
        
        //System.out.println(cnt);
        return -1;
    }
    
    class Node{
        int x;
        int y;
        int cost;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
            public Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    
    public boolean isValid(int x, int y){
        return x >=0 && x < X && y >=0 && y < Y;
    }
    
    
    
}
