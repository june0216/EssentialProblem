import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 나무재태크 {
    public static void main(String[] args) throws Exception{
        new 나무재태크().solution();
    }


    int N;
    int treeCnt;
    int[][] map;
    int[][] A;
    PriorityQueue<Node> tree;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        treeCnt = Integer.parseInt(st.nextToken());
        int year = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }


        List<Node> treeList = new ArrayList<>();
        tree = new PriorityQueue<>();
        for(int i = 0; i < treeCnt; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            tree.offer(new Node(x, y,age ));
        }

        for(int i = 0; i < year; i++){
            springAndSummer();
         
            fall();
            winter();
        }
        System.out.println(tree.size());
        

            

        
        
    }


    /*
    봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 
    각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다. 
    하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다. 
    만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
    */

    public void print(){
        System.out.println(tree.size());
    }

    public void springAndSummer(){
        Deque<Node> deadTree = new ArrayDeque<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        while(!tree.isEmpty()){
            Node cur = tree.poll();
            if(map[cur.y][cur.x] >= cur.age){
                map[cur.y][cur.x] -= cur.age;
                cur.age++;
                pq.offer(cur);
            }else{
             
                deadTree.add(cur);
            }

            
        }


        tree = pq;

        // 여름 - 여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 
        // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
        while(!deadTree.isEmpty()){
            Node cur = deadTree.poll();
            map[cur.y][cur.x] += cur.age/2;
        }
        
        
        
    }


    public void fall(){
        //가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 
        //어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다. 
        //상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
        //System.out.println("fall");


        int dx[] = {-1, 1, 0, 0, -1, 1, -1, 1};
        int dy[] = {0, 0, -1, 1, -1, 1, 1, -1};
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int cnt = tree.size();

        PriorityQueue<Node> tmp = new PriorityQueue<>();
        List<Node> li = new ArrayList<>();



        // 이렇게 먼저 5의 배수만 골라서 모아놓고 한 번에 업데이트하기 -> 틀렸습니다에서 벗어나는 포인트 
        while(cnt-- >0 ){
            Node cur = tree.poll();
            tmp.add(cur);
            if(cur.age%5 == 0){
                li.add(cur);
                
            }
            
        }

        


        for(Node cur : li){
            for(int i = 0;  i< 8; i++){
                int nx = dx[i] + cur.x;
                int ny  = dy[i] + cur.y;
                if(isValid(nx, ny)){
                    
                    tmp.add(new Node(nx, ny, 1));
                }
            }
        }



        // 이렇게 시간복잡도를 줄일 수 있음 
        tree = tmp;


        
        
    }


    public void winter(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] += A[i][j];
            }
        }
    }

    public boolean isValid(int x, int y){
        return x >=0 && x < N && y >=0 && y < N;
    }


    class Node implements Comparable<Node>{
        int x;
        int y;
        int age;
        public Node(int x, int y, int age){
            this.x = x;
            this.y = y;
            this.age = age;
            
        }

        @Override
        public int compareTo(Node node){
            return this.age - node.age;
        }

        
    }
}
