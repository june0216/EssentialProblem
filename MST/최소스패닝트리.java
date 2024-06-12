import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소스패닝트리 {
    public static void main(String[] args) throws Exception{
        new 최소스패닝트리().solution();
    }

    int N;
    int E;

    int[] parent;
    int[] rank;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Node(from, to, cost));
        }

        parent = new int[N+1];
        rank = new int[N+1]; // 각 순위를 저장할 배열
        for(int i = 1; i < N+1; i++){
            parent[i] = i;
        }

        int res = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int fromParent = find(cur.from);
            int toParent = find(cur.to);
            if(fromParent != toParent){
                res += cur.cost; // 비용을 업데이트
                union(fromParent, toParent); // 같은 부모를 같도록 한다.
            }
        }

        System.out.println(res);


    }

    public void union(int from, int to){
        if(rank[from] < rank[to]){
            rank[from] = to; // 높은 걸 넣는다.
        }else if(rank[from] > rank[to]){
            rank[to] = from;
        }else{
            rank[to] = from;// 랭크가 같은 경우
            rank[from]++; //증가한다.
        }

    }

    public int find(int num){
        if(parent[num] == num) return num;
        else return parent[num] = find(parent[num]);
    }

    public class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost){
            this.from  = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node node){
            return this.cost - node.cost;
        }
    }
}
