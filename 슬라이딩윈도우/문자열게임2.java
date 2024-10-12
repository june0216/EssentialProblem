import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class 문자열게임2 {
    public static void main(String[] args) throws Exception{
        new 문자열게임2().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        while(N-- >0){
            String word = br.readLine();
            int num = Integer.parseInt(br.readLine());
            Map<Character, List<Integer>> charPositions = new HashMap<>();
            int index = 0;
            for(char c : word.toCharArray()){
                charPositions.putIfAbsent(c, new ArrayList<>());
                charPositions.get(c).add(index++);
            }

            int maxLen = -1;
            int minLen = Integer.MAX_VALUE;
            for(List<Integer> position : charPositions.values()){
                if(position.size() < num){
                    continue;
                }

                for(int i = 0; i <= position.size() - num; i++){
                    int curLen = position.get(i + num-1) - position.get(i) + 1;
                    maxLen = Math.max(maxLen, curLen);
                    minLen = Math.min(minLen, curLen);
                }
            }
            if(maxLen == -1 || minLen == Integer.MAX_VALUE){
                sb.append(-1).append("\n");
            }
            else{
                sb.append(minLen + " " + maxLen).append("\n");
            }

        }
        System.out.println(sb);
    }
}
