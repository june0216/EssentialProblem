import java.util.*;
import java.io.*;
class 아이템찾기 {
    int[][] map;
    boolean[][] visited;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        visited = new boolean[101][101];
        map = new int[101][101];
        for(int i = 0 ; i < rectangle.length; i++){
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];
            
            draw(x1*2, y1*2, x2*2, y2*2);
            
        }
        answer = dfs(characterX*2, characterY*2,itemX*2, itemY*2);
        return answer/2;
    }
    
    public int dfs(int characterX, int characterY,int itemX, int itemY){
        
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(characterX, characterY, 0));
        visited[characterY][characterX] = true;
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.x == itemX && cur.y == itemY){
                return cur.cnt;
            }
            int nextX = 0;
            int nextY = 0;
            for(int i =  0 ;i < 4; i++){
                nextX = cur.x + dx[i];
                nextY = cur.y + dy[i];
                if(nextX >= 0 && nextX < 101 && nextY >= 0 && nextY < 101 && !visited[nextY][nextX] && map[nextY][nextX] == 1){
                visited[nextY][nextX] = true;  // 방문 후에 표시

                que.add(new Node(nextX, nextY, cur.cnt+1));
            }
            }
            
        }
        return 0;
    }
    
    public void draw(int x1, int y1, int x2, int y2){
        for(int i = x1; i <= x2; i++){
            for(int j = y1; j <= y2; j++){
                if(map[j][i] == 2) continue;
                map[j][i] = 2; // 내부 설정
                if (i == x1 || i == x2 || j == y1 || j == y2) {
                    map[j][i] = 1; // 경계 설정
                }
            }
        }
    }
                
}
                
class Node{
    int x;
    int y;
    int cnt;
    public Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
}
