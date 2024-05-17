import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 불우이웃돕기 {
    // 2차원의 정보를 노드와 간선으로 변환하는 것이 헷갈렸다.
    public static void main(String[] args) throws Exception{
        new 불우이웃돕기().solution();
    }


    char[][] map;

    PriorityQueue<Mst> pq;

    boolean[][] visited;

    int[] parent;

    int N;
    int total;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        total = 0;
        map = new char[N][N];
        pq = new PriorityQueue<>();

        for(int i = 0 ; i < N; i++){
            String input = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j]= input.charAt(j);
                if(map[i][j] == '0') continue;
                int cost = 0;
                if(map[i][j] >= 'a' && map[i][j] <= 'z'){
                    cost = map[i][j] - 'a' + 1;
                    pq.offer(new Mst(i, j, cost));
                    total += cost;
                }else if(map[i][j] >= 'A' && map[i][j] <= 'Z'){
                    cost = map[i][j] - 'A' + 27;
                    pq.offer(new Mst(i, j, cost));
                    total += cost;
                }


            }
        }
        visited = new boolean[N][N];
        parent = new int[N]; // N*2가 아니라 (i, j)에 하나 있어도 (j, i)도 연결되어 있는 것이므로 N개만 만들어주면 된다.
        for(int i = 0 ; i < N; i++){
            parent[i] = i;
        }
        kruskal();

    }

    public void kruskal(){
        int cnt = 0;
        int countCost = 0;
        while(!pq.isEmpty()){ // 가장 짧은 간선부터 꺼낸다.
            Mst cur = pq.poll();
            int fromParent = find(cur.from);
            int toParent = find(cur.to);
            if(fromParent != toParent){ // 만약 연결이 안되어 있다면
                countCost+= cur.cost; // 계산해주고
                union(fromParent, toParent); // 같은 부모로 세팅 == 연결되어 있다는 것을 표시
                cnt++;
            }
        }
        if(cnt != N-1){ // 모두가 연결되어 있지 않으므로 -1을 반환
            System.out.println(-1);
            return;
        }
        System.out.println(total - countCost); // 전체 가중치에서 연결하기 위한 최소 가중치를 빼면 잉여가 많아진다.
    }

    public int find(int num){
        if(parent[num] == num) return num;
        else return parent[num] = find(parent[num]);
    }

    public void union(int from, int to){
        if(from > to){
            parent[from] = to;
            return;
        }
        parent[to]=from;
    }

    class Mst implements Comparable<Mst>{

        int from;
        int to;
        int cost;

        public Mst(int from, int to, int cost){
            this.from =from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Mst mst){
            return this.cost  - mst.cost;
        }

    }


}
