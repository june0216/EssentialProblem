import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/*
각 학생들이 최단 시간 내에 갔다 오기를 원한다는 조건과 최장시간 걸린 학생을 골라야한다는 조건이 함께 있어 헷갈렸다.
하지만 다시 생각해보면 각 학생마다 최단 시간을 구해주고 그 중 그나마 오래걸린 학생을 골라주면 된다.
이때, 학생마다 최단 시간을 구해주기 위해서는 가중치가 있으므로 다익스트라를 사용하면 된다.

 */
public class 파티 {
    public static void main(String[] args) throws Exception{
        new 파티().solution();
    }

    int Target;
    List<Node>[] graph;
    List<Node>[] reverseGraph;
    int[] goCost;
    int[] backCost;

    int N;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Target = Integer.parseInt(st.nextToken()); // 모임 공간

        graph = new ArrayList[N+1]; // 돌아올 때 비용 계산 (출발 지점부터 각 최소 비용을 저장한다)
        reverseGraph = new ArrayList[N+1]; // 출발할 때 비용 계산
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        goCost = new int[N+1];
        backCost = new int[N+1];

        for(int i = 0 ;i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            reverseGraph[to].add(new Node(from, cost)); // 문제에선 단방향이라고 주어졌지만 생각의 전환을 통해 문제를 풀기 위해서 반대의 경우도 저장함

        }

        backCost = dijkstra(graph); // 모임 후 돌아올 때 계산
        goCost = dijkstra(reverseGraph); // 모임 지점에 가기 위한 계산


        // 최단 거리를 다 구했고 그 중 가장 오래 걸린 사람의 비용을 구한다.
        int res = Integer.MIN_VALUE;
        for(int i = 1; i < N+1; i++){
            res = Math.max(res, backCost[i] + goCost[i]);
        }

        System.out.println(res);
    }


    public int[] dijkstra(List<Node>[] arr){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[Target] = 0;// 출발지점은 0의 비용이 든다.
        pq.offer(new Node(Target, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            for(Node next : arr[cur.num]){
                if(dist[next.num] > cur.cost + next.cost){ // 원래 있던 비용보다 현재까지 온 비용 + 현재 지점에서 next까지의 비용을 더한 것이 더 작다면 업데이트
                    dist[next.num] = cur.cost+ next.cost;
                    pq.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
        return dist;

    }


    class Node implements Comparable<Node>{
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node){
            return this.cost - node.cost;
        }
    }
}
