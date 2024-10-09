import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소스패닝트리2 {
    public static void main(String[] args) throws Exception{
        new 최소스패닝트리2().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt((st.nextToken()));

        PriorityQueue<Node> pq = new PriorityQueue<>();
        List<Node>[] graph = new List[V+1];
        for(int i = 0; i < V+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        boolean[] visited = new boolean[V+1];

        long total = 0;
        int cnt = 0;
        pq.offer(new Node(1, 0));
        while(!pq.isEmpty() && cnt < V){
            Node next = pq.poll();
            if(!visited[next.node]){
                total += next.cost;
                visited[next.node] = true;
                cnt++;
                for(Node node : graph[next.node]){
                    if(visited[node.node]){
                        continue;
                    }
                    pq.offer(node);
                }
            }

        }
        System.out.println(total);
    }

    public class Node implements Comparable<Node>{
        long cost;
        int node;

        public Node(int node, long cost){
            this.node = node;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node node){
            if(this.cost > node.cost) return 1;
            else  if(this.cost < node.cost) return -1;
            else return 0;
        }
    }

}
