import java.util.*;
public class 배달 {
    public int[] cost;
    public List<Node>[] info;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        cost = new int[N+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        info = new List[N+1];
        for(int i = 0; i < N+1; i++){
            info[i] = new ArrayList<>();
        }

        for(int i = 0; i < road.length; i++){
            int from = road[i][0];
            int to = road[i][1];
            int weight = road[i][2];
            info[from].add(new Node(to, weight));
            info[to].add(new Node(from, weight));
        }

        answer = dijstra(K);
        return answer;
    }

    public int dijstra(int K){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        cost[1] = 0;
        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node next : info[current.num]){
                if(cost[next.num] >= (next.weight + cost[current.num])){
                    cost[next.num] = next.weight + cost[current.num];
                    pq.offer(new Node(next.num, cost[next.num]));
                }

            }
        }
        int result = 0;
        for(int num : cost){
            if(num <= K){
                result++;
            }
        }
        return result;
    }

    class Node implements Comparable<Node>{
        int weight;
        int num;

        public Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node node){
            return this.weight - node.weight;
        }
    }
}
