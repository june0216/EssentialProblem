import java.util.*;

class 부대복귀 {
    List<Node>[] info;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        info = new List[n+1];
        for(int i = 0; i <=n ;i++){
            info[i] = new ArrayList<>();
        }
        
        for(int i = 0; i <roads.length ;i++){
            info[roads[i][0]].add(new Node(roads[i][1], 1));
            info[roads[i][1]].add(new Node(roads[i][0], 1));
        }
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(destination, 0));
        dp[destination] = 0;
        while(!que.isEmpty()){
            Node cur = que.poll();
            for(Node next : info[cur.to]){
                if(dp[next.to] > cur.cost + next.cost){
                    dp[next.to] = cur.cost + next.cost;
                    que.offer(new Node(next.to, dp[next.to]));
                }
            }
        }
        answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            if(dp[sources[i]] == Integer.MAX_VALUE){
                answer[i] = -1;
                continue;
            }
            answer[i] = dp[sources[i]];
        }
        
        return answer;
    }
    
    class Node implements Comparable<Node>{
        int cost;
        int to;
        public Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node node){
            return this.cost - node.cost;
        }
    }
}
