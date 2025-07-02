import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 뱀 {
    public static void main(String[] args) throws Exception{
        new 뱀().solution();
    }

    int N;
    int[][] map;
    int[] dx = {1, 0, -1, 0}; // 왼쪽 
    int[] dy = {0, 1, 0, -1};


    // 
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int appleCnt = Integer.parseInt(br.readLine());
        map = new int[N][N];
        Deque<Node> que = new ArrayDeque<>();
        
        for(int i = 0; i < appleCnt; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            map[row][col] = 1;
            
        }

        int cnt = Integer.parseInt(br.readLine());
         Map<Integer, String> move = new HashMap<>();
        for(int i = 0; i < cnt; i++){
             st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            String dir= st.nextToken();

            move.put(n, dir);
        }

       
         Set<String> visited = new HashSet<>();

        que.offer(new Node(0, 0, 0));
        int dirIdx = 0;
        String kk = "0 0";
        visited.add(kk);
        /*
        먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다
    */



        // 다시 갈 수 있다. 
        int time = 0;
       
        while(!que.isEmpty()){
            time++;
            int c = que.size();
            Node cur = que.peekFirst();
            int nx = dx[dirIdx] + cur.x;
            int ny = dy[dirIdx] + cur.y;
            if(isValid(nx, ny)){
                // 다음칸에 위치시킨다.
                String key = ny + " "+ nx;
                if(visited.contains(key)){ // 몸을 만났을 때
                    //System.out.println("몸을 만났을 때");
                    break;
                }
                que.offerFirst(new Node(nx, ny, cur.dir));
                
                visited.add(key);
                if(map[ny][nx] == 1){
                    // 사과가 있다면 
                    map[ny][nx] = 0;
                    
                }else{
                    Node tail = que.pollLast();
                    String k = tail.y + " " + tail.x;
                    visited.remove(k);
                    
                }
                //
                
    
                
               
                
            }else{
                // 벽을 만나거나
                
                break;
            }
            if(move.containsKey(time)){
                //Node head = que.pollFirst();
                if(move.get(time).equals("D")){
                    // 오른쪽 (시계 방향)
                     dirIdx = (dirIdx+1)%4;
                    //que.offerFirst(new Node(head.x, head.y, ((head.dir+1)%4)));
                    
                }else{
                    // 오른쪽 
                    dirIdx = (dirIdx+3)%4;
                    //que.offerFirst(new Node(head.x, head.y, ((head.dir+3)%4)));
                    
                }
            }
            
            
        }
        System.out.println(time);
        
        
        
    }

    public boolean isValid(int x, int y){
        return x >=0 && x < N && y >=0 && y < N;
    }

    class Node{
        int x;
        int y;
        int dir;
        public Node(int x,int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
        
    }
}
