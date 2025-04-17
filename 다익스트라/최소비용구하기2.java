import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 최소비용구하기2 {
    public static void main(String[] args) throws Exception{
        new 최소비용구하기2().solution();
    }

    int X;
    int Y;
    int[][] map;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] visited;
    int N;

    /*
    역추적하면 된다. 
    
    */
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int cnt =  Integer.parseInt(br.readLine());
        List<Node>[] info = new List[N+1];
        for(int i = 0; i < N+1; i++){
            info[i] = new ArrayList<>();
        }

        int[] pre = new int[N+1];
        while(cnt-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            info[from].add(new Node(to, cost));
            //info[to].add(new Node(from, cost));
        }


        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        int dp[] = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        List<Integer> route = new ArrayList<>();
        route.add(start);
        dp[start] = 0;
        pre[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.cost > dp[cur.to]) continue; // 이 부분 없으면 시간 초과 
            int m = Integer.MAX_VALUE;
            int num = 0;
            for(Node next : info[cur.to]){
                
                if(dp[next.to] > dp[cur.to] + next.cost){
                    dp[next.to] = dp[cur.to] + next.cost;
                    // next.to로 갈 수 있는 노드들이 많다고 하더라도 최단 비용의 이전 노드가 업데이트 된다. 
                    pre[next.to] = cur.to;
                    pq.offer(new Node(next.to, dp[next.to]));
                }

                
            }
            
          
            
            
        }


        Deque<Integer> stack = new ArrayDeque<>();
        int idx = end;
        stack.offerLast(end);
        while(true){
            if(idx == start){
                
                break;
            }
            stack.offerLast(pre[idx]);
            idx = pre[idx];
            
        }

        int routeSize = stack.size();

        StringBuilder sb = new StringBuilder();
       while(!stack.isEmpty()){
           sb.append(stack.pollLast()).append(" ");
       }


        // 경로 
        System.out.println(dp[end]);
        System.out.println(routeSize);

        System.out.println(sb);

        
    }

        class Node implements Comparable<Node>{
        int to;
   
      
        int cost;
        public Node(int to,   int cost){
            this.to =to;
       
         
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node){
            
            return this.cost - node.cost;
        }
    }
}
