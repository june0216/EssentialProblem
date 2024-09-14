import java.util.*;
class 피로도 {
    // 모든 조합을 구해야함 -> 백트레킹!
    boolean[] visited;
    int answer = -1;
    public int solution(int k, int[][] dungeons) {

        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return answer;

    }
    
    public void dfs(int power, int[][] dungeons, int cnt){
        for(int i = 0; i < dungeons.length; i++){
            if(!visited[i] && dungeons[i][0] <= power){
                visited[i] = true;
                dfs(power-dungeons[i][1], dungeons, cnt+1);
                visited[i] = false;
            }
            
        }
        answer = Math.max(answer, cnt);
        
    }
}
