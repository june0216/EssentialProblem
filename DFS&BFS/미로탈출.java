import java.util.*;
public class 미로탈출 {

    public static void main(String[] args){
         new 미로탈출().solution(new String[]{"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"});
    }
    boolean[][] visited;
    int[] dx = new int[]{0, 0, -1, 1};
    int[] dy = new int[]{-1, 1, 0, 0};
    char[][] miro;

    int X;
    int Y;
    public int solution(String[] maps) {
        int answer = 0;
        Y = maps.length;
        X = maps[0].length();
        miro = new char[Y][X];

        visited = new boolean[Y][X];
        Node lever = new Node(0, 0, 0);
        Node start = new Node(0, 0, 0);
        Node end = new Node(0, 0, 0);


        for(int i = 0 ; i < Y;i++){
            miro[i] = maps[i].toCharArray();
            if(maps[i].contains("L") || maps[i].contains("E") || maps[i].contains("S")){
                for(int j = 0; j < X;j++){
                    if(maps[i].charAt(j) == 'L'){
                        lever.x = j;
                        lever.y = i;
                    }
                    if(maps[i].charAt(j) == 'E'){
                        end.x = j;
                        end.y = i;
                    }
                    if(maps[i].charAt(j) == 'S'){
                        start.x = j;
                        start.y = i;
                    }
                }
            }


        }

        // 레버는 항상 지나가야한다.
        //레버까지 가는 최단 경로 + 레버에서 문까지 가는 최단 경로

        int startToLever = dfs(start.x, start.y, lever.x, lever.y);
        if(startToLever == -1){
            return -1;
        }
        int LeverToEnd = dfs(lever.x, lever.y , end.x, end.y);
        if(LeverToEnd == -1){
            return -1;
        }
        return startToLever + LeverToEnd;


    }

    public int dfs(int x, int y, int targetX, int targetY){

        Deque<Node> pq = new ArrayDeque<>();
        pq.offer(new Node(x, y, 0));
        visited[y][x] = true;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.x == targetX && cur.y == targetY){
                visited = new boolean[Y][X];

                return cur.cnt;

            }
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >= 0 && ny >= 0 && nx < X && ny < Y){
                    if(!visited[ny][nx] && miro[ny][nx] != 'X'){
                        Node newNode = new Node(nx, ny, cur.cnt+1);

                        pq.offer(newNode);
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return -1;

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
}
