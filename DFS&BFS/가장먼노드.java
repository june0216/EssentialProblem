import java.io.*;
import java.util.*;
class 가장먼노드 {
    List<Integer>[] li;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        li = new List[n+1];
        for(int i = 0; i < n+1; i++){
            li[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < edge.length; i++){
            li[edge[i][0]].add(edge[i][1]);
            li[edge[i][1]].add(edge[i][0]);
        }
        return bfs(n);
    }
    

    public int bfs(int n){
        boolean[] visited = new boolean[n+1];
        int[] len = new int[n+1];
        Deque<Integer> que = new ArrayDeque<>();
        que.add(1);
        visited[1] = true;
        
        while(!que.isEmpty()){
            int cur = que.poll();
            //System.out.println(li[cur[0]].get(0));
            for(int num : li[cur]){
                if(!visited[num]){
                    que.add(num);
                    len[num] = len[cur]+1;
                    visited[num] = true;
                }
            }
        }
        Arrays.sort(len);
        int max = len[n];
        
        int result = 0;
        for(int i = n; i >= 1;i--){
            if(max == len[i]){
                result++;
            }else{
                break;
            }
        
            
        }
        return result;
        
    }
}
