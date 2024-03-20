import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class DFS와BFS2 {
    public static PriorityQueue<Integer>[] nodeInfo;
    public static PriorityQueue<Integer>[] nodeInfo2;
    public static StringBuilder sb = new StringBuilder();

    public static boolean[] visited;
    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int startNum = Integer.parseInt(st.nextToken());

        nodeInfo= new PriorityQueue[N+1]; //여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
        nodeInfo2= new PriorityQueue[N+1];
        for(int i = 1 ; i < N+1; i++){
            nodeInfo[i] = new PriorityQueue<>();
            nodeInfo2[i] = new PriorityQueue<>();
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodeInfo[from].offer(to);
            nodeInfo[to].offer(from); // 양방향이니까 각각 저장한다.

            nodeInfo2[from].offer(to);
            nodeInfo2[to].offer(from); // 양방향이니까 각각 저장한다.

        }
        visited = new boolean[N+1];

        dfs(startNum);
        Arrays.fill(visited, false);
        sb.append("\n");
        bfs(startNum);
        System.out.println(sb);
    }

    public static Deque<Integer> que = new ArrayDeque<>();
    public static void bfs(int start){
        que.offer(start);
        visited[start] = true;
        sb.append(start).append(" ");
        while(!que.isEmpty()){
            int current = que.poll();
            while(!nodeInfo2[current].isEmpty()){
                int next = nodeInfo2[current].poll();
                if(!visited[next]){
                    visited[next] = true;
                    sb.append(next + " ");
                    que.offer(next);
                }
            }
        }
    }

    public static void dfs(int start){
        visited[start] = true;
        sb.append(start + " ");
        while(!nodeInfo[start].isEmpty()){
            int next = nodeInfo[start].poll();
            if(!visited[next]){
                dfs(next);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new DFS와BFS2().solution();
    }
}
