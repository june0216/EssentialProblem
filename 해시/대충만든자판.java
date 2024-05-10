import java.util.*;
public class 대충만든자판 {
    public int[] solution(String[] keymap, String[] targets) {
        List<Integer> answer = new ArrayList<>();
        // 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지 알아보고자 합니다.
        int[] minList = new int[26];
        Arrays.fill(minList, Integer.MAX_VALUE);
        for(String key : keymap){
            for(int i = 0; i < key.length();i++){
                char c = key.charAt(i);
                minList[c-65] = Math.min(minList[c-65], i+1);
            }
        }
        for(String val : targets){
            int temp = 0;
            for(char c : val.toCharArray()){
                if(minList[c-65] == Integer.MAX_VALUE){
                    temp = -1;
                    break;
                }
                temp += minList[c-65];
            }
            answer.add(temp);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
