import java.util.*;
class 전력망둘로나누기 {
    
    List<Integer>[] graph;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        graph = new List[n+1];
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            graph[v1].add(v2);
            graph[v2].add(v1);
            
        }
        
        for(int i = 0 ; i < wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));
            
            boolean[] visited = new boolean[n+1];
            int cnt = dfs(1, visited);
            
            answer = Math.min(answer, Math.abs(cnt - (n-cnt)));
            
            graph[v1].add(v2);
            graph[v2].add(v1);
            

        }
        
        return answer;
        
    }
    
    public int dfs(int idx, boolean[] visited) {
        visited[idx] = true;
        int cnt = 1;

        for (int neighbor : graph[idx]) {
            if (!visited[neighbor]) {
                cnt += dfs(neighbor, visited);
            }
        }

        return cnt;
    }
    
    
}
