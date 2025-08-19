import java.util.*;
class 단어퍼즐 {
    public int solution(String[] strs, String t) {
        int answer = 0;

        Set<String> puzzles = new HashSet<>(Arrays.asList(strs));
        int[] dp = new int[t.length()+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= t.length(); i++){
            for(int j = 1; j <= 5; j++){
                if(i-j < 0){
                    continue;
                }
                
                String sub= t.substring(i-j, i);
                if(puzzles.contains(sub)){
                    if(dp[i-j] == Integer.MAX_VALUE) continue;
                    dp[i] = Math.min(dp[i-j]+1, dp[i]);
                }
            }
        }
        
        
        if(dp[t.length()] == Integer.MAX_VALUE) return -1;
        return dp[t.length()];
    }
}
