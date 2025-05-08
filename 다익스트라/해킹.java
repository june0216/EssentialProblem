import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 해킹 {
    public static void main(String[] args) throws Exception{
        new 해킹().solution();
    }


    List<Node>[] info;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 단방향 
        while(N-->0){
            st = new StringTokenizer(br.readLine());
            int total = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int victNum = Integer.parseInt(st.nextToken());
            info = new List[total+1];
            for(int i = 0; i <= total; i++){
                info[i] = new ArrayList<>();
            }
            for(int i = 0; i < d; i++){
                
                 st = new StringTokenizer(br.readLine());
                    int a =  Integer.parseInt(st.nextToken());
                    int b =  Integer.parseInt(st.nextToken());
                    int s = Integer.parseInt(st.nextToken());
                    
                    info[b].add(new Node(a, s));// a가 b를 의존 
            }


            //총 몇 대의 컴퓨터가 감염되며 그에 걸리는 시간이 얼마인지 구하
            PriorityQueue<Node> pq = new PriorityQueue<>();
            int[] dp = new int[total+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            pq.offer(new Node(victNum, 0));
           
            dp[victNum] = 0;
            int time = 0;
            while(!pq.isEmpty()){
                Node cur= pq.poll();
                //System.out.println(cur.to);
                for(Node next : info[cur.to]){
                    if(dp[next.to] > (cur.cost + next.cost)){
                        //System.out.println(cur.to);
                        dp[next.to] = cur.cost + next.cost;
                        pq.offer(new Node(next.to, dp[next.to]));
                    }
                }
            }
            int cnt = 0;
            for(int i = 1; i < total+1; i++){
                if(dp[i] == Integer.MAX_VALUE) continue;
                time = Math.max(time, dp[i]);
                cnt++;
            }
            sb.append(cnt).append(" ").append(time).append("\n");
            
        }
        System.out.println(sb);
    }

    class Node implements Comparable<Node>{
        int to; 
        int cost;
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
