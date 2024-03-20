import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class DFS와BFS {
    public static int[][] nodeInfo;
    public static StringBuilder sb = new StringBuilder();

    public static boolean[] visited;
    public static int N;
    public static void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int startNum = Integer.parseInt(st.nextToken());

        nodeInfo = new int[N+1][N+1];
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodeInfo[from][to] = 1;
            nodeInfo[to][from] = 1; // 양방향이니까 반대편도 저장한다.

        }
        visited = new boolean[N+1];
        dfs(startNum);
        Arrays.fill(visited, false); // 다시 bfs에서 visited 여부 확인을 위해 초기화
        sb.append("\n");
        bfs(startNum);
        System.out.println(sb);
    }

    public static Deque<Integer> que = new ArrayDeque<>();
    public static void bfs(int start){
        que.offer(start);
        visited[start] = true;
        sb.append(start + " ");
        while(!que.isEmpty()){
            int current = que.poll();
            for(int i = 1; i < N+1; i++){ // current 노드와 연결된 정점을 찾기 위해 전체를 돌면서 찾는다.
                int connected = nodeInfo[current][i];
                if(connected ==1&&!visited[i] ){ // 연결되어 있으면서 방문하지 않았으면 큐에 넣는다.
                    visited[i] = true; // 방문 처리를 한다. -> 큐에서 꺼낼 때 방문 처리가 아니라 넣을 때 방문 처리를 하는 이유는 꺼낼 때 한다면 아직 꺼내기 전인데 방문했던 노드를 다시 방문할 수 있기 때문이다.
                    sb.append(i + " ");
                    que.offer(i);
                }
            }
        }
    }

    public static void dfs(int start){
        visited[start] = true;
        sb.append(start + " ");
        for(int i = 1; i < N+1; i++){ // start 노드와 연결된 정점을 찾기 위해 전체를 돌면서 찾는다.
            int connected = nodeInfo[start][i];
            if(connected ==1&&!visited[i] ){
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new DFS와BFS().solution();
    }
}

