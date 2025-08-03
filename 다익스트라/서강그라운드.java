import java.util.*;
import java.io.*;

class 서강그라운드 {
    public static void main(String[] args) throws Exception {
        new 서강그라운드().solution();
    }

    int[] itemList;      // 각 지역의 아이템 개수
    List<Node>[] li;     // 그래프 인접 리스트
    int findSize;        // 수색 범위
    int n;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        findSize = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());

        li = new List[n+1];
        itemList = new int[n+1];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i <= n; i++) {
            li[i] = new ArrayList<>();
            itemList[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            li[from].add(new Node(to, cost));
            li[to].add(new Node(from, cost));
        }

        int max = 0;
        // 모든 시작점에 대해 다익스트라 수행
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dijkstra(i));
        }

        System.out.println(max);
    }

    public int dijkstra(int start) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        // 틀린 이유 1 : 시작점들을 다 넣는 걸 독립적으로 생각하지 않았다. 
        // 틀린 이유 2 : 여기서 원래 cost를 기준으로 하는 게 아니라 아이템과 cost를 둘 다 고려하려고 했다. 
        // 
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (cur.cost > dist[cur.to]) continue;

            for (Node next : li[cur.to]) {
                int newCost = cur.cost + next.cost;
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }

       
        int total = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= findSize) { // 범위내라면 
                total += itemList[i];
            }
        }
        return total;
    }

    class Node implements Comparable<Node> {
        int to, cost;
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; 
        }
    }
}
